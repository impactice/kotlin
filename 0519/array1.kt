package section3
import java.util.Arrays

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)
    val arr2d = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))

    // 1차원 배열 출력
    println(Arrays.toString(arr))

    // 2차원 배열 출력
    //println(Arrays.deepToString(arr2d))
    println(Arrays.toString(arr.sortedArray()))
    println(Arrays.toString(arr.sortedArrayDescending()))
    println(Arrays.toString(arr)) //원본 변화 없음

    //arr.sort()
    arr.sortDescending()
    println(Arrays.toString(arr))

    
}
