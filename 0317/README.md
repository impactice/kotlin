<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/36e7356f-a892-4fd1-bd6a-7adc34c5a09a" />

```
package section1

fun main() {
    println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
}

fun sum(a: Int, b: Int): Int {
    var sum = a+b
    return sum
}
```

한 줄로 정리 가능하다

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/f6ec50a1-a78e-4e4c-b821-62697ee04160" />

```
package section1

fun main() {
    println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
}

// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b // 이렇게 한 줄로도 표현 가능하다
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/5045419e-f429-46b2-b921-b5e04a6483c6" />

```
package section1

fun main() {
    //println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
    printSum(a=5,b=3)
}

// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b 

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a+b}")
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/9f2daad0-013c-495a-bac6-cb49d21b7581" />

```
package section1

fun main() {
    //println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
    //printSum(a=5,b=3)
    add(name="정문주", "mjlunar@ks.ac.kr")
    add(name="뽀로로")
}

// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b

fun printSum(a: Int, b: Int) {
    //println("sum of $a and $b is ${a+b}")
}

fun add(name:String, email:String = "이메일없음") {
    println("name : $name, email : $email")
}
```

순서가 바뀌어도 된다 

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/55d9d267-c0dc-4ff9-b853-04dff8dc4952" />


```
package section1

fun main() {
    //println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
    //printSum(a=5,b=3)
    add(name="정문주", "mjlunar@ks.ac.kr")
    add(email = "눈덮인숲속마을",name="뽀로로")
}

// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b

fun printSum(a: Int, b: Int) {
    //println("sum of $a and $b is ${a+b}")
}

fun add(name:String, email:String = "이메일없음") {
    println("name : $name, email : $email")
}
```

