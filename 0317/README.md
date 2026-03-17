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






