private class PrivateCalss{
    var i = 1
    private var j = 2
    private fun privateFunc() {
        j+=1
        println(j)
    }
    fun access() {
        privateFunc()
    }
}
class Other{
    //val pc = PrivateCalss() // 이렇게는 못 만듬
    private val pc = PrivateCalss()
}
fun main() {
    val pc = PrivateCalss() // 생성가능
    println(pc.i)
    // println(pc.j) // 출력이 안됨(접근이 안됨) // private 멤버 접근 불가
    // pc.privateFunc() // private 멤버 접근 불가
    pc.access() // 접근 가능
}