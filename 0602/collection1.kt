import java.util.*;

fun main(){
    //학생 성적을 가변 map으로 만들기
    val scores: MutableMap<String, Int> = mutableMapOf(
        "Alice" to 85,
        "Bob" to 92,
        "Charlie" to 78,
    )
    println(scores)
    //학생 추가
    scores["David"] = 88
    println(scores)
    // 점수 수정
    scores["Alice"] = 58
    println(scores)
    //모든 학생의 이름과 성적 출력
    for((name, score) in scores)
        println("$name : $scores")
    //성적이 80점 이상 학생을 골라 set으로 저장 후 출력
    val highScores:Set<String> =scores.filter{it.value >= 80}.keys.toSet()
    println("80점 이상 : $highScores")

    // [문제 2] 중복 학번이 있는 리스트에서 유일한 학번만 남기시오
    val studentIds = listOf(1001, 1002, 1001, 1003, 1002, 1004)
    val uniqueIds: Set<Int> = studentIds.toSet()
    println("유일한 학번: $uniqueIds")
    println("중복 제거 후 개수: ${uniqueIds.size}")

    studentIds.forEach{ print("$it") }
    println()

    studentIds.forEachIndexed { i, v -> println("[$i] = $v")  }

    val result = studentIds.onEach{print("처리 전 : $it")}.filter{ it%2==0}
    println(result)
    println(studentIds)
}