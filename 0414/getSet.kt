class User(_id: Int, _name: String, _age: Int) {
    // 프로퍼티
    val id: Int = _id
        get() = field //  field : 프로퍼티를 참조하는 변수
    private var n:String? = null //임시 프로퍼티
    var name:String = _name
        get() {
            if(n == null) field = "NONAME" // get
            return field ?: throw AssertionError("Asserted by others")
        }
        /*
        get() = field
        set(value){
            field = value
            println("이름을 $field 로 바꿉니다.")
            //field = value.uppercase(getDefault()) //????? 모르겠
        }*/

    var age:Int = _age
}

fun main() {
    val user1 = User(1,"Kildong", 30)
    //user1.id = 20 //이거는 안 된대
    println("user1.id : ${user1.id} user1.name : ${user1.name}")
    user1.name = "Dooly"
    println("user1.id : ${user1.id} user1.name : ${user1.name}")
}