import java.io.File
import java.io.FileReader

fun main() {
    print("이름 : ")
    val name = readLine() ?: "익명"
    print("메모 :")
    val memo = readLine() ?: ""

    try {
        val file = File("memo.txt")
        file.writeText("작성자 : $name\n내용: $memo\n")
        println("메모가 저장되었습니다.")

        // 파일 읽기 -> 화면에 출력
        //(1) 전체 텍스트를 한 번에 읽기
        val read = FileReader("hoho.txt")
        println(read.readText())
        //(2) 줄 단위로 읽어 리스트에 저장
        val lines = File("hoho.txt").readLines()
        lines.forEachIndexed { index, string -> println("${index+1}: $string") }
    }catch (e: Exception){
        println(e.message)
    }
}