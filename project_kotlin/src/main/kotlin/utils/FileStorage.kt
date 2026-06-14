package utils

import models.Spaceship
import java.io.File
import java.io.FileNotFoundException

/**
 * [개념 적용 ⑤: 사용자 정의 예외(Custom Exception)]
 * 세이브 파일의 데이터가 중간에 끊겨있거나 형식이 손상되었을 때 발생시키는 예외입니다.
 */
class CorruptedSaveException(message: String) : Exception(message)

/**
 * 게임 데이터를 디스크에 물리적으로 저장하고 불러오는 역할을 하는 싱글톤 객체입니다.
 */
object FileStorage {
    private const val FILE_NAME = "save.txt"

    /**
     * 현재 상태(함선 정보, 인벤토리, 발견한 행성 목록)를 텍스트 파일로 저장합니다.
     */
    fun save(spaceship: Spaceship, discoveredPlanets: Set<String>) {
        try {
            // [개념 적용 ④: 파일 입출력 및 use 스코프 함수 활용]
            // bufferedWriter() 스트림을 열고, 블록이 끝나면 안전하게 자동으로 close() 해줍니다. (메모리 누수 방지)
            File(FILE_NAME).bufferedWriter().use { writer ->
                
                // 1. 기본 정보 쓰기 (쉼표로 구분)
                writer.write("${spaceship.name},${spaceship.shipClass.name},${spaceship.fuel},${spaceship.minerals},${spaceship.hp},${spaceship.maxHp},${spaceship.attackPower},${spaceship.level},${spaceship.exp}\n")
                
                // 2. 인벤토리 쓰기 (Map의 데이터를 문자열 형태로 변환)
                // 예: "Repair Kit:2;Fuel Cell:1"
                val invStr = spaceship.inventory.entries.joinToString(";") { "${it.key}:${it.value}" }
                writer.write("$invStr\n")
                
                // 3. 발견한 행성 목록 쓰기 (Set의 데이터를 쉼표/세미콜론으로 묶어 문자열 화)
                val planetStr = discoveredPlanets.joinToString(";")
                writer.write("$planetStr\n")
            }
        } catch (e: Exception) {
            println("저장 중 오류 발생: ${e.message}")
        }
    }

    /**
     * 디스크에서 게임 데이터를 읽어옵니다.
     * @return 성공 시 데이터를 Map 형태로 반환하고, 실패 시 null 반환
     */
    fun load(): Map<String, Any>? {
        return try {
            val file = File(FILE_NAME)
            // [개념 적용 ⑤: 예외 처리(Exception Handling)]
            // 1) 파일이 없을 경우 예외 던지기
            if (!file.exists()) throw FileNotFoundException("세이브 파일이 없습니다.")
            
            // [개념 적용 ④: use 스코프 함수 활용]
            file.bufferedReader().use { reader ->
                
                // 2) 내용이 비어있으면 커스텀 예외 던지기
                val line = reader.readLine() ?: throw CorruptedSaveException("세이브 파일이 비어 있습니다.")
                val parts = line.split(",")
                
                // 3) 데이터 갯수가 부족하면 커스텀 예외 던지기
                if (parts.size < 9) throw CorruptedSaveException("데이터 형식이 맞지 않습니다.")
                
                // 인벤토리(Map) 복원 로직
                val invLine = reader.readLine() ?: ""
                val inventory = mutableMapOf<String, Int>()
                if (invLine.isNotBlank()) {
                    invLine.split(";").forEach {
                        val pair = it.split(":")
                        if (pair.size == 2) {
                            inventory[pair[0]] = pair[1].toInt()
                        }
                    }
                }

                // 행성 목록(Set) 복원 로직
                val planetLine = reader.readLine() ?: ""
                val planets = if (planetLine.isNotBlank()) planetLine.split(";").toSet() else emptySet()

                // 읽어들인 값들을 Map 형태로 패키징하여 반환
                mapOf(
                    "name" to parts[0],
                    "shipClass" to parts[1],
                    "fuel" to parts[2].toInt(),
                    "minerals" to parts[3].toInt(),
                    "hp" to parts[4].toInt(),
                    "maxHp" to parts[5].toInt(),
                    "attackPower" to parts[6].toInt(),
                    "level" to parts[7].toInt(),
                    "exp" to parts[8].toInt(),
                    "inventory" to inventory,
                    "discoveredPlanets" to planets
                )
            }
        } catch (e: FileNotFoundException) {
            println("불러오기 실패: ${e.message}")
            null
        } catch (e: CorruptedSaveException) {
            println("세이브 파일 손상: ${e.message}")
            null
        } catch (e: NumberFormatException) {
            // [개념 적용 ⑤: 예외 처리] Int로 파싱할 때 글자가 들어있으면 발생하는 예외를 잡음
            println("데이터 형식이 잘못되었습니다 (숫자가 와야 할 자리에 문자 존재).")
            null
        }
    }
}
