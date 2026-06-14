fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val listWithNull = listOf(1, null, 3, null, 5, 6)

    //map : 각 요소를 변환, 원본과 같은 개수의 리스트를 반환
    val doubled = list.map{it*2}
    println(doubled)
    val withIndex = list.mapIndexed {i,v -> i * v}
    println(withIndex)
    println(listWithNull.mapNotNull {it?.times(2)})

    val list2 = list.groupBy { if(it%2==0) "even" else "odd"}
    println(list2)

    // 문자열 첫 글자로 그룹화
    val fruits = listOf("apple", "apricot", "banana", "blueberry", "cherry")
    println(fruits.groupBy { it.first() })
}