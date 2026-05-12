# 추상 클래스와 인터페이스

## 1. 추상 클래스와 인터페이스

### 추상 클래스(abstract class)

### [예제] 추상 클래스 Vehicle , [예제] Vehicle 하위 클래스 Car, Motocycle
```
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

```

<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/5718b2dd-d4cd-48f6-99eb-406c46f730f6" />

### [예제] object를 사용하여 단일 인스턴스 생성
```
//추상 클래스
abstract class Printer {
    abstract fun print()
}
/*
class Printer1 : Printer() {
    override fun print() {}
}
*/ //이걸 사용하지 않고 함
val myPrinter = object: Printer() {
    override fun print() {
        println("출력합니다.")
    }
}

fun main(){
    myPrinter.print()
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/06c08e63-9dcb-413a-8ece-93d05c84ef89" />


### 인터페이스(Interface)

### 코틀린의 인터페이스

### 인터페이스의 선언과 구현

### [예제]  Cat 클래스 구현하기 , 게터를 구현한 프로퍼티

```
interface Pet {
    var category: String    //추상 프로퍼티
    val msgTags:String
        get() = "I'm yoer lovely pet"
    fun feeding()           //추상 메서드
    fun patting() {         //일반 메서드 (구현부가 포함됨)
        println("Keep patting!")
    }
}
class Cat(override var category: String) : Pet {
    override fun feeding() {
        println("Feed the cat a tuna can!")
    }
}
fun main(){
    val cat = Cat("small")
    println("Pet Category:${cat.category}")
    println("Pet Message Tags:${cat.msgTags}")
    cat.feeding()
    cat.patting()

}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/ffc215ad-9b61-4579-b985-d2c52df7214c" />

```

open class Animal(val name: String)

interface Pet {
    var category: String    //추상 프로퍼티
    val msgTags:String
        get() = "I'm yoer lovely pet"
    fun feeding()           //추상 메서드
    fun patting() {         //일반 메서드 (구현부가 포함됨)
        println("Keep patting!")
    }
}
class Dog(name: String, override var category: String) : Animal(name), Pet {
    override fun feeding() {
        println("Dog the dog a bone")
    }
}
class Cat(name: String, override var category: String) : Animal(name), Pet {
    override fun feeding() {
        println("Feed the cat a tuna can!")
    }
}
fun main(){
    val dog = Dog("Toto", "BigFat")
    val cat = Cat("Coco","small")
    println("Pet Category:${cat.category}")
    println("Pet Message Tags:${cat.msgTags}")
    cat.feeding()
    cat.patting()

}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/62ac1824-752b-4e32-b91e-0db6eee51873" />

```

open class Animal(val name: String)

interface Pet {
    var category: String    //추상 프로퍼티
    val msgTags:String
        get() = "I'm yoer lovely pet"
    fun feeding()           //추상 메서드
    fun patting() {         //일반 메서드 (구현부가 포함됨)
        println("Keep patting!")
    }
}
class Dog(name: String, override var category: String) : Animal(name), Pet {
    override fun feeding() {
        println("Dog the dog a bone")
    }
}
class Cat(name: String, override var category: String) : Animal(name), Pet {
    override fun feeding() {
        println("Feed the cat a tuna can!")
    }
}
class Master{
    fun playWithPet(dog: Dog) {
        println("Enjoy with my dog.")
    }
    fun playWithPet(cat: Cat) {
        println("Enjoy with my cat.")
    }
}
fun main(){
    val master = Master()
    val dog = Dog("Toto", "BigFat")
    val cat = Cat("Coco","small")
    println("Pet Category:${cat.category}")
    println("Pet Message Tags:${cat.msgTags}")
    cat.feeding()
    cat.patting()
    println("Pet Message Tags:${dog.msgTags}")
    dog.feeding()
    dog.patting()
    master.playWithPet(dog)
    master.playWithPet(cat)

}
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/88388fc1-e080-4764-a71d-5f8657017bbb" />

```
open class Animal(val name: String)

interface Pet {
    var category: String    // 추상 프로퍼티
    var species: String
    val msgTags: String
        get() = "I'm your lovely pet"
    fun feeding()           // 추상 메서드
    fun patting() {         // 일반 메서드 (구현부가 포함됨)
        println("Keep patting!")
    }
}

class Dog(name: String, override var category: String) : Animal(name), Pet {
    override var species: String = "dog"
    override fun feeding() {
        println("Feed the dog a bone")
    }
}

class Cat(name: String, override var category: String) : Animal(name), Pet {
    override var species: String = "cat"
    override fun feeding() {
        println("Feed the cat a tuna can!")
    }
}

class Master {
    fun playWithPet(pet: Pet) {
        println("Enjoy with my ${pet.species}")
    }
}

fun main() {
    val master = Master()
    val dog = Dog("Toto", "BigFat")
    val cat = Cat("Coco", "Small")

    master.playWithPet(dog)
    master.playWithPet(cat)
}

```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/93817d61-2074-4a71-b978-8e5757d74ab2" />

