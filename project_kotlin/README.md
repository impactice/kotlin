# 🚀 Space Explorer (우주 탐사선 시뮬레이터) - Ver 1.1

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-blue.svg?logo=kotlin)
![Compose Desktop](https://img.shields.io/badge/Compose_for_Desktop-1.6.0-green.svg)
![Coroutines](https://img.shields.io/badge/Coroutines-1.8.0-purple.svg)

**Space Explorer**는 Kotlin의 핵심 객체지향 및 함수형 프로그래밍 개념을 적극 활용하여 개발된 데스크톱 기반 우주 개척 시뮬레이션 게임입니다. 
단순한 텍스트 CLI 환경을 넘어, **Compose for Desktop**을 활용한 모던 GUI 화면과 **Kotlin Coroutines** 기반의 실시간 처리, 그리고 `Sealed Class`를 활용한 동적 전투 시스템을 구현했습니다.

## ✨ 주요 기능 및 업데이트 (Ver 1.1)

- **[NEW] 동적 메인 뷰포트 (Sealed Class)**: 탐사 상태(`Exploring`)와 전투 상태(`Combat`)를 Sealed Class로 완벽히 분리하여, 적과 조우하면 즉시 붉은 경고 화면과 체력 게이지가 나타나도록 상태 기반 UI(Declarative UI)를 구축했습니다.
- **[NEW] 턴제 전투 시스템**: 섹터 스캔 시 확률적으로 '우주 해적선' 등의 적과 조우하며, 서로 데미지를 주고받거나 도망치는 전투 로직을 도입했습니다. (다형성 활용)
- **우주선 커스텀 건조 (DSL)**: 코틀린의 DSL 빌더 패턴(`spaceship { ... }`)을 사용하여 우주선과 장착 모듈, 승무원을 직관적이고 구조적으로 초기화합니다.
- **백그라운드 자동 채굴 (Coroutines)**: 메인 UI 조작과 독립적으로 동작하는 비동기 코루틴 루프가 플레이어의 광물 자원을 백그라운드에서 실시간으로 채굴합니다.
- **데이터 세이브 & 로드 (File I/O)**: `use` 블록을 활용한 안전한 자원 해제 방식으로 함선의 현재 HP, 연료, 광물 상태를 로컬 디스크(`save.txt`)에 저장하고 복원합니다.

## 🛠 기술 스택 및 핵심 개념

### 1. Advanced Kotlin (심화 기능)
- **State Management**: `Sealed Class`(`GameState`)를 활용해 게임 상태에 따라 UI 화면이 즉각적으로 변하도록 아키텍처를 설계했습니다.
- **Type-safe Builders (DSL)**: `@DslMarker` 애노테이션을 활용한 커스텀 빌더 패턴.
- **Coroutines & StateFlow**: `CoroutineScope.launch`와 `StateFlow`를 연동하여 비동기 전투 로그와 채굴 내역을 실시간 렌더링.

### 2. Object-Oriented Programming (객체지향과 다형성)
- `CelestialBody` 추상 클래스를 상위 타입으로 두고, 평화로운 행성(`Planet`)과 전투가 가능한 적(`Enemy`) 클래스가 이를 상속받아 다형성을 완벽히 구현했습니다.

### 3. Functional Programming (함수형 프로그래밍)
- `filter`, `any` 등 고차함수를 활용한 컬렉션 제어.
- `apply`, `let` 등 스코프 함수(Scope Functions)를 활용한 간결한 객체 캡슐화.

## 🚀 실행 방법 (How to Run)

이 프로젝트는 **Gradle Toolchain(Java 17)**을 적용해 두었으므로, 사용자의 PC에 자바 환경이 맞지 않더라도 Gradle이 알아서 필요한 버전의 JDK를 임시로 다운로드하여 실행해 줍니다. 

```bash
# 1. 레포지토리 클론
git clone https://github.com/사용자계정/SpaceExplorer.git
cd SpaceExplorer

# 2. Gradle을 통한 앱 실행 (Compose Desktop 윈도우 창 팝업)
./gradlew run
```

---
*개발: [본인 이름 기입]* | *버전: Ver 1.1*
