package section1

fun main() {
    //println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
    //printSum(a=5,b=3)
    //add(name="정문주", "mjlunar@ks.ac.kr")
    //add(email = "눈덮인숲속마을",name="뽀로로")
    // 직접 값 나열
    printNumbers(1, 2, 3)
    // 배열을 펼쳐서 전달
    val numbers = intArrayOf(1, 2, 3, 4)
    printNumbers(*numbers)

}
fun printNumbers(vararg counts:Int) {
    for(n in counts) {
        print("$n ")
    }
}
// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b

fun printSum(a: Int, b: Int) {
    //println("sum of $a and $b is ${a+b}")
}

fun add(name:String, email:String = "이메일없음") {
    //println("name : $name, email : $email")
}