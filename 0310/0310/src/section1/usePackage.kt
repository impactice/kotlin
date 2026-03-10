package section1

import com.example.edu.Person as User // as User를 씀으로서 패키지에 별명을 달았다
import kotlin.math.PI

fun main() {
    val intro:String = "안녕하세요"
    val num: Int = 100

    println(PI)
    //val user1 = Person("정원희", 20) //import com.example.edu.Person 이거를 쓴 것
    val user1 = Person("kong", "정원희")
    val user2 = User("뽀로로", 10)
    println("$num : ${user1.name}님 $intro")
}

class Person(val id: String, val name: String)