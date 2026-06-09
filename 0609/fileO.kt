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