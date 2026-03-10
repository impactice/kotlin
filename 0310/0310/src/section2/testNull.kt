package section2

fun main() {
    var str1:String? = "Hello Kotlin"
    str1 = null
    //?. : null이 아니면 연산을 수행하고 null이면 그냥 null반환
    println("str1 : $str1 \t length : ${str1?.length?:-1}")
//    if(str1 != null)  // 이 4줄 자리 코드를 축약한 한 1줄이 위에 꺼
//        println("str1 : $str1 \t length : ${str1?.length}")
//    else
//        println("str1 : null")
    //println("str1 : $str1 \t length : ${str1!!.length}") //!!는 str1은 null일 리가 없다고 선언하는 거라서 오류가 뜸
}