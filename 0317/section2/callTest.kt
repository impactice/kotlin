package section2

fun add(x:Int, y:Int) = x + y

fun funcParam(a:Int, b:Int, c:(Int,Int)->Int): Int{
    return c(a,b)
}
fun main() {
    //println(funcParam(1,2,add)) // 이거는 안됨 add가 람다식이 아니라서
    println(funcParam(1,2,{a,b -> add(a,b)}))
    println(funcParam(1,2,:: add)) // 이거는 된다
}
