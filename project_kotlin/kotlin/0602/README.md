
# 컬렉션과 표준 함수 

## Part 1. 컬렉션의 구조와 기본 

```
import java.util.*;

fun main(){
    //학생 성적을 가변 map으로 만들기
    val score : MutableMap<String, Int> = mutableMapOf(
        "Alice" to 85,
        "Bob" to 92,
        "Charlie" to 78,
    )
    println(score)
}
```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/399abe6f-ac8f-4f14-b307-f1f583eff4ee" />

```
import java.util.*;

fun main(){
    //학생 성적을 가변 map으로 만들기
    val scores: MutableMap<String, Int> = mutableMapOf(
        "Alice" to 85,
        "Bob" to 92,
        "Charlie" to 78,
    )
    println(scores)
    //학생 추가
    scores["David"] = 88
    println(scores)
    // 점수 수정
    scores["Alice"] = 58
    println(scores)
    //모든 학생의 이름과 성적 출력
    for((name, score) in scores)
        println("$name : $scores")
}
```

<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/c7594a8b-62a0-40ad-a64d-b5c49c955de4" />

```
import java.util.*;

fun main(){
    //학생 성적을 가변 map으로 만들기
    val scores: MutableMap<String, Int> = mutableMapOf(
        "Alice" to 85,
        "Bob" to 92,
        "Charlie" to 78,
    )
    println(scores)
    //학생 추가
    scores["David"] = 88
    println(scores)
    // 점수 수정
    scores["Alice"] = 58
    println(scores)
    //모든 학생의 이름과 성적 출력
    for((name, score) in scores)
        println("$name : $scores")
    //성적이 80점 이상 학생을 골라 set으로 저장 후 출력
    val highScores:Set<String> =scores.filter{it.value >= 80}.keys.toSet()
    println("80점 이상 : $highScores")
}
```

<img width="1919" height="1025" alt="image" src="https://github.com/user-attachments/assets/3ce14bfc-60f7-4bc7-8352-60ccff4ca0a7" />

```
import java.util.*;

fun main(){
    //학생 성적을 가변 map으로 만들기
    val scores: MutableMap<String, Int> = mutableMapOf(
        "Alice" to 85,
        "Bob" to 92,
        "Charlie" to 78,
    )
    println(scores)
    //학생 추가
    scores["David"] = 88
    println(scores)
    // 점수 수정
    scores["Alice"] = 58
    println(scores)
    //모든 학생의 이름과 성적 출력
    for((name, score) in scores)
        println("$name : $scores")
    //성적이 80점 이상 학생을 골라 set으로 저장 후 출력
    val highScores:Set<String> =scores.filter{it.value >= 80}.keys.toSet()
    println("80점 이상 : $highScores")

    // [문제 2] 중복 학번이 있는 리스트에서 유일한 학번만 남기시오
    val studentIds = listOf(1001, 1002, 1001, 1003, 1002, 1004)
    val uniqueIds: Set<Int> = studentIds.toSet()
    println("유일한 학번: $uniqueIds")
    println("중복 제거 후 개수: ${uniqueIds.size}")
}
```
<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/676da5b6-6362-4a4f-b9de-f51f5fcd2dee" />

## Part 2. 컬렉션 확장 함수와 Sequence

```
import java.util.*;

fun main(){
    //학생 성적을 가변 map으로 만들기
    val scores: MutableMap<String, Int> = mutableMapOf(
        "Alice" to 85,
        "Bob" to 92,
        "Charlie" to 78,
    )
    println(scores)
    //학생 추가
    scores["David"] = 88
    println(scores)
    // 점수 수정
    scores["Alice"] = 58
    println(scores)
    //모든 학생의 이름과 성적 출력
    for((name, score) in scores)
        println("$name : $scores")
    //성적이 80점 이상 학생을 골라 set으로 저장 후 출력
    val highScores:Set<String> =scores.filter{it.value >= 80}.keys.toSet()
    println("80점 이상 : $highScores")

    // [문제 2] 중복 학번이 있는 리스트에서 유일한 학번만 남기시오
    val studentIds = listOf(1001, 1002, 1001, 1003, 1002, 1004)
    val uniqueIds: Set<Int> = studentIds.toSet()
    println("유일한 학번: $uniqueIds")
    println("중복 제거 후 개수: ${uniqueIds.size}")

    studentIds.forEach{ print("$it") }
    println()

    studentIds.forEachIndexed { i, v -> println("[$i] = $v")  }

    val result = studentIds.onEach{print("처리 전 : $it")}.filter{ it%2==0}
    println(result)
    println(studentIds)
}
```

<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/7c332fd0-ebe7-4ac3-8f36-02ebf6ca5027" />

```
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val listWithNull = listOf(1, null, 3, null, 5, 6)

    //map : 각 요소를 변환, 원본과 같은 개수의 리스트를 반환
    val doubled = list.map{it*2}
    println(doubled)
    val withIndex = list.mapIndexed {i,v -> i * v}
    println(withIndex)
    println(listWithNull.mapNotNull {it?.times(2)})
    
}
```

<img width="1919" height="1028" alt="image" src="https://github.com/user-attachments/assets/6f8d463d-b69f-4ef0-94a0-570de8780a72" />

### 2-4. 집계: fold / reduce / count / max / min

여러 요소를 **하나의 값으로 합산**하는 함수들이다.

#### count / max / min — 간단한 집계
```
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val listWithNull = listOf(1, null, 3, null, 5, 6)

    //map : 각 요소를 변환, 원본과 같은 개수의 리스트를 반환
    val doubled = list.map{it*2}
    println(doubled)
    val withIndex = list.mapIndexed {i,v -> i * v}
    println(withIndex)
    println(listWithNull.mapNotNull {it?.times(2)})

    val list2 = list.groupBy { if(it%2==0) "even" else "odd"}
    println(list2)

    // 문자열 첫 글자로 그룹화
    val fruits = listOf("apple", "apricot", "banana", "blueberry", "cherry")
    println(fruits.groupBy { it.first() })
}
```

<img width="1919" height="1035" alt="image" src="https://github.com/user-attachments/assets/ec224d1a-3d23-42a4-9c9c-6aa66cc86111" />

#### fold — 초기값이 있는 누적 연산

`fold(초기값) { 누적값, 다음요소 -> 연산식 }` 형태로 사용한다.

왼쪽부터 오른쪽으로 각 요소를 순서대로 처리하며 값을 누적한다.

### 2-5. 검사: all / any / none / contains

컬렉션의 요소들이 조건을 만족하는지 확인하여 `Boolean`을 반환한다.

### 2-6. 정렬, 분리, 결합

### 2-7. Sequence — 지연 평가(Lazy Evaluation)

```
fun main(){
    val list = listOf(1, 2, 3, 4, 5)

// 즉시 평가: map → [1, 4, 9, 16, 25] 리스트 생성 → filter → [4, 16] 리스트 생성
// 중간 리스트가 2개 만들어진다
    val result = list
        .map { println("map($it)"); it * it }       // ① 5개 모두 map 처리
        .filter { println("filter($it)"); it % 2 == 0 } // ② 5개 모두 filter 처리
    println(result)

    val listSeq = list.asSequence().map{println("map($it)"); it * it}
        .filter { println("filter($it)"); it % 2 == 0 }
}
```

<img width="1919" height="1032" alt="image" src="https://github.com/user-attachments/assets/c752215e-f9ba-486d-995d-32fee7b277a4" />

## Part 3. 코틀린 표준(스코프) 함수

### 3-1. 스코프 함수란? — 왜 필요한가?




### 3-9. 어떤 함수를 언제 쓸까? — 실전 판단 기준 (스코프는 시험에 낸 적이 있대)


