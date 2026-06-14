package game

import kotlinx.coroutines.*
import models.*
import dsl.spaceship
import utils.FileStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameManager {
    val myShip = spaceship {
        name("Stargazer-1")
        modules {
            add(Engine("Warp Drive", 1))
            add(MiningLaser("Basic Laser", 10))
        }
        crew {
            add("Captain Kim")
            add("Engineer Lee")
        }
    }

    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default + Job())

    init {
        // 저장된 데이터가 있으면 불러오기
        FileStorage.load()?.let { data ->
            myShip.fuel = data["fuel"] as Int
            myShip.minerals = data["minerals"] as Int
            log("저장된 우주선 데이터를 불러왔습니다.")
        } ?: run {
            log("새로운 탐사를 시작합니다. 승무원이 우주선에 탑승했습니다.")
        }
    }

    fun startBackgroundMining() {
        scope.launch {
            while (isActive) {
                delay(3000L) // 3초마다 채굴 비동기 로직
                myShip.minerals += 5
                log("자동 채굴: 광물 +5 (현재: ${myShip.minerals})")
            }
        }
    }

    fun scanSector() {
        scope.launch {
            log("우주 섹터 스캔 중...")
            delay(1500L) // 스캔 지연 애니메이션 효과
            log("스캔 완료: 새로운 행성 [알파-센타우리] 발견!")
        }
    }

    fun saveGame() {
        FileStorage.save(myShip)
        log("함선 데이터가 성공적으로 저장되었습니다.")
    }

    private fun log(message: String) {
        _logs.value = _logs.value + message
    }
}
