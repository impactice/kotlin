inline fun shortFunc(a:Int, crossinline out:(Int)->Unit){ //crossinline은 비지역 반환을 예방
    println("Before calling out()")
    out(a)
    println("After calling out()")
} //

// String을 확장해 getLongString 추가
fun String.getLongString(target: String): String =
    if (this.length > target.length) this else target

fun main() {
    val str1 = "Hello"
    val str2 = "World"
//    shortFunc(3) {
//        if (it == 3) {
//            println("First call : $it")
//            return@shortFunc // 만약 return을 하고 싶으면 이렇게 쓰면 됨
//        }
//        else
//            println("HOHOHO")
//    }
}