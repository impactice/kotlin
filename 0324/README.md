# 코틀린 다양한 함수 알아보

## 인라인 함수 

```
fun shortFunc(a:Int, out:(Int)->Unit){
    println("Before calling out()")
    out(a)
    println("After calling out()")
} // 보통의 경우 이렇게 쓴다

fun main() {
    shortFunc(3){println("First call : $it")}
    shortFunc(5){println("Second call : $it")}
}
```

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/e18879f1-ea74-42e8-ab5a-994a55aa3e4b" />


인라인 변경한 거 확인방법(코드가 짦아서 결과는 같음 ) 

```
inline fun shortFunc(a:Int, out:(Int)->Unit){
    println("Before calling out()")
    out(a)
    println("After calling out()")
} //

fun main() {
    shortFunc(3){println("First call : $it")}
    shortFunc(5){println("Second call : $it")}
}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/649f3ac3-7c59-4a38-9f91-1c3f664e4bad" />

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/77a7d0a8-14bb-4f41-b03c-afa928dafc43" />

디컴파일을 누르면 밑의 이미지가 나온다 

<img width="1919" height="1035" alt="image" src="https://github.com/user-attachments/assets/9d68f867-1509-42b1-baf0-5bb66bda383e" />


```
inline fun shortFunc(a:Int, noinline out:(Int)->Unit){
    println("Before calling out()")
    out(a)
    println("After calling out()")
} //

fun main() {
    shortFunc(3){println("First call : $it")}
    shortFunc(5){println("Second call : $it")}
    //shortFunc(15){println("Second call : $it")}
}
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/0fdab423-5f8b-46b2-98af-91820d4f7296" />

### 인라인 함수와 비 지역 반환

```
inline fun shortFunc(a:Int, crossinline out:(Int)->Unit){ //crossinline은 비지역 반환을 예방
    println("Before calling out()")
    out(a)
    println("After calling out()")
} //

fun main() {
    shortFunc(3) {
        if (it == 3) {
            println("First call : $it")
        }
        else
            println("HOHOHO")
    }
}
```

```
inline fun shortFunc(a:Int, crossinline out:(Int)->Unit){ //crossinline은 비지역 반환을 예방
    println("Before calling out()")
    out(a)
    println("After calling out()")
} //

fun main() {
    shortFunc(3) {
        if (it == 3) {
            println("First call : $it")
            return@shortFunc // 만약 return을 하고 싶으면 이렇게 쓰면 됨
        }
        else
            println("HOHOHO")
    }
}
```

## 중위 함수 

```
fun main() {
    //일반 표현법
    val num = 5.muliply(10)
    println(num)
    //중위 표현법
}

// int 확장함수 만들기
fun Int.muliply(x: Int): Int {
    return this*x
}

```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/bea2c1f6-fad1-49ff-8a7a-2463bbc27d03" />

```
fun main() {
    //일반 표현법
    var num = 5.muliply(10)
    println(num)
    //중위 표현법
    num = 5 muliply 100
    println(num)

    val deadLine = 3 days "later"
    println(deadLine)
}

// int 확장함수 만들기
infix fun Int.muliply(x: Int): Int {
    return this * x
}

infix fun Int.days(unit: String): Long {
    return if (unit == "ago") {
        System.currentTimeMillis() - (this * 24 * 60 * 60 * 1000L)
    } else {
        System.currentTimeMillis() + (this * 24 * 60 * 60 * 1000L)
    }
}

```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/27c21f10-f66e-45f6-8015-a78301ea313f" />

## 꼬리 재귀 함수 (Tail Recursive Function)

```
fun main() {
    val number = 4
    val result: Long

    result = factorial(number)
    println("Factorial : $number -> $result")
}
fun factorial(n: Int): Long{
    return if(n==1) n.toLong() else n*factorial(n - 1)
}

```

<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/b870f24e-86bc-497d-b793-e654803631f0" />


```
fun main() {
    val number = 4
    val result: Long

    //result = factorial(number)
    result = factorial1(number)
    println("Factorial : $number -> $result")
}
fun factorial(n: Int): Long{
    return if(n==1) n.toLong() else n*factorial(n - 1)
}
fun factorial1(n: Int, run: Int =1):Long{
    return if(n==1) run.toLong() else factorial1(n-1,run*n)
}
```
값은 같다 
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/952b3407-58c8-470d-8b81-d20800918a60" />

