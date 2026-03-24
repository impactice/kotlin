fun main() {
    val number = 4
    val result: Long

    //result = factorial(number)
    result = factorial1(number)
    println("Factorial : $number -> $result")
}

fun factorialwhile(n: Int): Long {  //while 추가
    var number = n.toLong()
    var result: Long = 1
    while (number > 0) {
        result *= number
        --number
    }
    return result
}

fun factorial(n: Int): Long {
    return if (n == 1) n.toLong() else n * factorial(n - 1)
}

tailrec fun factorial1(n: Int, run: Int = 1): Long {
    return if (n == 1) run.toLong() else factorial1(n - 1, run * n)
}