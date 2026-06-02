fun main(){
    val naums = generateSequence(1){it + 1}.take(15).toList().filter{it % 2 == 1}.map{it*it}.toList()
    println(naums)

    generateSequence ( 1 to 1 ){it.second to it.first+it.second}.map{it.first}


}