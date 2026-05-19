package section2

class MyClass<T>{
    var myPorp:T
    constructor(myPorp:T){
        this.myPorp = myPorp
    }
}

fun <T> find(a:Array<T>,Target:T):Int{
    for(i in a.indices){
        if(a[i] == Target){
            return i
        }
    }
    return -1
}
fun<T> add(a:T,b:T,op:(T,T)->T):T{
    return op(a,b);
}

fun main() {
    val arr1:Array<String> = arrayOf("apple","banana","pineapple")
    val arr2:Array<Int> = arrayOf(1,2,3,4)
    println(find<String>(arr1,"banana"))
    println(find<Int>(arr2,4))
    val result = add(3, 4) { a, b -> a + b }
    val result1 = add(3.14, 4.3) { a, b -> a + b }
}