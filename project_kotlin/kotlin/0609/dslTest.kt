data class Person(
    var name: String? = null,
    var age: Int? = null,
    var jab: Job? = null
)
data class Job(
    var category: String? = null,
    var position: String? = null,
    var extension: String? = null
)
fun person(block:(Person)->Unit):Person{
    val p = Person()
    block(p)
    return p
}

fun main() {
    val person = person {
        it.name = "길동"
        it.age = 30
    }
    println(person)
}