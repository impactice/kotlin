package section3

fun main() {
    val x: Any
    x = "Hello"
    if(x is String) {
        println(x.length)
    }
    val num =256
    if(num is Int){
        println(num)
    }else if(num !is Int){
        println("Not al Int")
    }
}