package section3

fun main() {
    checkArg(x="안녕하세요")
    checkArg(5555)
}
fun checkArg(x: Any) {
    if(x is String)
        println("x is String : $x")
    if(x is Int)
        println("x is Int : ${x}")
}