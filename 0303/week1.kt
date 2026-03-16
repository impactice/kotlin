fun main() {
    val studentName: String? = "정원희"
    var score: Int = 100
    score = 95
    println("${studentName}님의 점수는 ${score}점입니다.")  // println("$studentName 님의 점수는 $score 점입니다")
    println("이름 길이: ${studentName?.length ?:0}")
}