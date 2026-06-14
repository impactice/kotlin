# 13주. 파일 입출력과 DSL

## Part 1. 파일 입출력 — 표준 입출력과 파일 쓰기

### 1-1. 표준 입출력의 기본

### 1-2. Kotlin의 파일 입출력 API

### 1-3. 파일에 쓰기 — Files.write()
```
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main() {
    val path = "hello.txt"
    val text = "Hello World!\n"

    try{
        Files.write(Paths.get(path), text.toByteArray(), StandardOpenOption.CREATE)
    }catch (e: IOException){
        println("error: ${e.message}")
    }
}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/c8b0bc20-c778-4629-b39e-56cd65f4cde8" />

### 1-4. 파일에 쓰기 — PrintWriter / BufferedWriter
```
import java.io.File
import java.io.FileWriter
import java.io.PipedWriter
import java.io.PrintWriter

fun main() {
    val path = "testfile.txt"
    val outString = "안녕하세요! \t Hello \r world!"
    val file = File(path)
    val printWriter = PrintWriter(file)
    printWriter.println(outString) // 파일에 출력
    printWriter.close() //반드시 닫아야 함
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/7d3a8292-23f9-4ff9-8a60-4628bf3db7a2" />


```
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
}

```
<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/8edcded9-5920-4e29-9448-3e043f6ed197" />

### 1-5. writeText() / appendText() — 가장 간단한 방법
```
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

```

### 실습 1
```
import java.io.File

fun main() {
    print("이름 : ")
    val name = readLine() ?: "익명"
    print("메모 :")
    val memo = readLine() ?: ""

    val file = File("memo.txt")
    file.writeText("작성자 : $name\n내용: $memo\n")
    println("메모가 저장되었습니다.")
}
```
<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/f8cc2bcf-da5c-4222-b61a-483c7d995d32" />

## Part 2. 파일에서 읽기 + Kotlin 파일 API 정리

### 2-1. `FileReader` + `readText()`로 파일 읽기

```
import java.io.File
import java.io.FileReader

fun main() {
    print("이름 : ")
    val name = readLine() ?: "익명"
    print("메모 :")
    val memo = readLine() ?: ""

    val file = File("memo.txt")
    file.writeText("작성자 : $name\n내용: $memo\n")
    println("메모가 저장되었습니다.")

    // 파일 읽기 -> 화면에 출력
    val read = FileReader(file)
    println(read.readText())
}
```

<img width="1919" height="1025" alt="image" src="https://github.com/user-attachments/assets/fc846750-6075-42bc-925c-4a12ad6a6f52" />


```
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
```




```
data class Person(
    var name: String? = null,
    var age: Int? = null,
    var jab: Job? = null
)
data class Job(
    var category: String? = null,
    var position: String? = null,
    var extension: String? = null
)
fun person(block:(Person)->Unit):Person{
    val p = Person()
    block(p)
    return p
}

fun main() {
    val person = person {
        it.name = "길동"
        it.age = 30
    }
    println(person)
}
```

