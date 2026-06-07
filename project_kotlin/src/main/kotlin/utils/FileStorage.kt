package utils

import models.Spaceship
import java.io.File
import java.io.FileNotFoundException

object FileStorage {
    private const val FILE_NAME = "save.txt"

    fun save(spaceship: Spaceship) {
        try {
            File(FILE_NAME).bufferedWriter().use { writer ->
                writer.write("${spaceship.name},${spaceship.fuel},${spaceship.minerals}")
            }
        } catch (e: Exception) {
            println("저장 중 오류 발생: ${e.message}")
        }
    }

    fun load(): Map<String, Any>? {
        return try {
            val file = File(FILE_NAME)
            if (!file.exists()) throw FileNotFoundException("세이브 파일이 없습니다.")
            
            file.bufferedReader().use { reader ->
                val line = reader.readLine() ?: return null
                val parts = line.split(",")
                mapOf(
                    "name" to parts[0],
                    "fuel" to parts[1].toInt(),
                    "minerals" to parts[2].toInt()
                )
            }
        } catch (e: FileNotFoundException) {
            println("불러오기 실패: ${e.message}")
            null
        } catch (e: NumberFormatException) {
            println("데이터 형식이 잘못되었습니다.")
            null
        }
    }
}
