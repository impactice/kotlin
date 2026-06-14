package section2

data class Customer (var name:String, var email:String) {
    var job:String = "Unknown"
    constructor(name:String, email:String, _job:String) : this(name,email) {
        job = _job
    }
    init{
        println("data class")
    }
}
// equals(), hashCode(), copy(), toString(), componentN() 이것들은 데이터 클래스가 자동 생성하는 메서드이다.

fun main() {
    val cus1 = Customer("John","john@mail.com")
    val cus2 = Customer("Sean","sean@mail.com")
    val cus3 = Customer("John","john@mail.com")
    println(cus1 == cus2)
    println(cus2 == cus3)
    println(cus2.equals(cus3))
    println("${cus2.hashCode()}, ${cus3.hashCode()}")
    val cus4 = cus1.copy("Jack")
    println(cus1.toString())
    println(cus4.toString())

    //반복문을 이용한 모든 객체의 프로퍼티 분해
    val customers = listOf(cus1, cus2, cus3,cus4)
    for((name, email) in customers) {
        println("name = $name, email = $email")
    }
    //함수 반환 값을 분해 저장
    val (myName, myEmail) = myFunc()
    println("$myName,$myEmail")

    //람다식에서 사용하기
    val myLambda = {
        (numeLa,emilLa): Customer ->
        println(numeLa)
        println(emilLa)
    }
    myLambda(cus4)
}
fun myFunc():Customer{
    return Customer("Mickey","mic@mail.com")
}