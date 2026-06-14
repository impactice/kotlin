package game

import kotlinx.coroutines.*
import models.*
import dsl.spaceship
import utils.FileStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * 게임의 현재 씬(상태)를 나타내는 Sealed Class 입니다.
 * Sealed Class를 활용하면 상태를 한정된 범위(CharacterCreation, Exploring, Combat, GameOver)
 * 로 묶어버릴 수 있어, 상태 분기 시 예기치 않은 오류를 막을 수 있습니다.
 */
sealed class GameState {
    object CharacterCreation : GameState()
    object Exploring : GameState()
    data class Combat(val enemy: Enemy) : GameState() // Combat 상태일 때는 교전 중인 적(Enemy) 객체를 들고 있습니다.
    object GameOver : GameState()
}

/**
 * 게임의 전반적인 로직, 진행, 상태 저장을 담당하는 매니저 클래스입니다.
 */
class GameManager {
    
    /**
     * 나의 우주선 객체. 
     * `by mutableStateOf`를 통해 이 객체 전체가 바뀌는 순간 UI가 자동으로 감지(Recomposition)하도록 만듭니다.
     */
    var myShip by mutableStateOf(spaceship {
        name("Unknown")
        shipClass(ShipClass.EXPLORER)
        modules {
            add(Engine("Warp Drive", 1))
            add(MiningLaser("Basic Laser", 10))
        }
    })

    // --- StateFlow 활용 영역 (코루틴 기반의 상태 관리 및 UI 통신용 데이터 관로) ---
    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs.asStateFlow()

    private val _gameState = MutableStateFlow<GameState>(GameState.CharacterCreation)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _discoveredPlanets = MutableStateFlow<Set<String>>(emptySet())
    val discoveredPlanets: StateFlow<Set<String>> = _discoveredPlanets.asStateFlow()
    // -------------------------------------------------------------

    // 코루틴(비동기 로직)을 실행하기 위한 스코프
    private val scope = CoroutineScope(Dispatchers.Default + Job())

    // 스캔 시 랜덤으로 발견될 네임드 행성 목록
    private val planetNames = listOf("Kepler-452b", "Gliese 581g", "Proxima Centauri b", "TRAPPIST-1e", "Mars", "Titan", "Alpha Centauri Bb", "HD 209458 b")

    init {
        // 게임매니저 생성 시 디스크에서 저장된 파일을 읽어옵니다.
        val savedData = FileStorage.load()
        if (savedData != null) {
            try {
                // 저장된 맵(Map)에서 값들을 추출 (Downcasting)
                val shipClass = ShipClass.valueOf(savedData["shipClass"] as String)
                myShip = spaceship {
                    name(savedData["name"] as String)
                    shipClass(shipClass)
                    modules {
                        add(Engine("Warp Drive", 1))
                        add(MiningLaser("Basic Laser", 10))
                    }
                }
                myShip.fuel = savedData["fuel"] as Int
                myShip.minerals = savedData["minerals"] as Int
                myShip.hp = savedData["hp"] as Int
                myShip.maxHp = savedData["maxHp"] as Int
                myShip.attackPower = savedData["attackPower"] as Int
                myShip.level = savedData["level"] as Int
                myShip.exp = savedData["exp"] as Int
                
                @Suppress("UNCHECKED_CAST")
                val inv = savedData["inventory"] as? Map<String, Int> ?: emptyMap()
                myShip.inventory = inv

                @Suppress("UNCHECKED_CAST")
                val planets = savedData["discoveredPlanets"] as? Set<String> ?: emptySet()
                _discoveredPlanets.value = planets

                log("저장된 우주선 데이터를 성공적으로 불러왔습니다.")
                // 데이터가 성공적으로 복원되었으므로 곧바로 탐사 상태로 전환합니다.
                _gameState.value = GameState.Exploring
            } catch (e: Exception) {
                log("데이터 복원 중 오류 발생: ${e.message}")
            }
        } else {
            log("저장된 데이터가 없습니다. 새로운 캐릭터(함선)를 생성하세요.")
        }
    }

    /**
     * 캐릭터 생성 화면에서 "시작" 버튼을 누를 때 호출됩니다.
     */
    fun createCharacter(name: String, shipClass: ShipClass) {
        // 유효성 검증
        if (name.isBlank()) {
            log("❌ 함선 이름은 비어있을 수 없습니다.")
            return
        }
        
        // DSL(Builder)을 통해 완전한 함선 객체 새로 생성
        myShip = spaceship {
            name(name)
            shipClass(shipClass)
            modules {
                add(Engine("Warp Drive", 1))
                add(MiningLaser("Basic Laser", 10))
            }
        }
        log("함선 [${myShip.name}](${myShip.shipClass.className}) 건조 완료. 탐사를 시작합니다!")
        _gameState.value = GameState.Exploring
    }

    /**
     * [선택 구현: 코루틴(Coroutine) 비동기 처리]
     * 별도의 스레드처럼 백그라운드에서 동작하며, 4초마다 광물을 자동으로 캐냅니다.
     */
    fun startBackgroundMining() {
        scope.launch {
            while (isActive) {
                delay(4000L)
                // 평화롭게 탐사 중이고 함선이 멀쩡할 때만 동작합니다.
                if (_gameState.value is GameState.Exploring && myShip.hp > 0) {
                    myShip.minerals += 2
                    log("자동 채굴: 광물 +2 (현재: ${myShip.minerals})")
                }
            }
        }
    }

    /**
     * '우주 섹터 스캔' 버튼 클릭 시 실행. 각종 랜덤 이벤트가 발생하는 진입점입니다.
     */
    fun scanSector() {
        if (_gameState.value !is GameState.Exploring) return
        
        scope.launch {
            log("우주 섹터 스캔 중...")
            delay(1000L) // 코루틴 delay 함수로 스캔에 소요되는 시간 1초를 연출 (UI 멈춤 없음)
            
            // 0 ~ 99 의 난수 추출
            val random = Random.nextInt(100)
            when {
                random < 20 -> { 
                    // 20% 확률로 적(해적) 등장
                    val enemyLevel = myShip.level + Random.nextInt(3)
                    val alien = Enemy(
                        name = "우주 해적선 (Lv.$enemyLevel)", 
                        maxHp = 40 + (enemyLevel * 10), 
                        attackPower = 5 + (enemyLevel * 5),
                        expReward = 15 + (enemyLevel * 5)
                    )
                    log("🚨 경고: [${alien.name}] 출현! 전투 준비!")
                    _gameState.value = GameState.Combat(alien) // 상태를 Combat으로 전환하여 UI를 변경
                }
                random < 40 -> { 
                    // 20% 확률로 버려진 아이템 발견
                    val itemTypes = listOf("Repair Kit", "Fuel Cell")
                    val foundItem = itemTypes.random()
                    myShip.addItem(foundItem, 1)
                    log("🎁 버려진 화물선 발견! [$foundItem] 1개를 획득했습니다.")
                }
                random < 60 -> { 
                    // 20% 확률로 자연 회복 이벤트
                    val restoredHp = 30
                    myShip.hp = (myShip.hp + restoredHp).coerceAtMost(myShip.maxHp)
                    log("✨ 신비로운 성운을 통과하며 선체 자동 수리! HP가 ${restoredHp}만큼 회복되었습니다.")
                }
                else -> { 
                    // 40% 확률로 자원 행성 발견
                    val pName = planetNames.random()
                    // Set(집합)에 저장하여 중복을 자동 방지 (개념 적용 ②: 컬렉션 Set)
                    _discoveredPlanets.value = _discoveredPlanets.value + pName
                    
                    val mineralReward = Random.nextInt(20, 50)
                    myShip.minerals += mineralReward
                    log("🌌 스캔 완료: 자원이 풍부한 평화로운 행성 [$pName] 발견! 광물 ${mineralReward} 획득.")
                }
            }
        }
    }

    /**
     * 전투 모드에서 '공격'을 눌렀을 때의 턴제 전투 로직입니다.
     */
    fun attackEnemy() {
        val currentState = _gameState.value
        if (currentState is GameState.Combat) {
            val enemy = currentState.enemy
            
            // 플레이어 -> 적 공격 (추상 메서드 다형성 호출)
            enemy.takeDamage(myShip.attackPower)
            log("💥 ${enemy.name}에게 ${myShip.attackPower}의 피해! (적 HP: ${enemy.hp})")

            if (!enemy.isAlive) {
                // 적 사망 처리 (승리)
                log("🏆 전투 승리! 전리품으로 광물을 30, 경험치를 ${enemy.expReward} 획득했습니다.")
                myShip.minerals += 30
                
                // 경험치 추가 후 레벨업이 발생하면 true 리턴 (함수 반환값 활용)
                if (myShip.addExp(enemy.expReward)) {
                    log("🎉 레벨 업! 현재 레벨: ${myShip.level} (최대 체력 및 공격력 증가)")
                }
                _gameState.value = GameState.Exploring // 평화 상태로 복귀
            } else {
                // 적 생존 시 플레이어에게 반격
                myShip.takeDamage(enemy.attackPower)
                log("⚠️ 적의 반격! 함선이 ${enemy.attackPower}의 피해를 입었습니다! (내 HP: ${myShip.hp})")
                
                if (!myShip.isAlive) {
                    log("💀 함선이 파괴되었습니다... GAME OVER.")
                    _gameState.value = GameState.GameOver // 게임 오버 상태로 전환
                }
            }
        }
    }

    /**
     * 전투 중 도망갈 때 호출되는 함수입니다.
     */
    fun flee() {
        if (_gameState.value is GameState.Combat) {
            // 조건: 연료가 충분해야 도망 가능
            if (myShip.fuel >= 10) {
                log("💨 도망칩니다! 엔진 과부하로 연료를 10 소비했습니다.")
                myShip.fuel -= 10
                _gameState.value = GameState.Exploring
            } else {
                log("❌ 연료가 부족하여 도망칠 수 없습니다!")
            }
        }
    }

    /**
     * 인벤토리 패널에서 '사용' 버튼을 눌렀을 때 아이템을 소비합니다.
     */
    fun useItem(itemName: String) {
        if (myShip.useItem(itemName)) {
            log("✅ [$itemName] 아이템을 사용했습니다!")
        } else {
            log("❌ [$itemName] 아이템 사용에 실패했습니다 (체력이 이미 꽉 찼거나 조건 불만족).")
        }
    }

    /**
     * '게임 저장' 버튼 클릭 시 호출됩니다.
     */
    fun saveGame() {
        FileStorage.save(myShip, _discoveredPlanets.value)
        log("함선 및 탐사 데이터가 성공적으로 저장되었습니다.")
    }

    /**
     * 로그 창에 문자열을 한 줄 추가하는 헬퍼 함수입니다.
     */
    fun log(message: String) {
        _logs.value = _logs.value + message
    }
}
