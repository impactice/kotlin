package models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * [개념 적용 ①: 인터페이스(Interface)]
 * 우주선에 장착할 수 있는 모듈(엔진, 무기 등)의 공통 규격을 정의합니다.
 */
interface SpaceshipModule {
    val name: String
    fun operate()
}

class Engine(override val name: String, val level: Int) : SpaceshipModule {
    override fun operate() {
        println("$name (Level $level) 가동 중...")
    }
}

class MiningLaser(override val name: String, val power: Int) : SpaceshipModule {
    override fun operate() {
        println("$name (Power $power) 으로 채굴 중...")
    }
}

/**
 * 함선의 직업군(Class)을 정의한 Enum 클래스입니다.
 * 직업에 따라 기본 체력(HP)과 기본 공격력이 다르게 부여됩니다.
 */
enum class ShipClass(val className: String, val baseHp: Int, val baseAttack: Int) {
    FIGHTER("Fighter", 150, 30),
    EXPLORER("Explorer", 100, 15),
    MINER("Miner", 120, 20)
}

/**
 * 인벤토리에 보관 및 사용할 수 있는 아이템의 최상위 계층입니다 (Sealed Class 활용).
 * Sealed Class를 사용하면 분기문(when) 등에서 누락 없이 안전하게 타입을 분기할 수 있습니다.
 */
sealed class Item(val name: String, val description: String) {
    /** 아이템 사용 함수. 사용 성공 시 true, 실패 시 false 반환. */
    abstract fun useOn(ship: Spaceship): Boolean
}

class RepairKit : Item("Repair Kit", "HP 50 회복") {
    override fun useOn(ship: Spaceship): Boolean {
        // 이미 체력이 꽉 찼다면 아이템 사용 불가 (false 반환)
        if (ship.hp >= ship.maxHp) return false
        ship.hp = (ship.hp + 50).coerceAtMost(ship.maxHp)
        return true
    }
}

class FuelCell : Item("Fuel Cell", "연료 50 회복") {
    override fun useOn(ship: Spaceship): Boolean {
        ship.fuel += 50
        return true
    }
}

/**
 * 팩토리 패턴(Factory Pattern)을 활용해 문자열 이름만으로 
 * 알맞은 객체(Item)를 생성해주는 싱글톤(object) 객체입니다.
 */
object ItemFactory {
    fun createItem(name: String): Item? {
        return when (name) {
            "Repair Kit" -> RepairKit()
            "Fuel Cell" -> FuelCell()
            else -> null
        }
    }
}

/**
 * [개념 적용 ①: 상속(Inheritance)]
 * 추상 클래스인 Character를 상속받아 구체화된 플레이어의 '우주선' 클래스입니다.
 * 
 * Compose UI 렌더링 최적화를 위해 체력, 자원 등의 가변 변수들은 
 * `mutableStateOf` 위임(Delegation)을 사용하여 선언되었습니다. 
 * 이를 통해 값이 바뀔 때마다 GUI가 즉각적으로 실시간 업데이트됩니다.
 */
class Spaceship(
    name: String,
    val shipClass: ShipClass = ShipClass.EXPLORER,
    val modules: MutableList<SpaceshipModule> = mutableListOf(),
    val crew: MutableList<String> = mutableListOf()
) : Character(name, shipClass.baseHp, shipClass.baseHp, shipClass.baseAttack) {

    // 부모 클래스의 추상 프로퍼티 오버라이딩 (Compose 상태 구독 적용)
    override var maxHp: Int by mutableStateOf(shipClass.baseHp)
    override var hp: Int by mutableStateOf(shipClass.baseHp)
    override var attackPower: Int by mutableStateOf(shipClass.baseAttack)

    // 게임 내 주요 자원 (Compose 상태 구독 적용)
    var fuel: Int by mutableStateOf(100)
    var minerals: Int by mutableStateOf(0)
    
    // 경험치 및 레벨 시스템
    var level: Int by mutableStateOf(1)
    var exp: Int by mutableStateOf(0)

    /**
     * [개념 적용 ②: 컬렉션 활용 (Map)]
     * 인벤토리 아이템 이름(String)을 키(Key)로, 소지 개수(Int)를 값(Value)으로 가지는 Map 객체입니다.
     * 기본적으로 Repair Kit 2개와 Fuel Cell 1개를 제공합니다.
     */
    var inventory: Map<String, Int> by mutableStateOf(mapOf("Repair Kit" to 2, "Fuel Cell" to 1))

    /**
     * 피격 데미지 처리 함수.
     */
    override fun takeDamage(amount: Int) {
        hp = (hp - amount).coerceAtLeast(0)
    }

    override val isAlive: Boolean
        get() = hp > 0

    /**
     * 경험치(EXP) 획득 및 레벨업(Level Up) 처리를 담당하는 함수입니다.
     * @return 레벨업이 발생했으면 true 반환
     */
    fun addExp(amount: Int): Boolean {
        exp += amount
        var leveledUp = false
        // 요구 경험치량: 현재 레벨 * 30
        while (exp >= level * 30) {
            exp -= level * 30
            level++
            maxHp += 20
            hp = maxHp // 레벨업 시 체력을 꽉 채움
            attackPower += 5
            leveledUp = true
        }
        return leveledUp
    }

    /**
     * 인벤토리에 새로운 아이템을 추가합니다.
     */
    fun addItem(itemName: String, count: Int = 1) {
        // 기존 맵(Map)에서 키로 조회 후, 없으면 0으로 처리 (안전한 접근)
        val currentCount = inventory[itemName] ?: 0
        // Map의 요소(Entry)를 갱신
        inventory = inventory + (itemName to (currentCount + count))
    }

    /**
     * 인벤토리에서 아이템을 사용합니다.
     * @return 사용에 성공하면 true, 실패하거나 개수가 없으면 false 반환
     */
    fun useItem(itemName: String): Boolean {
        val count = inventory[itemName] ?: 0
        if (count > 0) {
            val item = ItemFactory.createItem(itemName)
            // [개념 적용 ③: 안전 호출(Safe Call)과 다형성 활용]
            // 아이템 객체를 생성하고 null이 아닌 경우에 한해 다형성을 통해 알맞은 useOn 로직 호출
            if (item != null && item.useOn(this)) {
                inventory = inventory + (itemName to (count - 1))
                return true
            }
        }
        return false
    }

    /**
     * [개념 적용 ③: 고차함수(람다식)]
     * 콘솔에 상태를 출력할 때 `joinToString` 내부의 람다식을 통해 모듈 이름을 추출하여 변환합니다.
     */
    fun showStatus() {
        println("=== [$name] 상태 ===")
        println("직업: ${shipClass.className} (Lv.$level)")
        println("체력: $hp/$maxHp, 공격력: $attackPower")
        println("연료: $fuel")
        println("광물: $minerals")
        println("승무원: ${crew.joinToString(", ")}")
        println("장착 모듈: ${modules.joinToString(", ") { it.name }}")
    }
}
