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

### 여러 인터페이스의 구현

### 다중 상속

### [예제] 인터페이스 다중 상속

### 인터페이스의 위임

### 위임을 이용한 멤버 접근

```
interface Nameable{
    val name: String
}
class StaffName:Nameable{
    override val name: String = "Sean"
}
class Work:Runnable{
    override fun run() {
        println("Work...")
    }
}
class Person(name:Nameable,work:Runnable):Nameable by name, Runnable by work
fun main(){
    val p = Person(StaffName(),Work())
    val p2 = Person2(StaffName(),Work())
    println(p.name)
    p.run()
}
class Person2(name:StaffName,work:Work)
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/22cad93c-168b-42b4-abf1-515fac9210a7" />

### [응용] 커피 제조기 만들어 보기

직접 해보라고 하심

# 데이터 클래스와 기타 클래스

## 2. 데이터 클래스와 기타 클래스

### 데이터 전달을 위한 데이터 클래스

### 데이터 클래스 선언하기

```
package section2

data class Customer (var name:String, var email:String) {
    var job:String = "Unknown"
    constructor(name:String, email:String, _job:String) : this(name,email) {
        job = _job
    }
    init{
        println("data class")
    }
}
// equals(), hashCode(), copy(), toString(), componentN() 이것들은 데이터 클래스가 자동 생성하는 메서드이다.

fun main() {
    val cus1 = Customer("John","john@mail.com")
    val cus2 = Customer("Sean","sean@mail.com")
    val cus3 = Customer("John","john@mail.com")
    println(cus1 == cus2)
    println(cus2 == cus3)
    println(cus2.equals(cus3))
    println("${cus2.hashCode()}, ${cus3.hashCode()}")
}
```

<img width="1919" height="1025" alt="image" src="https://github.com/user-attachments/assets/0b589432-4e48-49a8-a91d-6f61f428e6f2" />


```
package section2

data class Customer (var name:String, var email:String) {
    var job:String = "Unknown"
    constructor(name:String, email:String, _job:String) : this(name,email) {
        job = _job
    }
    init{
        println("data class")
    }
}
// equals(), hashCode(), copy(), toString(), componentN() 이것들은 데이터 클래스가 자동 생성하는 메서드이다.

fun main() {
    val cus1 = Customer("John","john@mail.com")
    val cus2 = Customer("Sean","sean@mail.com")
    val cus3 = Customer("John","john@mail.com")
    println(cus1 == cus2)
    println(cus2 == cus3)
    println(cus2.equals(cus3))
    println("${cus2.hashCode()}, ${cus3.hashCode()}")
    val cus4 = cus1.copy("Jack")
    println(cus1.toString())
    println(cus4.toString())
}
```

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/aad4fec8-367d-4df2-b9d2-34ff9529df7d" />

### 객체 디스트럭쳐링(Destructuring) : 구조 분해 할당


- 객체가 가지고 있는 프로퍼티를 **개별 변수로 분해하여 할당**하는 방법
    
    ```kotlin
    val (name, email) = cus1
    println("name = $name, email = $email")
    ```
    
- 특정 프로퍼티를 가져 올 필요 없을 때는 **밑줄**로 표기
    
    ```kotlin
    val (_, email) = cus1 // 첫 번째 프로퍼티 제외
    ```
    
- **componentN()** 메서드 이용

    ```kotlin
    val name2 = cus1.component1()
    val email2 = cus1.component2()
    println("name = $name2, email = $email2")
    ```
- 객체 데이터가 많은 경우 반복문 사용
    
    ```kotlin
    val cus1 = Customer("Sean", "sean@mail.com")
    val cus2 = Customer("Sean", "sean@mail.com")
    val bob = Customer("Bob", "bob@mail.com")
    val erica = Customer("Erica", "erica@mail.com")
     
    val customers = listOf(cus1, cus2, bob, erica) // 모든 객체를 컬렉션 List 목록으로 구성
    
    **for((name, email) in customers) { // 반복문을 이용해 모든 객체의 프로퍼티 분해
        println("name = $name, email = $email")
    }**
    ```
    
- 함수로부터 객체가 반환되는 경우 사용
    
    ```kotlin
    fun myFunc(): Customer {
        return Customer("Mickey", "mic@abc.com")
    }
    
    val (myName, myEmail) = myFunc()
    ```
    
- 람다식에서 사용

    ```kotlin
    // 람다식 함수로 Destructuring 된 변수 출력해 보기
    val myLamda = {
        (nameLa, emailLa): Customer ->
        println(nameLa)
        println(emailLa)
    }
    myLamda(cus1)
    ```

```
package section2

data class Customer (var name:String, var email:String) {
    var job:String = "Unknown"
    constructor(name:String, email:String, _job:String) : this(name,email) {
        job = _job
    }
    init{
        println("data class")
    }
}
// equals(), hashCode(), copy(), toString(), componentN() 이것들은 데이터 클래스가 자동 생성하는 메서드이다.

fun main() {
    val cus1 = Customer("John","john@mail.com")
    val cus2 = Customer("Sean","sean@mail.com")
    val cus3 = Customer("John","john@mail.com")
    println(cus1 == cus2)
    println(cus2 == cus3)
    println(cus2.equals(cus3))
    println("${cus2.hashCode()}, ${cus3.hashCode()}")
    val cus4 = cus1.copy("Jack")
    println(cus1.toString())
    println(cus4.toString())

    //반복문을 이용한 모든 객체의 프로퍼티 분해
    val customers = listOf(cus1, cus2, cus3,cus4)
    for((name, email) in customers) {
        println("name = $name, email = $email")
    }
    //함수 반환 값을 분해 저장
    val (myName, myEmail) = myFunc()
    println("$myName,$myEmail")

    //람다식에서 사용하기
    val myLambda = {
        (numeLa,emilLa): Customer ->
        println(numeLa)
        println(emilLa)
    }
    myLambda(cus4)
}
fun myFunc():Customer{
    return Customer("Mickey","mic@mail.com")
}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/4037f8c1-ae4b-45a5-ba8b-c2956515d7cd" />


### 범용 데이터 클래스 Pair와 Triple

### 내부 클래스 기법

### 이너 클래스 (Inner Class)


### 지역 클래스 (Local Class)

### 익명 객체

### 쉴드 클래스

### 열거형 클래스 

### 애노테이션 클래스
(시험에는 안 나올 것 같대)


