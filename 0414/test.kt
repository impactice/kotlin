open class Electronics(val name: String) {
    open fun powerOn() {
        println("$name 의 전원이 켜졌습니다.")
    }
}

// 여기에 Notebook 클래스를 구현하세요.
class Notebook(val notebookName: String, val cpu:String) : Electronics("Notebook") {
    override fun powerOn() {
        println("노트북 전원을 켭니다.")
        super.powerOn()
    }
}

fun main() {
    val myLaptop = Notebook("Gram", "i7")
    myLaptop.powerOn()
}



// 문제 2번
class Account(val accountNumber: String) {
    var balance: Int = 0
        set (value) {
    if (value < 0) {
        field = 0
    } else {
        field = value
    }
}
}

//문제 3번 (설명하고 끝 -> 저걸 사용할려면 뭘 해야 하는 지 2개)
// 1. performOp 고차 함수를 작성하세요.
fun performOp(a: Int, b: Int, operation: (Int, Int) -> Int) {
    operation(a, b)
}

fun main() {
    // 2. performOp를 호출하여 10 * 20의 결과를 출력하세요.
    println(performOp(10,20,{x, y -> x * y}))
}

// 문제 4번
class Engine(val power: String) {
    fun start() = println("엔진 가동: $power")
}

class Car(val model: String, power: String) {
    private val engine = Engine(power) // 구성(Composition) 관계

    fun drive() {
        engine.start()
        println("$model 이 달립니다.")
    }
}

fun main() {
    val myCar = Car("Tesla", "200hp")
    myCar.drive()
    // println(myCar.engine.power) // (질문) 이 줄에서 에러가 발생하는 이유는?
}

// 5번

// 여기에 Student 클래스 전체를 작성하세요.
class Student(val name: String, _score: Int) {
    var score: Int = _score
        set (value) {
            if (value in 0..100) field = value
            else println("유효하지 않은 점수입니다.")
        }
    val isPassed: Boolean
        get() = _score >= 60
}
fun main() {
    // 테스트 코드 작성
    val s = Student("김철수",60)
    println("${s.name} 합격 여부 : ${s.isPassed}")
    s.score = 90
    println("${s.name} 합격 여부 : ${s.isPassed}")
    s.score = 120
}