class Bird {
    //property - 생성자가 없을 때는 초기값을 할당해야 함!
    var name:String = "bird1"  // 초기값들은 꼭 들어가 있어야 한다 -> 없으면 에러가 남
    var wing:Int = 2
    var beak:String = "short"
    var color:String = "blue"

    //secondary constructor
    constructor(name:String, wing:Int, beak:String, color:String) {
        this.name = name
        this.wing = wing
        this.beak = beak
        this.color = color
    }

    //method
    fun fly() = println("Fly wing : $wing")
    fun sing(vol:Int) = println("Singing $vol")
}
fun main() {
    val coco = Bird(name = "Coco", wing = 2, beak = "short",color = "red")
    //coco.color = "red"

    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(10)

}