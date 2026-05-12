open class Animal(val name: String)

interface Pet {
    var category: String    // 추상 프로퍼티
    var species: String
    val msgTags: String
        get() = "I'm your lovely pet"
    fun feeding()           // 추상 메서드
    fun patting() {         // 일반 메서드 (구현부가 포함됨)
        println("Keep patting!")
    }
}

class Dog(name: String, override var category: String) : Animal(name), Pet {
    override var species: String = "dog"
    override fun feeding() {
        println("Feed the dog a bone")
    }
}

class Cat(name: String, override var category: String) : Animal(name), Pet {
    override var species: String = "cat"
    override fun feeding() {
        println("Feed the cat a tuna can!")
    }
}

class Master {
    fun playWithPet(pet: Pet) {
        println("Enjoy with my ${pet.species}")
    }
}

fun main() {
    val master = Master()
    val dog = Dog("Toto", "BigFat")
    val cat = Cat("Coco", "Small")

    master.playWithPet(dog)
    master.playWithPet(cat)
}
