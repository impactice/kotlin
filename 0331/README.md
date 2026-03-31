
# 흐름 제

## 람다식에서 return 사용하기

```
fun main() {
    retFunc()
    println("메인함수 종료")
}
inline fun inlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}
fun retFunc() {
    println("start of retFunc") // ①
    inlineLambda(13, 3) lb1@{ a, b ->  // ②
        val result = a + b
        if(result > 10) return@lb1 // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    }
    println("end of retFunc") // ⑤
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/d2130221-4515-4f66-8dbb-373cb0e266da" />

## 라벨(Label)이란?
숫자를 바꾸니 작동이 됨
```
fun main() {
    retFunc()
    println("메인함수 종료")
}
inline fun inlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}
fun retFunc() {
    println("start of retFunc") // ①
    inlineLambda(4, 3) lb1@{ a, b ->  // ②
        val result = a + b
        if(result > 10) return@lb1 // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    }
    println("end of retFunc") // ⑤
}
```

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/058a9380-132e-4b8b-82bd-ffc179ca967f" />


이렇게 쓸 수도 있음 

```
fun main() {
    retFunc()
    println("메인함수 종료")
}
inline fun inlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}
fun retFunc() {
    println("start of retFunc") // ①
    inlineLambda(4, 3) { a, b ->  // ②
        val result = a + b
        if(result > 10) return@inlineLambda // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    }
    println("end of retFunc") // ⑤
}
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/edad67ed-bbb4-49db-8594-bd8a8ced142a" />


```
fun main() {
    retFunc()
    println("메인함수 종료")
}
inline fun inlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}
fun retFunc() {
    println("start of retFunc") // ①
    inlineLambda(4, 3 , out= fun (a,b){  // ②
        val result = a + b
        if(result > 10) return // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    })
    /*inlineLambda(4, 3) { a, b ->  // ②
        val result = a + b
        if(result > 10) return@inlineLambda // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    }*/
    println("end of retFunc") // ⑤
}
```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/5c721d21-e24e-4406-a361-42da955dbbcd" />

## 람다식과 익명 함수를 함수에 할당할 때 주의할 점

```
fun greet() {println("Hello World")}

fun main() {
    greet()
    //retFunc()
    //println("메인함수 종료")
}
inline fun inlineLambda(a: Int, b: Int, out: (Int, Int) -> Unit) {
    out(a, b)
}
fun retFunc() {
    println("start of retFunc") // ①
    inlineLambda(4, 3 , out= fun (a,b){  // ②
        val result = a + b
        if(result > 10) return // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    })
    /*inlineLambda(4, 3) { a, b ->  // ②
        val result = a + b
        if(result > 10) return@inlineLambda // ③ 10보다 크면 이 함수를 빠져 나감
        println("result: $result") //  ④ 10보다 크면 이 문장에 도달하지 못함
    }*/
    println("end of retFunc") // ⑤
}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/077cf225-77c9-4de0-9577-8f94220389a3" />

## 중첩 루프에서 라벨을 사용하여 제어하기

```
//break 예시
fun labelBreak() {
    println("labelBreak")
    first@ for(i in 1..5) {
        second@ for (j in 1..5) {
            if (j == 3) break@first
            println("i:$i, j:$j")
        }
        println("after for j")
    }
    println("after for i")
}

fun main() {
    labelBreak()
}
```

<img width="1919" height="1025" alt="image" src="https://github.com/user-attachments/assets/f46efcb6-e1fd-4cc5-bc92-165ab3e2b1fe" />

```
//break 예시
fun labelBreak() {
    println("labelBreak")
    first@ for(i in 1..5) {
        second@ for (j in 1..5) {
            if (j == 3) break@first
            println("i:$i, j:$j")
        }
        println("after for j")
    }
    println("after for i")
}

//continue 예시
fun labelContinue() {
    println("labelBreak")
    first@ for(i in 1..5) {
        second@ for (j in 1..5) {
            if (j == 3) continue@first
            println("i:$i, j:$j")
        }
        println("after for j")
    }
    println("after for i")
}

fun main() {
    //labelBreak()
    labelContinue()
}
```

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/f646b238-b9bd-4b5f-bf17-24f43550d9e2" />

## 예외 처리 방법

```
fun main() {
    val a = 6
    val b = 0
    val c : Int
    
    try {
        c = a/b // 0으로 나눔
    } catch (e : Exception){
        println("Exception is handled.")
    } finally {
        println("finally 블록은 반드시 항상 실행됨")
    }
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/043069bb-6a09-4caa-a022-348a3aee2f06" />

