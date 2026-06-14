package section2

import section1.Point

class Box<T>(t:T){
    var name = t
}
fun main() {
    val box1:Box<Int> = Box<Int>(10)
    val box2:Box<String> = Box<String>("Hello")
    val box3:Box<Point> = Box<Point>(Point(3,8))
    println(box1.name)
    println(box2.name)
    println("(${box3.name.x}, ${box3.name.y})")
}