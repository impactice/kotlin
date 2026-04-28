
# 위임과 지연 초기화

## 2. 지연 초기화 

### 지연 초기화가 필요한 이유 

### lateinit을 사용한 지연 초기화

```
// 작년에 초기화 되는 곳 시험에 나옴
class Person{
    //var name: String = "" // 초기화
    lateinit var name: String // 이렇게 하면 초기화 하지 않고 넘어감 // 지연 초기화를 위한 선언
    fun test() {
        if(!::name.isInitialized) { // 프로티의 초기화 여부를 판단
            println("not initialized")
        }else{
            println("initialized")
        }
    }
}
fun main() {
    val p1 = Person() // name 프로퍼티는 여기서 초기화되지 않음
    p1.test()
    p1.name = "Pororo" //name 프로퍼티가 초기화 되는 시점
    p1.test()
    println("${p1.name}")
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/80a6a4a9-cb08-4ba7-b18a-178bb44c2c9b" />

