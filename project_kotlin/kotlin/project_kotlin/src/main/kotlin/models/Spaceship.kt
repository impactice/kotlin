package models

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

class Spaceship(
    val name: String,
    val modules: List<SpaceshipModule>,
    val crew: List<String>
) {
    var fuel: Int = 100
    var minerals: Int = 0

    fun showStatus() {
        println("=== [$name] 상태 ===")
        println("연료: $fuel")
        println("광물: $minerals")
        println("승무원: ${crew.joinToString(", ")}")
        println("장착 모듈: ${modules.joinToString(", ") { it.name }}")
    }
}
