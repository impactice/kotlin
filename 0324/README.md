# 다양한 함수들
## 코틀린 다양한 함수 알아보자

### 인라인 함수 

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

### 중위 함수 

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

### 꼬리 재귀 함수 (Tail Recursive Function)

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

tailrec 키워드

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

tailrec fun factorial1(n: Int, run: Int =1):Long{
    return if(n==1) run.toLong() else factorial1(n-1,run*n)
}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/dc28ff77-5847-4411-b443-b27aef9868c7" />


# 조건문과 반복문

### 실습] 나이에 따른 입장료 계산하기

어떤 공원의 입장료는 다음과 같다. 나이를 입력하면 입장료를 안내하는 코드를 작성해 보자.

```
// 나이에 따른 입장료 계산하기
// 2세미만 없음 2~13세 1000원 14~19세 2000원 20세이상 2500원

fun main() {
//if문 사용
     // 내가 작성한거
    println("나이를 입력해주세요: ")
    val old = readln().toInt()
    if (old < 2) {
        println("입장료 없음")
    } else if (old in 2..13) {
        println("입장료 1000원")
    } else if (old in 14..19) {
        println("입장료 2000원")
    } else {
        println("입장료 2500원")
    }


//when문 사용

    var age = readLine()!!.toInt()
    var result = when { // 인자가 없는 when문
        age <2 -> 0
        age in 2..13->1000
        age in 14..19->2000
        else -> 2500
    }
    val price = when(age) { // 인자가 있는 when문
        in 2..13->1000
        in 14..19->2000
        in 20..Int.MAX_VALUE->2500
        else -> {
            println("돌 전엔 공짜")
            //0 // 이거는 왜 적은 거지?
        }
    }
    println(price)
}


```

## 2. 반복문


```
fun main() {
    for(i in 1..5) println(i) // 상행 반복
    //for(i in 5..1) println(i) // 이거는 안됨
    for(i in 5 downTo 1) println(i) //하행 반복
    for(i in 1..5 step 2) println(i) //건너 뛰어 반복
}
```


<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/1c50a8ae-0e0d-460f-ae97-cc49f8ebde39" />

```
fun main() {
    val number = 4
    val result: Long

    //result = factorial(number)
    result = factorial1(number)
    println("Factorial : $number -> $result")
}

fun factorialwhile(n: Int): Long {  //while 추가
    var number = n.toLong()
    var result: Long = 1
    while (number > 0) {
        result *= number
        --number
    }
    return result
}

fun factorial(n: Int): Long {
    return if (n == 1) n.toLong() else n * factorial(n - 1)
}

tailrec fun factorial1(n: Int, run: Int = 1): Long {
    return if (n == 1) run.toLong() else factorial1(n - 1, run * n)
}
```

