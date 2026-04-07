open class Base{
    protected var i =1
    fun protectedFun(){
        i+=2
    }
    fun access(){
        protectedFun()
    }
}
class Derived : Base() {
    fun test(base: Base):Int{
        protectedFun()
        return i
    }
}
fun main() {
    val base = Base()
    //base.i // 접근불가
    //base.protectedFunc()
    base.access()
}