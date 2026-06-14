fun main(){
    val list = listOf(1, 2, 3, 4, 5)

// 즉시 평가: map → [1, 4, 9, 16, 25] 리스트 생성 → filter → [4, 16] 리스트 생성
// 중간 리스트가 2개 만들어진다
    val result = list
        .map { println("map($it)"); it * it }       // ① 5개 모두 map 처리
        .filter { println("filter($it)"); it % 2 == 0 } // ② 5개 모두 filter 처리
    println(result)

    val listSeq = list.asSequence().map{println("map($it)"); it * it}
        .filter { println("filter($it)"); it % 2 == 0 }
}