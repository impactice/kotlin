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