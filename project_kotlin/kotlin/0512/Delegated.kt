package section1

interface Nameable{
    val name: String
}
class StaffName:Nameable{
    override val name: String = "Sean"
}
class Work:Runnable{
    override fun run() {
        println("section1.Work...")
    }
}
class Person(name:Nameable,work:Runnable):Nameable by name, Runnable by work
fun main(){
    val p = Person(StaffName(),Work())
    val p2 = Person2(StaffName(),Work())
    println(p.name)
    p.run()
}
class Person2(name:StaffName,work:Work)