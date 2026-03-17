package section2

fun main() {
    var add = highOrder({x,y->x+y}, 10, 20)
    var mul = highOrder({x,y->x*y}, 10, 20)

    /*
    var result : Int
    val multi = {x:Int,y:Int->x*y} //일반 변수에 람다식 할당 //val multi:(Int,Int) -> Int = {x:Int,y:Int->x*y} // val multi:(Int,Int) -> Int = {x,y->x*y} // 저거 다 됨
    val multi2:(Int,Int) -> Int = {x,y->
        println("x * y")
        x*y
    }
    result = multi2(2,6) // 람다식이 할당된 변수는 함수처럼 사용가능
    println(result)
    val greet: ()-> Unit = {println("Hello World!")} //val greet = {println("Hello World!")} // 이거 가능
    greet()

    val square:(Int) -> Int = {x:Int -> x * x}
    val nestedLambda:() -> () -> Unit = {{println("hihi")}} */

//    val res1 = sum(3, 2)  // 일반 인자
//    // 인자에 함수 호출 결과값 사용
//    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
//    println("res1: $res1, res2: $res2")
//    println("funcFunc: ${funcFunc()}")
}

fun highOrder(sum:(Int,Int)->Int, a:Int, b:Int):Int {
    return sum(a, b)
}
fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b

fun funcFunc(): Int { // 함수의 반환값으로 함수 호출 결과값 사용
    return sum(2, 2)
}