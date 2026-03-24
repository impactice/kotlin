fun main() {
    //일반 표현법
    var num = 5.muliply(10)
    println(num)
    //중위 표현법
    num = 5 muliply 100
    println(num)

    val deadLine = 3 days "later"
    println(deadLine)
}

// int 확장함수 만들기
infix fun Int.muliply(x: Int): Int {
    return this * x
}

infix fun Int.days(unit: String): Long {
    return if (unit == "ago") {
        System.currentTimeMillis() - (this * 24 * 60 * 60 * 1000L)
    } else {
        System.currentTimeMillis() + (this * 24 * 60 * 60 * 1000L)
    }
}
