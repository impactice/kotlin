
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
