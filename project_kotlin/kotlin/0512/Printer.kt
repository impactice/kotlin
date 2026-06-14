//추상 클래스
abstract class Printer {
    abstract fun print()
}
/*
class Printer1 : Printer() {
    override fun print() {}
}
*/ //이걸 사용하지 않고 함
val myPrinter = object: Printer() {
    override fun print() {
        println("출력합니다.")
    }
}

fun main(){
    myPrinter.print()
}