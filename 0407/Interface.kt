open class A{
    open fun f() = println("A Class f()")
    fun a() = println("A Class a()")
}
//인터페이스는 기본적으로 open
interface B{
    fun f() = println("B Class f()")
    fun b() = println("B Class b()")
}
class C:A(), B{
    override fun f() = println("C Class f()")
    fun test() {
        f()
        b()
        super<A>.f() // <A>가 없으면 왜 에러가 나나? 어떠한 super함수인지 지정을 안해서...
        super<B>.f()
    }
}
fun main() {
    val c = C()
    c.test()
}