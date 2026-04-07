import org.intellij.lang.annotations.Language

// 상속 가능한 클래스가 되려면 open 사용하기
open class Bird(var name: String, var wing:Int, var beak: String, var color:String){
    fun fly() = println("fly wing: $wing")
    open fun sing(vol:Int) = println("Sing vol: $vol") //open추가 -> 오버라이딩 가능한 메서드
}
// 주 생성자를 사용하는 상속
class Lark(name:String, wing:Int, beak:String, color:String):Bird(name, wing, beak, color){ //Bird로 부터 상속을 받는다
    fun singHitone() = println("happy song!") // 메서드 추가
}
// 부 생성자를 사용하는 상속
class Parrot:Bird{
    val language: String // 새로 추가된 프로퍼티
    constructor(name:String, wing:Int, beak:String, color:String, language: String):super(name,wing,beak,color){ //기반 클래스 생성자 호출
        this.language = language // 부생성자에서 초기화
    }
    fun speak() = println("speak! $language")
    override fun sing(vol:Int) { //오버라이딩 한다고 써줘야 한다
        super.sing(vol)
        println("I'm a parrot! The volume level is $vol")
        speak()
    }
}
fun main() {
    val coco = Bird("mybird", 2, "short", "blue")
    val lark = Lark("mylark", 2, "long", "brown")
    val parrot = Parrot("myparrot", 2, "short", "multiple", "korean") // 프로퍼티가 추가됨

    println("Coco: ${coco.name}, ${coco.wing}, ${coco.beak}, ${coco.color}")
    println("Lark: ${lark.name}, ${lark.wing}, ${lark.beak}, ${lark.color}")
    println("Parrot: ${parrot.name}, ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language}")

    lark.singHitone()  // 새로 추가된 메서드가 사용 가능
    parrot.speak()
    lark.fly()
}