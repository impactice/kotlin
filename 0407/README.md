# 상속과 다형성
## 상속과 클래스의 계층
### 하위 클래스 선언하기
```
import org.intellij.lang.annotations.Language

// 상속 가능한 클래스가 되려면 open 사용하기
open class Bird(var name: String, var wing:Int, var beak: String, var color:String){
    fun fly() = println("fly wing: $wing")
    fun sing(vol:Int) = println("Sing vol: $vol")
}
// 주 생성자를 사용하는 상속
class Lark(name:String, wing:Int, beak:String, color:String):Bird(name, wing, beak, color){ //Bird로 부터 상속을 받는다
    fun singHitone() = println("happy song!") // 메서드 추가
}
// 부 생성자를 사용하는 상속
class Parrot:Bird{
    val language: String // 새로 추가된 프로퍼티
    constructor(name:String, wing:Int, beak:String, color:String, language: String):super(name,wing,beak,color){ //기반 클래스 생성자 호출
        this.language = language // 부생성자에서 초기화
    }
    fun speak() = println("speak! $language")
}
fun main() {
    val coco = Bird("mybird", 2, "short", "blue")
    val lark = Lark("mylark", 2, "long", "brown")
    val parrot = Parrot("myparrot", 2, "short", "multiple", "korean") // 프로퍼티가 추가됨

    println("Coco: ${coco.name}, ${coco.wing}, ${coco.beak}, ${coco.color}")
    println("Lark: ${lark.name}, ${lark.wing}, ${lark.beak}, ${lark.color}")
    println("Parrot: ${parrot.name}, ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language}")

    lark.singHitone()  // 새로 추가된 메서드가 사용 가능
    parrot.speak()
    lark.fly()
}
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/d91a08be-fc0c-4002-a8b3-d7acf4f975d0" />


### Overriding (오버라이딩)

```
import org.intellij.lang.annotations.Language

// 상속 가능한 클래스가 되려면 open 사용하기
open class Bird(var name: String, var wing:Int, var beak: String, var color:String){
    fun fly() = println("fly wing: $wing")
    open fun sing(vol:Int) = println("Sing vol: $vol") //open추가 -> 오버라이딩 가능한 메서드
}
// 주 생성자를 사용하는 상속
class Lark(name:String, wing:Int, beak:String, color:String):Bird(name, wing, beak, color){ //Bird로 부터 상속을 받는다
    fun singHitone() = println("happy song!") // 메서드 추가
}
// 부 생성자를 사용하는 상속
class Parrot:Bird{
    val language: String // 새로 추가된 프로퍼티
    constructor(name:String, wing:Int, beak:String, color:String, language: String):super(name,wing,beak,color){ //기반 클래스 생성자 호출
        this.language = language // 부생성자에서 초기화
    }
    fun speak() = println("speak! $language") 
    override fun sing(vol:Int) { //오버라이딩 한다고 써줘야 한다
        println("I'm a parrot! The volume level is $vol")
        speak()
    }
}
fun main() {
    val coco = Bird("mybird", 2, "short", "blue")
    val lark = Lark("mylark", 2, "long", "brown")
    val parrot = Parrot("myparrot", 2, "short", "multiple", "korean") // 프로퍼티가 추가됨

    println("Coco: ${coco.name}, ${coco.wing}, ${coco.beak}, ${coco.color}")
    println("Lark: ${lark.name}, ${lark.wing}, ${lark.beak}, ${lark.color}")
    println("Parrot: ${parrot.name}, ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language}")

    lark.singHitone()  // 새로 추가된 메서드가 사용 가능
    parrot.speak()
    lark.fly()
}
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/e07706e6-cefd-4bf1-bb08-c6a42b7f585e" />

## super와 this의 참조

### super로 상위 객체 참조하기

```
import org.intellij.lang.annotations.Language

// 상속 가능한 클래스가 되려면 open 사용하기
open class Bird(var name: String, var wing:Int, var beak: String, var color:String){
    fun fly() = println("fly wing: $wing")
    open fun sing(vol:Int) = println("Sing vol: $vol") //open추가 -> 오버라이딩 가능한 메서드
}
// 주 생성자를 사용하는 상속
class Lark(name:String, wing:Int, beak:String, color:String):Bird(name, wing, beak, color){ //Bird로 부터 상속을 받는다
    fun singHitone() = println("happy song!") // 메서드 추가
}
// 부 생성자를 사용하는 상속
class Parrot:Bird{
    val language: String // 새로 추가된 프로퍼티
    constructor(name:String, wing:Int, beak:String, color:String, language: String):super(name,wing,beak,color){ //기반 클래스 생성자 호출
        this.language = language // 부생성자에서 초기화
    }
    fun speak() = println("speak! $language")
    override fun sing(vol:Int) { //오버라이딩 한다고 써줘야 한다
        super.sing(vol)
        println("I'm a parrot! The volume level is $vol")
        speak()
    }
}
fun main() {
    val coco = Bird("mybird", 2, "short", "blue")
    val lark = Lark("mylark", 2, "long", "brown")
    val parrot = Parrot("myparrot", 2, "short", "multiple", "korean") // 프로퍼티가 추가됨

    println("Coco: ${coco.name}, ${coco.wing}, ${coco.beak}, ${coco.color}")
    println("Lark: ${lark.name}, ${lark.wing}, ${lark.beak}, ${lark.color}")
    println("Parrot: ${parrot.name}, ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language}")

    lark.singHitone()  // 새로 추가된 메서드가 사용 가능
    parrot.speak()
    lark.fly()
}
```

##  this로 현재 객체 참조하기

### 여러 개의 부 생성자에서 참조하기

```
open class Person {
    constructor(firstName: String) {
        println("[Person] firstName: $firstName")
    }
    constructor(firstName: String, age: Int) { // ③
        println("[Person] firstName: $firstName, $age")
    }
}
class Developer: Person {
    constructor(firstName: String): this(firstName, 10) { // ①
        println("[Developer] $firstName")
    }
    constructor(firstName: String, age: Int): super(firstName, age) { // ②
        println("[Developer] $firstName, $age")
    }
}
fun main() {
    val sean = Developer("Sean")
}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/c1616407-381f-4f9e-8a5a-f22082caebf2" />




### 주 생성자와 부 생성자를 함께 사용 

```
//open class Person {
//    constructor(firstName: String) {
//        println("[Person] firstName: $firstName")
//    }
//    constructor(firstName: String, age: Int) { // ③
//        println("[Person] firstName: $firstName, $age")
//    }
//}
//class Developer: Person {
//    constructor(firstName: String): this(firstName, 10) { // ①
//        println("[Developer] $firstName")
//    }
//    constructor(firstName: String, age: Int): super(firstName, age) { // ②
//        println("[Developer] $firstName, $age")
//    }
//}
//fun main() {
//    val sean = Developer("Sean")
//}

class Person(firstName: String, out: Unit = println("[Primary Constructor] Parameter")){ // ② 주 생성자
    val fName = println("[Property] Person fName: $firstName") // ③ 프로퍼티 할당

    init {
        println("[init] Person init block") // ④ 초기화 블록
    }

    // ① 보조 생성자
    constructor(firstName: String, age: Int, out: Unit = println("[Secondary Constructor] Parameter")): this(firstName){
        println("[Secondary Constructor] Body: $firstName, $age") // ⑤ 부 생성자 본문
    }
}

fun main() {
    val p1 = Person("Kildong", 30) // ①→② 호출, ③→④→⑤ 실행
    println()
    val p2 = Person("Dooly") // ② 호출, ③→④ 실행
}
```

<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/86bbf31e-f524-4c0b-ab18-a41d4f821cda" />

## 인터페이스에서 참조하기

### Interface(인터페이스)

```
open class A{
    open fun f() = println("A Class f()")
    fun a() = println("A Class a()")
}
//인터페이스는 기본적으로 open
interface B{
    fun f() = println("B Class f()")
    fun b() = println("B Class b()")
}
class C:A(), B{
    override fun f() = println("C Class f()")
    fun test() {
        f()
        b()
    }
}
fun main() {
    val c = C()
    c.test()
}
```

<img width="1919" height="1027" alt="image" src="https://github.com/user-attachments/assets/00fe309f-ff7a-4e14-a1a5-ce71b1a1a6e5" />

### 앵클브래킷 <> 사용하여 구분하기 

```
open class A{
    open fun f() = println("A Class f()")
    fun a() = println("A Class a()")
}
//인터페이스는 기본적으로 open
interface B{
    fun f() = println("B Class f()")
    fun b() = println("B Class b()")
}
class C:A(), B{
    override fun f() = println("C Class f()")
    fun test() {
        f()
        b()
        super<A>.f() // <A>가 없으면 왜 에러가 나나? 어떠한 super함수인지 지정을 안해서...
        super<B>.f()
    }
}
fun main() {
    val c = C()
    c.test()
}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/5e303605-c196-46c3-a289-ab8dfcdcb2cc" />

# 캡슐화

## 정보 은닉 캡슐화

### 가시성 지시자 (visibility modifiers)

### private 가시성 테스트 

```
private class PrivateCalss{
    var i = 1
    private var j = 2
    private fun privateFunc() {
        j+=1
        println(j)
    }
    fun access() {
        privateFunc()
    }
}
class Other{
    //val pc = PrivateCalss() // 이렇게는 못 만듬
    private val pc = PrivateCalss()
}
fun main() {
    val pc = PrivateCalss() // 생성가능
    println(pc.i)
    // println(pc.j) // 출력이 안됨(접근이 안됨) // private 멤버 접근 불가
    // pc.privateFunc() // private 멤버 접근 불가
    pc.access() // 접근 가능
}
```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/67918d21-92ba-4318-9c6a-9e6bbcb1e3fc" />

### protected 가시성 테스트

```
open class Base{
    protected var i =1
    fun protectedFun(){
        i+=2
    }
    fun access(){
        protectedFun()
    }
}
class Derived : Base() {
    fun test(base: Base):Int{
        protectedFun()
        return i
    }
}
fun main() {
    val base = Base()
    //base.i // 접근불가
    //base.protectedFunc()
    base.access()
}
```

## 클래스와 클래스의 관계

### UML의 가시성 표기 기호

#### 자동차와 도둑의 예제

```

```

