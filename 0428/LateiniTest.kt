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

//data class Person2(var name: String, var age: Int)
//lateinit val p2 = Person2()

fun main() {
    //p2 = Person2("kildong",30)
    val p1 = Person() // name 프로퍼티는 여기서 초기화되지 않음
    p1.test()
    p1.name = "Pororo" //name 프로퍼티가 초기화 되는 시점
    p1.test()
    println("${p1.name}")
}