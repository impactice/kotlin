import java.io.File
import java.io.PrintWriter

fun main() {
    val path = "testfile.txt"
    val outString = "안녕하세요! \t Hello \r world!^^"
    val file = File(path)
    val printWriter = PrintWriter(file)
    //printWriter.println(outString) // 파일에 출력
    //printWriter.close() //반드시 닫아야 함

    //use 사용하기
    printWriter.use { it.print(outString) }

    /*
    val file= File("output.txt")
    file.writeText("Hello Kotlin!")         // 파일 전체를 덮어쓰기
    file.appendText("\\nDo great work!")     // 기존 내용 뒤에 추가
    */
}
