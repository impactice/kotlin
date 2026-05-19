# 연산자 오버로딩

## 3. 연산자 오버로딩 (operator overloading)

### 연산자의 작동 방식
```
class Point(var x: Int = 0, var y: Int = 10) {
    // + 연산자 오버로딩
    operator fun plus(p: Point): Point {
        return Point(x + p.x, y + p.y)
    }

}

fun main() {
    val a = Point(3, -8)
    val b = Point(2, 9)
    val p = a + b
    println("point = (${p.x}, ${p.y})")
    println(a + b)
}

```

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/49abf059-50b5-45da-a164-57f73b10d713" />

### 연산자의 종류
```
data class Vector(val x: Double, val y: Double) {
    operator fun plus(v: Vector): Vector {
        return Vector(x + v.x, y + v.y)
    }

    operator fun times(t: Double): Vector {
        return Vector(x * t, y * t)
    }
}

// Double * Vector 연산자 오버로딩
operator fun Double.times(v: Vector): Vector {
    return Vector(v.x * this, v.y * this)
}

fun main() {
    println("Vector(1.0, 2.0) + Vector(3.0, 1.5) = ${Vector(1.0, 2.0) + Vector(3.0, 1.5)}")
    println("Vector(1.0, 2.0) * 3.0 = ${Vector(1.0, 2.0) * 3.0}")
    println("Vector(1.0, 2.2) * 3.0 = ${Vector(1.0, 2.2).times(3.0)}")
    println("3.0 * Vector(1.0, 2.0) = ${3.0 * Vector(1.0, 2.0)}")
}

```

<img width="1919" height="1027" alt="image" src="https://github.com/user-attachments/assets/0d7604ec-3b04-4883-99f6-d16dda5fa20b" />

# 제네릭 다루기

## 1. 제네릭 다루기

### 제네릭의 일반적인 사용 방법

```
class Box<T>(t:T){
    var name = t
}
fun main() {
    val box1:Box<Int> = Box<Int>(10)
    val box2:Box<String> = Box<String>("Hello")
    val box3:Box<section1.Point> = Box<section1.Point>(section1.Point(3,8))
    println(box1.name)
    println(box2.name)
    println("(${box3.name.x}, ${box3.name.y})")
}
```

<img width="1919" height="1024" alt="image" src="https://github.com/user-attachments/assets/256338a2-acce-4fb6-a349-75fc5b456db6" />

### 제네릭 클래스

```
package section2

class MyClass<T>{
    var myPorp:T
    constructor(myPorp:T){
        this.myPorp = myPorp
    }
}

fun main() {
    val m = MyClass(3.14)
}
```
<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/c605189f-6bc9-458e-89f8-46efdfd423ad" />

### 제네릭 함수 혹은 메서드, 제네릭 + 람다식 : 자료형 확정 문제 해결

```
package section2

class MyClass<T>{
    var myPorp:T
    constructor(myPorp:T){
        this.myPorp = myPorp
    }
}

fun <T> find(a:Array<T>,Target:T):Int{
    for(i in a.indices){
        if(a[i] == Target){
            return i
        }
    }
    return -1
}
fun<T> add(a:T,b:T,op:(T,T)->T):T{
    return op(a,b);
}

fun main() {
    val arr1:Array<String> = arrayOf("apple","banana","pineapple")
    val arr2:Array<Int> = arrayOf(1,2,3,4)
    println(find<String>(arr1,"banana"))
    println(find<Int>(arr2,4))
    val result = add(3, 4) { a, b -> a + b }
    val result1 = add(3.14, 4.3) { a, b -> a + b }
}
```

실행을 안 눌렀음...
<img width="1919" height="1025" alt="image" src="https://github.com/user-attachments/assets/05e8bcbf-f784-48b0-b44b-853655c6242b" />


### 자료형 제한하기 


### 클래스에서 형식 매개변수의 자료형 제한하기

### 함수에서 형식 매개변수의 자료형 제한하기

### where 절로 다수 조건의 형식 매개변수 제한하기

### 형식 매개변수의 null 제어 

### 실습 : 제네릭 스택 구현

### 상, 하위 형식의 가변성 (Variance)

```
package section2

open class Parent
class Child : Parent()
class Cup<T>

fun main() {
    val obj1:Parent = Child()
    // val obj2:Child = Parent() //불가능
    // val obj3:Cup<Parent> = Cup<Child>() //안됨
    // val obj4:Cup<Child> = Child<Parent>() //안됨
    val obj5 = Cup<Child>()
    val obj6 = obj5
}
```

#### 제네릭 클래스의 자료형 변환

#### 클래스와 자료형

#### 가변성의 3가지 유형

#### 무변성 (invariance)

#### 공변성 (covariance)

#### 반공변성 (contravariance)

#### 공변성에 따른 자료형 제한하기

# 배열 다루기 (파일이 없다고 뜸)

```

package section3
import java.util.Arrays

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)
    val arr2d = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))

    // 1차원 배열 출력
    println(Arrays.toString(arr))

    // 2차원 배열 출력
    //println(Arrays.deepToString(arr2d))
    println(Arrays.toString(arr.sortedArray()))
    println(Arrays.toString(arr.sortedArrayDescending()))
    println(Arrays.toString(arr)) //원본 변화 없음

    arr.sort()
    println(Arrays.toString(arr))
}

```

<img width="1919" height="1035" alt="image" src="https://github.com/user-attachments/assets/71ac65a2-1011-459f-b2a6-ca3f609349eb" />


```
package section3
import java.util.Arrays

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)
    val arr2d = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))

    // 1차원 배열 출력
    println(Arrays.toString(arr))

    // 2차원 배열 출력
    //println(Arrays.deepToString(arr2d))
    println(Arrays.toString(arr.sortedArray()))
    println(Arrays.toString(arr.sortedArrayDescending()))
    println(Arrays.toString(arr)) //원본 변화 없음

    //arr.sort()
    arr.sortDescending()
    println(Arrays.toString(arr))

    
}

```

# 문자열 다루기 

## 3. 문자열 다루기

### 문자열의 기본 처리

#### 문자열 추출하고 병합하기

#### 문자열 비교하기 

#### StringBuilder 사용하기

### 리터럴(Literal) 문자열

#### 형식 문자 사용하기





