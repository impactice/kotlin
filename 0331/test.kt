// 2021963066 정원희입니다

// 실습1
//fun main() {
//    // 여기에 라벨을 정의하고 루프를 작성하세요.
//    outer@for (i in 1..10) {
//        for (j in 1..10) {
//            if (i * j == 20) {
//                // 라벨을 사용하여 전체 루프 탈출
//                break@outer
//            }
//            println("i: $i, j: $j, product: ${i * j}")
//        }
//    }
//    println("시스템을 종료합니다")
//}

// 실습 2

//fun calculateGrade(score: Int): String {
//    if (score !in 0..100) {
//        // 예외 던지기 구현
//        if (score > 100) {
//            throw IllegalArgumentException("점수는 0~100 사이")
//        }
//    }
//    return when {
//        score >= 90 -> "A"
//        score >= 80 -> "B"
//        else -> "C"
//    }
//}
//
//fun main() {
//    val inputScore = 150 // 테스트용 점수
//
//    // try-catch를 식(expression)으로 활용하여 결과 저장
//    val result = try {
//        calculateGrade(inputScore)
//    }catch (ex: IllegalArgumentException) {
//        "ERROR"
//    }
//
//        println("결과: $result")
//}

// 실습 3
// 클래스 설계
class Student(val name: String, val id: Int) {
    init {
        println("학생 [$name] 등록 완료")
    }

    constructor(name: String, id: Int, major: String) : this(name, id) {
        println("전공: $major")
    }

    fun displayInfo() {
        println("학번: $id, 이름: $name")
    }
}

fun main() {
    // 1. 주 생성자로 객체 생성
    val s1 = Student("홍길동", 2024001)
    s1.displayInfo()

    // 2. 부 생성자로 객체 생성 (전공: 컴퓨터공학)
    val s2 = Student("임꺽정", 2024002, "컴퓨터공학")
    s2.displayInfo()
}
