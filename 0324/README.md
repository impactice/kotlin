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

```
fun main() {
    val secret = (1..100).random()  // 비밀 숫자 생성
    var guess: Int                   // 사용자 입력
    var attempts = 0                 // 시도 횟수

    println("=== 숫자 추리 게임 ===")
    println("1~100 사이의 숫자를 맞춰보세요!\n")

    do {
        println("[시도 $attempts] 숫자를 입력하세요: ")
        guess = readLine()!!.toInt()
        // ... (입력받기) ...
        guess = when {
            


        }


        // ... (생략) ...
    } while (secret == guess)
}
```

```
// step 1
/*
fun main() {
    // 1부터 100 사이의 임의의 숫자를 생성하여 secret 변수에 저장
    val secret = (1..100).random()

    // 사용자가 입력한 숫자를 저장할 변수 초기값은 0으로 설정
    var guess: Int = 0

    // 사용자의 시도 횟수를 세기 위한 변수  0부터 시작
    var attempts = 0

    // val이랑 var 차이: 코틀린에서 val과 var의 차이는 변수의 변경 가능성이다.

    //val: 읽기 전용 변수, 즉 불변 변수이다. 한 번 초기화하면 값을 다시 할당할 수 없다.
    //var: 가변 변수이다. 값을 여러 번 할당할 수 있다.


    // 게임 시작 안내 문구를 출력
    println("=== 숫자 추리 게임 ===")
    println("1~100 사이의 숫자를 맞춰보세요!\n")

    // 정답을 맞출 때까지 반복하는 do-while 문을 시작합니다.
    do {
        // 시도 횟수를 1씩 증가
        attempts++

        // 줄바꿈 없이 입력 프롬프트를 출력하여 예시와 동일한 형태를 만듭니다.
        print("[시도 $attempts] 숫자를 입력하세요: ")

        // 사용자의 입력을 받아 정수로 변환한 뒤 guess 변수에 저장합니다.
        guess = readLine()!!.toInt()

        // when 표현식을 사용하여 입력값(guess)과 정답(secret)의 크기를 비교합니다.
        when {
            guess < secret -> println("더 큽니다! ↑\n") // 입력값이 정답보다 작을 때
            guess > secret -> println("더 작습니다! ↓\n") // 입력값이 정답보다 클 때
            guess == secret -> println("정답입니다! ${attempts}번 만에 맞추셨습니다!") // 정답을 맞췄을 때
        }

        // 사용자가 입력한 값(guess)이 비밀 숫자(secret)와 '다를 동안' 계속 반복합니다.
    } while (secret != guess)
}
 */

// step 2
/*
// 1. 고차 함수 선언
// 이 함수는 정답(secret), 입력값(guess), 그리고 두 값을 비교하여 문자열을 반환하는 함수(compare)를 매개변수로 받습니다.
fun checkGuess(
    secret: Int,
    guess: Int,
    compare: (Int, Int) -> String
): String {
    // 넘겨받은 compare 함수에 secret과 guess를 인자로 전달하여 실행하고,
    // 그 결과(힌트 문자열)를 그대로 반환(return)합니다.
    return compare(secret, guess)
}

fun main() {
    // 1부터 100 사이의 임의의 숫자를 생성하여 secret 변수에 저장합니다.
    val secret = (1..100).random()

    // 사용자가 입력한 숫자를 저장할 변수입니다.
    var guess: Int = 0

    // 사용자의 시도 횟수를 세기 위한 변수입니다.
    var attempts = 0

    println("=== 숫자 추리 게임 ===")
    println("1~100 사이의 숫자를 맞춰보세요!\n")

    // 정답을 맞출 때까지 반복합니다.
    do {
        // 시도 횟수를 1 증가시킵니다.
        attempts++

        // 사용자에게 입력을 안내하고 값을 받습니다.
        print("[시도 $attempts] 숫자를 입력하세요: ")
        guess = readLine()!!.toInt()

        // 2. trailing lambda를 활용한 고차 함수 호출
        // checkGuess 함수의 마지막 매개변수가 함수(람다)이므로 소괄호 밖으로 중괄호를 빼서 작성합니다.
        val hint = checkGuess(secret, guess) { s, g ->
            // s는 secret, g는 guess를 의미합니다.
            // when 표현식의 결과(문자열)가 람다의 반환값이 되어 hint 변수에 저장됩니다.
            when {
                g < s -> "더 큽니다! ↑\n"
                g > s -> "더 작습니다! ↓\n"
                else -> "정답입니다! ${attempts}번 만에 맞추셨습니다!" // g == s인 경우 (else로 처리하여 모든 경우의 수 보장)
            }
        }

        // 고차 함수를 통해 만들어진 힌트 문자열을 출력합니다.
        println(hint)

        // 사용자가 입력한 값(guess)이 비밀 숫자(secret)와 다를 동안 반복합니다.
    } while (secret != guess)
}

 */

//step3
/*
// 3. 확장 함수 선언
// 기존의 Int 타입에 우리가 만든 toRank()라는 새로운 함수를 추가(확장)합니다.
// 반환 타입은 String으로 지정합니다.
fun Int.toRank(): String {
    // this 키워드는 이 함수를 호출한 주체(여기서는 Int 숫자인 attempts)를 가리킵니다.
    // in 연산자를 사용하여 this 값이 해당 범위(Range) 안에 포함되는지 확인합니다.
    return when (this) {
        in 1..3 -> "S등급"       // 1 ~ 3회
        in 4..6 -> "A등급"       // 4 ~ 6회
        in 7..10 -> "B등급"      // 7 ~ 10회
        else -> "C등급"          // 11회 이상 (그 외의 모든 경우)
    }
}

// 1. 고차 함수 선언 (Step 2에서 작성한 내용)
fun checkGuess(
    secret: Int,
    guess: Int,
    compare: (Int, Int) -> String
): String {
    return compare(secret, guess)
}

fun main() {
    val secret = (1..100).random()  // 비밀 숫자 생성
    var guess: Int = 0              // 사용자 입력
    var attempts = 0                // 시도 횟수

    println("=== 숫자 추리 게임 ===")
    println("1~100 사이의 숫자를 맞춰보세요!\n")

    // 정답을 맞출 때까지 반복
    do {
        attempts++
        print("[시도 $attempts] 숫자를 입력하세요: ")
        guess = readLine()!!.toInt()

        // 2. trailing lambda를 활용한 고차 함수 호출 (Step 2에서 작성한 내용)
        val hint = checkGuess(secret, guess) { s, g ->
            when {
                g < s -> "더 큽니다! ↑\n"
                g > s -> "더 작습니다! ↓\n"
                else -> "정답입니다! ${attempts}번 만에 맞추셨습니다!"
            }
        }
        println(hint)
    } while (secret != guess)

    // --- Step 3: 게임 종료 후 등급 출력 ---

    // Int 타입인 attempts 변수에서 바로 확장 함수 toRank()를 호출합니다.
    val rank = attempts.toRank()

    // 실행 예시의 "A등급 - 훌륭합니다!" 처럼 부가 설명을 달아주기 위한 추가 로직입니다.
    val comment = when (rank) {
        "S등급" -> "완벽합니다!"
        "A등급" -> "훌륭합니다!"
        "B등급" -> "잘하셨습니다!"
        else -> "조금 더 분발해보세요!"
    }

    // 최종 결과 출력
    println("당신의 등급: $rank - $comment")
}

 */

// step4
/*
// 4. 중위 함수 선언 (Step 4 추가)
// infix 키워드를 사용하여 중위 표기법으로 호출할 수 있는 확장 함수를 만듭니다.
// this(호출하는 숫자: guess)와 other(비교할 숫자: secret)의 차이가 5 이하인지 판별하여 Boolean 값을 반환합니다.
infix fun Int.isCloseTo(other: Int): Boolean {
    // 두 수의 차이(절대값)를 구하기 위해 큰 수에서 작은 수를 뺍니다.
    val diff = if (this > other) this - other else other - this
    return diff <= 5
}

// 3. 확장 함수 선언 (Step 3)
// Int 타입에 시도 횟수에 따른 등급을 문자열로 반환하는 기능을 추가합니다.
fun Int.toRank(): String {
    return when (this) {
        in 1..3 -> "S등급"
        in 4..6 -> "A등급"
        in 7..10 -> "B등급"
        else -> "C등급"
    }
}

// 1. 고차 함수 선언 (Step 2)
// 정답과 입력값, 그리고 두 값을 비교할 함수(람다)를 매개변수로 받습니다.
fun checkGuess(
    secret: Int,
    guess: Int,
    compare: (Int, Int) -> String
): String {
    return compare(secret, guess)
}

fun main() {
    val secret = (1..100).random()  // 비밀 숫자 생성
    var guess: Int = 0              // 사용자 입력
    var attempts = 0                // 시도 횟수

    println("=== 숫자 추리 게임 ===")
    println("1~100 사이의 숫자를 맞춰보세요!\n")

    // 정답을 맞출 때까지 반복
    do {
        attempts++
        print("[시도 $attempts] 숫자를 입력하세요: ")
        guess = readLine()!!.toInt()

        // 2. trailing lambda를 활용한 고차 함수 호출
        val hint = checkGuess(secret, guess) { s, g ->
            when {
                g < s -> "더 큽니다! ↑"
                g > s -> "더 작습니다! ↓"
                else -> "정답입니다! ${attempts}번 만에 맞추셨습니다!"
            }
        }

        // 일차적인 힌트 출력
        println(hint)

        // 4. 중위 함수 활용: 오답일 때만 추가 힌트 제공
        // guess와 secret이 같지 않고(오답), 두 값의 차이가 5 이하일 때 참이 됩니다.
        if (guess != secret && (guess isCloseTo secret)) {
            println("거의 다 왔습니다!")
        }

        println() // 각 시도마다 보기 좋게 한 줄 띄우기

    } while (secret != guess)

    // 게임 종료 후 등급 계산 및 출력
    val rank = attempts.toRank()

    val comment = when (rank) {
        "S등급" -> "완벽합니다!"
        "A등급" -> "훌륭합니다!"
        "B등급" -> "잘하셨습니다!"
        else -> "조금 더 분발해보세요!"
    }

    println("당신의 등급: $rank - $comment")
}

 */

//step5
/*
// 4. 중위 함수 (Step 4)
infix fun Int.isCloseTo(other: Int): Boolean {
    val diff = if (this > other) this - other else other - this
    return diff <= 5
}

// 3. 확장 함수 (Step 3)
fun Int.toRank(): String {
    return when (this) {
        in 1..3 -> "S등급"
        in 4..6 -> "A등급"
        in 7..10 -> "B등급"
        else -> "C등급"
    }
}

// 1. 고차 함수 (Step 2)
fun checkGuess(secret: Int, guess: Int, compare: (Int, Int) -> String): String {
    return compare(secret, guess)
}

// 5. 꼬리 재귀 함수 (Step 5)
// tailrec 키워드는 이 함수가 꼬리 재귀 최적화가 가능함을 컴파일러에게 알립니다.
tailrec fun playRound(secret: Int, attempts: Int = 0): Int {
    // 매 판마다 시도 횟수를 1씩 증가시킵니다.
    val currentAttempts = attempts + 1

    print("[시도 $currentAttempts] 숫자를 입력하세요: ")
    // 입력값이 null이거나 숫자가 아닐 경우를 대비해 예외 처리를 곁들일 수 있습니다.
    val guess = readLine()?.toIntOrNull() ?: 0

    // 고차 함수를 이용해 힌트를 생성합니다.
    val hint = checkGuess(secret, guess) { s, g ->
        when {
            g < s -> "더 큽니다! ↑"
            g > s -> "더 작습니다! ↓"
            else -> "정답입니다! ${currentAttempts}번 만에 맞추셨습니다!"
        }
    }
    println(hint)

    // 정답이 아닐 때만 근접 힌트를 출력합니다. (Step 4 중위 함수 활용)
    if (guess != secret && (guess isCloseTo secret)) {
        println("거의 다 왔습니다!")
    }
    println()

    // 조건에 따른 분기 처리
    return if (guess == secret) {
        // 정답이면 최종 시도 횟수를 반환하고 함수를 종료합니다.
        currentAttempts
    } else {
        // 오답이면 자기 자신을 다시 호출합니다. (재귀 호출)
        // 이 호출이 함수의 '가장 마지막 동작'이어야 tailrec 최적화가 작동합니다.
        playRound(secret, currentAttempts)
    }
}

fun main() {
    val secret = (1..100).random()
    println("=== 숫자 추리 게임 ===")
    println("1~100 사이의 숫자를 맞춰보세요!\n")

    // 반복문(do-while) 대신 꼬리 재귀 함수를 호출하여 게임을 시작합니다.
    // 반환값으로 최종 시도 횟수를 받습니다.
    val totalAttempts = playRound(secret)

    // 게임 종료 후 결과 처리 (Step 3)
    val rank = totalAttempts.toRank()
    val comment = when (rank) {
        "S등급" -> "완벽합니다!"
        "A등급" -> "훌륭합니다!"
        "B등급" -> "잘하셨습니다!"
        else -> "조금 더 분발해보세요!"
    }

    println("당신의 등급: $rank - $comment")
}

 */

// step 6

// 4. 중위 함수 (Step 4)
infix fun Int.isCloseTo(other: Int): Boolean {
    val diff = if (this > other) this - other else other - this
    return diff <= 5
}

// 3. 확장 함수 (Step 3)
fun Int.toRank(): String {
    return when (this) {
        in 1..3 -> "S등급"
        in 4..6 -> "A등급"
        in 7..10 -> "B등급"
        else -> "C등급"
    }
}

// 1. 고차 함수 (Step 2)
fun checkGuess(secret: Int, guess: Int, compare: (Int, Int) -> String): String {
    return compare(secret, guess)
}

// 5. 꼬리 재귀 함수 (Step 5)
tailrec fun playRound(secret: Int, attempts: Int = 0): Int {
    val currentAttempts = attempts + 1

    print("[시도 $currentAttempts] 숫자를 입력하세요: ")
    // 입력 중 오류가 발생하지 않도록 안전하게 처리 (숫자가 아니면 0으로 간주)
    val guess = readLine()?.toIntOrNull() ?: 0

    val hint = checkGuess(secret, guess) { s, g ->
        when {
            g < s -> "더 큽니다! ↑"
            g > s -> "더 작습니다! ↓"
            else -> "정답입니다! ${currentAttempts}번 만에 맞추셨습니다!"
        }
    }
    println(hint)

    if (guess != secret && (guess isCloseTo secret)) {
        println("거의 다 왔습니다!")
    }

    return if (guess == secret) {
        currentAttempts
    } else {
        playRound(secret, currentAttempts)
    }
}

fun main() {
    // --- Step 6: 통계 기록용 변수 선언 ---
    // 배열이나 리스트 없이 누적값을 저장하기 위한 변수들입니다.
    var totalPlays = 0        // 총 플레이 횟수
    var totalAttempts = 0     // 총 시도 횟수 (평균을 구하기 위해 필요)

    // 최소 시도 횟수를 구하기 위해 초기값을 Int가 가질 수 있는 가장 큰 값으로 설정합니다.
    // 그래야 첫 게임 결과가 무조건 최소 횟수로 갱신됩니다.
    var minAttempts = Int.MAX_VALUE

    var isPlaying = true      // 게임 계속 진행 여부 플래그

    println("=== 숫자 추리 게임 ===")

    // 사용자가 'n'을 입력할 때까지 계속 반복하는 while 문
    while (isPlaying) {
        // 난이도 선택
        print("난이도를 선택하세요 (1:쉬움  2:보통  3:어려움): ")
        val difficulty = readLine()?.toIntOrNull() ?: 2 // 기본값은 2(보통)로 설정

        // when 표현식으로 난이도에 따른 최대 범위 설정
        val maxRange = when (difficulty) {
            1 -> 50     // 쉬움
            3 -> 200    // 어려움
            else -> 100 // 보통 (2번 또는 잘못된 입력 시)
        }

        println("1~${maxRange} 사이의 숫자를 맞춰보세요!\n")
        val secret = (1..maxRange).random()

        // Step 5에서 만든 꼬리 재귀 함수로 한 판을 진행하고, 걸린 횟수를 반환받습니다.
        val attemptsThisRound = playRound(secret)

        // --- Step 6: 게임 종료 후 통계 업데이트 ---
        totalPlays++ // 플레이 횟수 1 증가
        totalAttempts += attemptsThisRound // 이번 판의 시도 횟수를 총합에 누적

        // 이번 판의 시도 횟수가 기존 최고 기록(최소 시도 횟수)보다 작으면 갱신
        if (attemptsThisRound < minAttempts) {
            minAttempts = attemptsThisRound
        }

        // 등급 출력
        val rank = attemptsThisRound.toRank()
        val comment = when (rank) {
            "S등급" -> "천재!"
            "A등급" -> "훌륭합니다!"
            "B등급" -> "잘했습니다!"
            else -> "조금 더 분발해보세요!"
        }
        println("당신의 등급: $rank - $comment\n")

        // 다시 하기 여부 확인
        print("다시 하시겠습니까? (y/n): ")
        val answer = readLine() ?: "n"

        // 입력값이 "y"나 "Y"가 아니면 루프를 종료합니다.
        if (answer.lowercase() != "y") {
            isPlaying = false
        }
        println() // 다음 판 또는 통계 출력 전 줄바꿈
    }

    // --- 게임 완전 종료 후 통계 출력 ---
    println("=== 최종 통계 ===")
    println("총 플레이: ${totalPlays}회")
    println("총 시도 횟수: ${totalAttempts}회")

    // 평균 시도 횟수 계산 (소수점 출력을 위해 Double로 형변환 후 나눗셈)
    val average = totalAttempts.toDouble() / totalPlays
    // 평균이 깔끔하게 나오도록 소수점 첫째 자리까지만 표시할 수도 있지만, 기본 나누기 결과도 예시와 호환됩니다.
    println("평균 시도 횟수: ${average}회")
    println("최소 시도 횟수: ${minAttempts}회 (최고 기록!)")
}
```
