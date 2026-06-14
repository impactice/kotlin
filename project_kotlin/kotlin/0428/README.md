
# 위임과 지연 초기화

## 2. 지연 초기화 

### 지연 초기화가 필요한 이유 

### lateinit을 사용한 지연 초기화

```
// 작년에 초기화 되는 곳 시험에 나옴
class Person{
    //var name: String = "" // 초기화
    lateinit var name: String // 이렇게 하면 초기화 하지 않고 넘어감 // 지연 초기화를 위한 선언
    fun test() {
        if(!::name.isInitialized) { // 프로티의 초기화 여부를 판단
            println("not initialized")
        }else{
            println("initialized")
        }
    }
}
fun main() {
    val p1 = Person() // name 프로퍼티는 여기서 초기화되지 않음
    p1.test()
    p1.name = "Pororo" //name 프로퍼티가 초기화 되는 시점
    p1.test()
    println("${p1.name}")
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/80a6a4a9-cb08-4ba7-b18a-178bb44c2c9b" />

### lazy를 사용한 지연 초기화

### lazy를 사용한 프로퍼티 지연 초기화

```
class LazyTest {
    init {
        println("init block")
    }
    val subject by lazy{
        println("lazy intialized")
        "Kotlin programming"
    }
}

fun main() {
    val test = LazyTest()
    println(test.subject)
}
```

<img width="1919" height="1035" alt="image" src="https://github.com/user-attachments/assets/10b62c8a-dfb7-4a8f-8579-842e4c68ba2d" />

```
class LazyTest {
    init {
        println("init block")
    }
    val subject by lazy{
        println("lazy intialized")
        "Kotlin programming"
    }
    fun flow() {
        println("not initialized")
        println(subject)
    }
}

fun main() {
    val test = LazyTest()
    test.flow()
    println(test.subject)
}
```


<img width="1919" height="1027" alt="image" src="https://github.com/user-attachments/assets/090d7de4-d2a9-4458-960e-83b3b2a7d96b" />

```
class Person2(var name: String, var age: Int)

lateinit var p1 : Person2 // 객체 생성의 지연 초기화, 생성자를 사용하지 않음

fun main() {
    var isPerson : Boolean = false
    p1 = Person2("John",18) // 생성자 호출 시점에서 초기화

    val p2 : Person2 by lazy {
        isPerson = true
        Person2("Hong",18)
    }

    println(isPerson)
    println("p2.name = ${p2.name}, p2.age = ${p2.age}")
    println(isPerson)
}
```


<img width="1919" height="1035" alt="image" src="https://github.com/user-attachments/assets/562e1ebf-5f7a-445a-8df0-247f0bd5e36c" />

### 객체 지연 초기화

```
class Person2(var name: String, var age: Int)

lateinit var p1 : Person2 // 객체 생성의 지연 초기화, 생성자를 사용하지 않음

fun main() {
    var isPerson : Boolean = false
    p1 = Person2("John",18) // 생성자 호출 시점에서 초기화

    val p2 : Person2 by lazy {
        isPerson = true
        Person2("Hong",18)
    }

    val personDelegate = lazy{
        Person2("Kildong",20)
    }

    println(isPerson)
    println("p2.name = ${p2.name}, p2.age = ${p2.age}")
    println(isPerson)

    println("personDelegate Init: ${personDelegate.isInitialized()}")
    println(personDelegate.value.name)
    println("personDelegate Init: ${personDelegate.isInitialized()}")
}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/e0cba8f8-958f-4058-8156-9c34c1738c9d" />

### 지연초기화를 하는 이유 

## by를 이용한 위임

### 위임(delegation)

### 클래스의 위임 

```
interface Car {
    fun go(): String
}
class VanImpl(val power: String): Car {
    override fun go() = "는 짐을 적재하며 $power 마력을 가집니다."
}
class SportImpl(val power: String): Car {
    override fun go() = "는 경주용에 사용되며 $power 마력을 가집니다."
}
class CarModel(val model: String, impl: Car): Car by impl {
    fun carInfo() {
        println("$model ${go()}") // ① 참조 없이 각 인터페이스 구현 클래스의 go를 접근
    }
}
fun main() {
    val myDamas = CarModel("Damas 2010", VanImpl("100마력"))
    val my350z = CarModel("350Z 2008", SportImpl("350마력"))
    
    myDamas.carInfo() // ② carInfo에 대한 다형성을 나타냄
    my350z.carInfo()
}
```

<img width="1919" height="1036" alt="image" src="https://github.com/user-attachments/assets/5425e806-20e9-4c66-a849-108a29932a25" />

### 프로퍼티 위임과 by lazy 

### observable() 함수와 vetoable() 함수의 위임

# 정적 변수와 컴패니언 객체 

## 3. 정적 변수와 컴패니언 객체

### 정적 변수와 컴패니언 객체

### 컴패니언 객체 사용하기


### 코틀린에서 자바의 static 멤버 사용하기

```
public class Customer {
    public static final String LEVEL = "BASIC";  // static 필드
    public static void login() { // static 메서드
        System.out.println("Login...");
    }
}
```

```
fun main() {
    println(Customer.LEVEL)
    Customer.login()
}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/2ce0373c-853f-4e84-b827-6972b0bc26c8" />


### 자바에서 코틀린 컴패니언 객체 사용하기

```
class KCustomer {
    companion object{
        const val LEVEL = "INTERMEDIATE"
        //@JvmField
        var test:Int = 0
        @JvmStatic
        fun login() = println("Login...")
    }
}
```

```
class KCustomerAccess {
    public static void main(String[] args) {
        System.out.println(KCustomer.LEVEL);
        KCustomer.login();

        //@JvmStatic 에너테이션을 사용하지 않을 때 접근방법
        KCustomer.Companion.login();
        KCustomer.Companion.setTest(100);
        System.out.println(KCustomer.Companion.getTest());
    }
}
```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/9d934c9d-39a7-4544-8813-3b917c21e132" />


## 최상위 함수 사용하기

### 최상위 함수(top-level function)

### 자바에서 코틀린의 최상위 함수 접근


## object와 싱글톤

### object 선언 

### Java static vs Kotlin object의 결정적 차이

### object 표현식






