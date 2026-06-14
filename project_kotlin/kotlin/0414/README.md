# 프로퍼티와 초기화 

## 프로퍼티의 접근

### 기본 게터와 세터 지정

```
class User(_id: Int, _name: String, _age: Int) {
    // 프로퍼티
    val id: Int = _id
        get() = field //  field : 프로퍼티를 참조하는 변수
    var name:String = _name
    var age:Int = _age
}

fun main() {
    val user1 = User(1,"Kildong", 30)
    println("user1.id : ${user1.id}")
}
```


```
class User(_id: Int, _name: String, _age: Int) {
    // 프로퍼티
    val id: Int = _id
        get() = field //  field : 프로퍼티를 참조하는 변수

    var name:String = _name
        get() = field
        set(value){
            field = value
        }

    var age:Int = _age
}

fun main() {
    val user1 = User(1,"Kildong", 30)
    println("user1.id : ${user1.id} user1.name : ${user1.name}")
    user1.name = "Dooly"
    println("user1.id : ${user1.id} user1.name : ${user1.name}")
}
```

<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/da02b5b0-aa42-4d27-9e9b-12f16ec97eba" />


### 커스텀 게터와 세터의 사용
```
class User(_id: Int, _name: String, _age: Int) {
    // 프로퍼티
    val id: Int = _id
        get() = field //  field : 프로퍼티를 참조하는 변수

    var name:String = _name
        get() = field
        set(value){
            field = value
            println("이름을 $field 로 바꿉니다.")
        }

    var age:Int = _age
}

fun main() {
    val user1 = User(1,"Kildong", 30)
    //user1.id = 20 //이거는 안 된대
    println("user1.id : ${user1.id} user1.name : ${user1.name}")
    user1.name = "Dooly"
    println("user1.id : ${user1.id} user1.name : ${user1.name}")
}
```

<img width="1919" height="1027" alt="image" src="https://github.com/user-attachments/assets/9c24f5ad-7152-40f8-9def-482fc911af6a" />

### 임시적인 보조 프로퍼티의 사용

```
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
```

<img width="1919" height="1027" alt="image" src="https://github.com/user-attachments/assets/8fe7703c-a24b-4199-acd3-4b8bbe380480" />

### 프로퍼티를 이용한 나이 속이기 예제 (시험 문제 1순위)

```
// 커스텀 세터: 나이 속이기 예제
class Member(val name: String, initialAge: Int) {
    var age: Int = initialAge
        set(value) {
            // 입력값에 따라 다르게 저장하는 커스텀 세터 구현
            
        }
    val introduction: String
        // 커스텀 게터 구현: 나이를 출력할 때 설명 문자열 반환
        
}

fun main() {
    val m1 = Member("김영서", 15)   // 18 미만 → 18로
    val m2 = Member("박지현", 25)   // 18~30 → 25 유지
    val m3 = Member("최도윤", 35)   // 30 초과 → 32로

    println(m1.introduction)  // 김영서 (18세)
    println(m2.introduction)  // 박지현 (25세)
    println(m3.introduction)  // 최도윤 (32세)

    m1.age = 10   // 다시 설정 → 18로 조정
    println("재설정 후: ${m1.age}")
}
```
