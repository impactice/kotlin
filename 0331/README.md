
# 흐름 제어 

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

## 예외 발생시키기 (throw)

```
fun main() {
    
    var amount = 600

    try {
        amount -= 100
        checkAmount(amount)
    } catch (e : Exception){
        println(e.message)
    }
    println("amount: $amount")
}

fun checkAmount(amount : Int){
    if (amount < 1000)
        throw Exception("잔고가 $amount 으로 1000 이하입니다.")
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/af6c852f-1f3b-49a5-bd37-0d83c009f459" />

## 사용자 정의 예외

```
class InvalidAmountException(message:String) : Exception(message) 

fun main(){
    var name = "kildong123"
    try {
        validateName(name)
    }catch (e:InvalidAmountException){
        println(e.message)
    }catch (e: Exception) {
        println(e.message)
    }
}

fun validateName(name: String){
    if(name.matches(Regex(".*\\d+.*"))) {
        throw InvalidAmountException("이름에 숫자가 포함되어 있습니다.")
    }

}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/1b39d33b-b3a9-48a2-8672-b0184f043200" />


# 클래스와 객체의 정의 

## 클래스 선언하기 

```
class Bird {
    //property
    var name:String = "bird1"  // 초기값들은 꼭 들어가 있어야 한다 -> 없으면 에러가 남
    var wing:Int = 2
    var beak:String = "short"
    var color:String = "blue"

    //method
    fun fly() = println("Fly wing : $wing")
    fun sing(vol:Int) = println("Singing $vol")
}
fun main() {
    val coco = Bird()
    coco.color = "red"

    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(10)

}
```

<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/ecd25c01-f820-4948-b5d0-bc57e1301888" />

## Secondary Constructor(부 생성자)

```
class Bird {
    //property - 생성자가 없을 때는 초기값을 할당해야 함!
    var name:String = "bird1"  // 초기값들은 꼭 들어가 있어야 한다 -> 없으면 에러가 남
    var wing:Int = 2
    var beak:String = "short"
    var color:String = "blue"

    //secondary constructor
    constructor(name:String, wing:Int, beak:String, color:String) {
        this.name = name
        this.wing = wing
        this.beak = beak
        this.color = color
    }

    //method
    fun fly() = println("Fly wing : $wing")
    fun sing(vol:Int) = println("Singing $vol")
}
fun main() {
    val coco = Bird(name = "Coco", wing = 2, beak = "short",color = "red")
    //coco.color = "red"

    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(10)

}

```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/93d03e36-dc95-45dc-8031-0c56ccada2c3" />




