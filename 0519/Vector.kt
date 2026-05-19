package section1

data class Vector(val x: Double, val y: Double) {
    operator fun plus(v: Vector): Vector {
        return Vector(x + v.x, y + v.y)
    }

    operator fun times(t: Double): Vector {
        return Vector(x * t, y * t)
    }
}

// Double * section1.Vector 연산자 오버로딩
operator fun Double.times(v: Vector): Vector {
    return Vector(v.x * this, v.y * this)
}

fun main() {
    println("section1.Vector(1.0, 2.0) + section1.Vector(3.0, 1.5) = ${Vector(1.0, 2.0) + Vector(3.0, 1.5)}")
    println("section1.Vector(1.0, 2.0) * 3.0 = ${Vector(1.0, 2.0) * 3.0}")
    println("section1.Vector(1.0, 2.2) * 3.0 = ${Vector(1.0, 2.2).times(3.0)}")
    println("3.0 * section1.Vector(1.0, 2.0) = ${3.0 * Vector(1.0, 2.0)}")
}
