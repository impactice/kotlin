class LazyTest {
    init {
        println("init block")
    }
    val subject by lazy{
        println("lazy intialized")
        "Kotlin programming" // lazy함수의 반환값: 이 반환값이 지연 초기화가 된다
    }
    fun flow() {
        println("not initialized")
        println(subject) // 최초 초기화
    }
}

fun main() {
    val test = LazyTest()
    test.flow()
    println(test.subject) //초기화된 값을 사용
}