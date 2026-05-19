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



