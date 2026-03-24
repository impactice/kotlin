// 나이에 따른 입장료 계산하기
// 2세미만 없음 2~13세 1000원 14~19세 2000원 20세이상 2500원

fun main() {
//if문 사용
     // 내가 작성한거
    println("나이를 입력해주세요: ")
    val old = readln().toInt()
    if (old < 2) {
        println("입장료 없음")
    } else if (old in 2..13) {
        println("입장료 1000원")
    } else if (old in 14..19) {
        println("입장료 2000원")
    } else {
        println("입장료 2500원")
    }


//when문 사용

    var age = readLine()!!.toInt()
    var result = when { // 인자가 없는 when문
        age <2 -> 0
        age in 2..13->1000
        age in 14..19->2000
        else -> 2500
    }
    val price = when(age) { // 인자가 있는 when문
        in 2..13->1000
        in 14..19->2000
        in 20..Int.MAX_VALUE->2500
        else -> {
            println("돌 전엔 공짜")
            //0 // 이거는 왜 적은 거지?
        }
    }
    println(price)
}

