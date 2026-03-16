# 01.코틀린 시작하기 

## 코틀린이란? 
- JeBrains가 만든 현대적 프로그래밍 언어
- JVM(Java Virtual Machime) 위에서 동작하며 Java와 100% 상호 운용됨
- java의 단점을 보완을 제공하기 위해 만들어짐
- 멀티 플랫폼 개발 언어 

## Kotlin의 장점(Java와 비교)
- NULL SAFETY
    - 변수에 null을 허용하지 않으므로 컴파일 타임에 NullPointerException방지      
- 간결한 문법
    - Java 대비 코드량이 약 40% 감소하며 보일러플레이트 코드가 대폭 줄어듬 
- JAVA 상호 운용
    -  기존 Java 라이브러리와 프레임워크를 그대로 사용가능
- 코루틴 
    - 비동기 프로그래밍을 간단하고 직관적으로 처리

# 과제 제출 코드 

📌 요구사항

- 불변 변수 studentName에 본인의 이름을 넣으세요.
- 가변 변수 score에 100을 넣고, 다음 줄에서 95로 변경하세요.
- 문자열 템플릿으로 출력: "[이름]님의 점수는 [점수]점입니다."
- studentName이 null이 될 수 있다고 가정하고 길이를 안전하게 출력하세요.

```
fun main() {
    val studentName: String? = "정원희"
    var score: Int = 100
    score = 95
    println("${studentName}님의 점수는 ${score}점입니다.")  // println("$studentName 님의 점수는 $score 점입니다")
    println("이름 길이: ${studentName?.length ?:0}")
}
```

