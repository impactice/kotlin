class Person3{
    var id:Int = 0
    var name:String = "Youngdeok"
    companion object{
        var language: String = "Korean"
        fun work() {
            println("working...")
        }
    }
}
fun main() {
    // 인스턴스를 생성하지 않고 기본 값 사용
    println(Person3.language)
    Person3.work()
    Person3.language = "English"
    println(Person3.language)
    //인스턴스 생성
    val p3 = Person3()
    println(p3.name)
}