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