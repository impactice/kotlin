# 함수형 프로그래밍 

## 코틀린의 함수

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

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/37a77518-32ac-4d0e-9e44-7ff63d75b6df" />


```
package section1

fun main() {
    //println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
    //printSum(a=5,b=3)
    //add(name="정문주", "mjlunar@ks.ac.kr")
    //add(email = "눈덮인숲속마을",name="뽀로로")
    printNumbers(n1=1,n2=2,n3=3)
}
fun printNumbers(n1: Int, n2: Int, n3: Int) {
    println("n1=$n1\tn2=$n2\tn3=$n3")
}
// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b

fun printSum(a: Int, b: Int) {
    //println("sum of $a and $b is ${a+b}")
}

fun add(name:String, email:String = "이메일없음") {
    //println("name : $name, email : $email")
}
```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/9eef083c-4084-467e-b373-5e863fd32b6a" />


```
package section1

fun main() {
    //println("함수 테스트 : 5+3 = ${sum(a=5,b=3)}")
    //printSum(a=5,b=3)
    //add(name="정문주", "mjlunar@ks.ac.kr")
    //add(email = "눈덮인숲속마을",name="뽀로로")
    // 직접 값 나열
    printNumbers(1, 2, 3)
    // 배열을 펼쳐서 전달
    val numbers = intArrayOf(1, 2, 3, 4)
    printNumbers(*numbers)

}
fun printNumbers(vararg counts:Int) {
    for(n in counts) {
        print("$n ")
    }
}
// 코틀린 함수는 매개변수와 반환값의 자료형을 명시함
fun sum(a: Int, b: Int): Int = a+b

fun printSum(a: Int, b: Int) {
    //println("sum of $a and $b is ${a+b}")
}

fun add(name:String, email:String = "이메일없음") {
    //println("name : $name, email : $email")
}
```

## 함수형 프로그래밍 


# 고차 함수와 람다식 

## 고차 함수와 람다식 

<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/8d831eb5-4fac-420a-86b2-52e66633df39" />

```
package section2

fun main() {
    val res1 = sum(3, 2)  // 일반 인자
    // 인자에 함수 호출 결과값 사용
    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
    println("res1: $res1, res2: $res2")
}

fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/929106ff-7cf3-4a6c-b164-9dfbefda85c3" />

```
package section2

fun main() {
    val res1 = sum(3, 2)  // 일반 인자
    // 인자에 함수 호출 결과값 사용
    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
    println("res1: $res1, res2: $res2")
    println("funcFunc: ${funcFunc()}")
}

fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b

fun funcFunc(): Int { // 함수의 반환값으로 함수 호출 결과값 사용
    return sum(2, 2)
}
```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/cca1440d-1858-4479-be97-f68fbf76c923" />

```
package section2

fun main() {
    var result : Int
    val multi = {x:Int,y:Int->x*y} //일반 변수에 람다식 할당 //val multi:(Int,Int) -> Int = {x:Int,y:Int->x*y} // val multi:(Int,Int) -> Int = {x,y->x*y} // 저거 다 됨
    result = multi(1,2) // 람다식이 할당된 변수는 함수처럼 사용가능
    println(result)
//    val res1 = sum(3, 2)  // 일반 인자
//    // 인자에 함수 호출 결과값 사용
//    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
//    println("res1: $res1, res2: $res2")
//    println("funcFunc: ${funcFunc()}")
}

fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b

fun funcFunc(): Int { // 함수의 반환값으로 함수 호출 결과값 사용
    return sum(2, 2)
}
```

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/336af173-b304-4cb9-a6cb-3a1667eb1aa4" />

```
package section2

fun main() {
    var result : Int
    val multi = {x:Int,y:Int->x*y} //일반 변수에 람다식 할당 //val multi:(Int,Int) -> Int = {x:Int,y:Int->x*y} // val multi:(Int,Int) -> Int = {x,y->x*y} // 저거 다 됨
    val multi2:(Int,Int) -> Int = {x,y->
        println("x * y")
        x*y
    }
    result = multi2(2,6) // 람다식이 할당된 변수는 함수처럼 사용가능
    println(result)


//    val res1 = sum(3, 2)  // 일반 인자
//    // 인자에 함수 호출 결과값 사용
//    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
//    println("res1: $res1, res2: $res2")
//    println("funcFunc: ${funcFunc()}")
}

fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b

fun funcFunc(): Int { // 함수의 반환값으로 함수 호출 결과값 사용
    return sum(2, 2)
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/871c92f4-ce02-4222-ac63-6a14f26c883b" />

```
package section2

fun main() {
    var result : Int
    val multi = {x:Int,y:Int->x*y} //일반 변수에 람다식 할당 //val multi:(Int,Int) -> Int = {x:Int,y:Int->x*y} // val multi:(Int,Int) -> Int = {x,y->x*y} // 저거 다 됨
    val multi2:(Int,Int) -> Int = {x,y->
        println("x * y")
        x*y
    }
    result = multi2(2,6) // 람다식이 할당된 변수는 함수처럼 사용가능
    println(result)
    val greet: ()-> Unit = {println("Hello World!")} //val greet = {println("Hello World!")} // 이거 가능
    greet()

    val square:(Int) -> Int = {x:Int -> x * x}
    val nestedLambda:() -> () -> Unit = {{println("hihi")}}

//    val res1 = sum(3, 2)  // 일반 인자
//    // 인자에 함수 호출 결과값 사용
//    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
//    println("res1: $res1, res2: $res2")
//    println("funcFunc: ${funcFunc()}")
}

fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b

fun funcFunc(): Int { // 함수의 반환값으로 함수 호출 결과값 사용
    return sum(2, 2)
}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/1fc76da6-4870-489e-86dd-24ae658b9f23" />

```
package section2

fun main() {
    var result: Int
    result = highOrder({x,y->x+y}, 10, 20)
    println(result)

    /*
    var result : Int
    val multi = {x:Int,y:Int->x*y} //일반 변수에 람다식 할당 //val multi:(Int,Int) -> Int = {x:Int,y:Int->x*y} // val multi:(Int,Int) -> Int = {x,y->x*y} // 저거 다 됨
    val multi2:(Int,Int) -> Int = {x,y->
        println("x * y")
        x*y
    }
    result = multi2(2,6) // 람다식이 할당된 변수는 함수처럼 사용가능
    println(result)
    val greet: ()-> Unit = {println("Hello World!")} //val greet = {println("Hello World!")} // 이거 가능
    greet()

    val square:(Int) -> Int = {x:Int -> x * x}
    val nestedLambda:() -> () -> Unit = {{println("hihi")}} */

//    val res1 = sum(3, 2)  // 일반 인자
//    // 인자에 함수 호출 결과값 사용
//    val res2 = mul(sum(3,3), 3) // 인자에 함수를 사용
//    println("res1: $res1, res2: $res2")
//    println("funcFunc: ${funcFunc()}")
}

fun highOrder(sum:(Int,Int)->Int, a:Int, b:Int):Int {
    return sum(a, b)
}
fun sum(a: Int, b: Int) = a + b
fun mul(a: Int, b: Int) = a * b

fun funcFunc(): Int { // 함수의 반환값으로 함수 호출 결과값 사용
    return sum(2, 2)
}
```


