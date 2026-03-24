fun main() {
    for(i in 1..5) println(i) // 상행 반복
    //for(i in 5..1) println(i) // 이거는 안됨
    for(i in 5 downTo 1) println(i) //하행 반복
    for(i in 1..5 step 2) println(i) //건너 뛰어 반복
}