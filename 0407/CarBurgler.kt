open class Car protected constructor(_year:Int,_model:String,_power:String,_wheel:String){
    private  var year = _year
    public var model = _model
    protected  open var power = _power
    internal var wheel = _wheel

    protected fun start(key:Boolean){
        if(key)
            println("Start the Engine!")
    }
    class Driver(_name:String,_license:String){
        private var name = _name
        var license = _license
        internal fun driving() =println("[Driver] drining() - $name" )
    }
}
class Tico(_year:Int,_model:String,_power: String,_wheel: String,var name:String, private var key:Boolean):Car(_year,_model,_power,_wheel){
    override var power: String = "50hp"
    val driver = Driver(name,"first class")
    constructor(_name:String, _key:Boolean):this(2014, "basic","100hp","normal",_name,_key ) {
        name = _name
        key = _key
    }
    fun access(password: String){
        if(password == "gotico") {
            println("============[Tico] access()========")
            //super.year //private라서 접근이 안됨
            println("super.model : ${super.model}")
            println("super.power : ${super.power}")
            println("super.wheel : ${super.wheel}")
            super.start(key)

            //driver.name //접근이 불가
            println("Driver().license = ${driver.license}")
            driver.driving()
        }else{
            println("You're a burglar!")
        }
    }
}
class Burglar(){
    fun steal(anycar: Any) {
        if(anycar is Tico){
            println("============[Burglar] steal()========")
            //anycar.power //protected라서 접근 불가
            //anycar.year //private라서 접근 불가

            println("anycar.model : ${anycar.model}")
            println("anycar.name : ${anycar.name}")
            println("anycar.wheel : ${anycar.wheel}")

            //
            println("anycar.Driver.license = ${anycar.driver.license}")
            anycar.driver.driving()
            //Car.start() //protect라서 안됨
            anycar.access("dontknow")
        }else{
            println("Nothing steel")
        }
    }
}
fun main(){
    val tico = Tico("kildong", true)
    tico.access("gotico")

    val burglar = Burglar()
    burglar.steal(tico)
}