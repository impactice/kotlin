class InvalidAmountException(message:String) : Exception(message)

fun main(){
    var name = "kildong123"
    try {
        validateName(name)
    }catch (e:InvalidAmountException){
        println(e.message)
    }catch (e: Exception) {
        println(e.message)
    }
}

fun validateName(name: String){
    if(name.matches(Regex(".*\\d+.*"))) {
        throw InvalidAmountException("이름에 숫자가 포함되어 있습니다.")
    }

}