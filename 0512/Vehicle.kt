// 추상클래스 - 상속받는 하위클래스가 추상 메서드를 구현해야 함
abstract class Vehicle(val name: String, val color: String, val weight: Double) {
    abstract var maxSpeed: Double // abstract선언을 하면 클래스도 선언을 해야 함(이걸 함으로써 초기화 형식이 아님) //추상 프로퍼티 : 하위 클래스에서 오버라이드 하고 초기화
    var year = "2026"
    abstract fun start()
    abstract fun stop()
    fun displaySpeces() {
        println("Name:$name, Color:$color, Weight:$weight, Year:$year, MaxSpeed:$maxSpeed")
    }
}

class Car(name: String, color: String, weight: Double, override var maxSpeed: Double)
    : Vehicle(name, color, weight) {
    override fun start() {
        println("Car Started")
    }
    override fun stop() {
        println("Car Stopped")
    }
}

class Motorcycle(name: String, color: String, weight: Double, override var maxSpeed: Double)
    : Vehicle(name, color, weight) {
    override fun start() {
        // 코드의 구현
        println("Bike Started")
    }
    override fun stop() {
        // 코드의 구현
        println("Bike Stopped")
    }
}

fun main() {
    val car = Car("SuperMatiz", "yellow", 1110.0, 270.0)
    val bike = Motorcycle("DreamBike", "red", 173.0, 100.0)

    car.displaySpeces()
    car.start()
    car.stop()

    bike.displaySpeces()
    bike.start()
    bike.stop()
}
