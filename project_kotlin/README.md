# 🚀 Space Explorer (우주 탐사선 시뮬레이터) - Ver 1.0

터미널로 다운하기
```
winget install JetBrains.IntelliJIDEA.Community
```

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-blue.svg?logo=kotlin)
![Compose Desktop](https://img.shields.io/badge/Compose_for_Desktop-1.6.0-green.svg)
![Coroutines](https://img.shields.io/badge/Coroutines-1.8.0-purple.svg)
**Space Explorer**는 Kotlin의 핵심 객체지향 및 함수형 프로그래밍 개념을 적극 활용하여 개발된 데스크톱 기반 우주 개척 시뮬레이션입니다. 
단순한 텍스트 CLI 환경을 넘어, **Compose for Desktop**을 활용한 모던 GUI와 **Kotlin Coroutines**을 통한 비동기 채굴 시스템을 도입하여 기술적 완성도를 높였습니다.

## ✨ 주요 기능 (Features)
- **우주선 커스텀 건조 (DSL)**: 코틀린의 DSL(Domain Specific Language) 빌더 패턴을 사용하여 우주선과 장착 모듈, 승무원을 직관적이고 구조적으로 초기화합니다.
- **백그라운드 자원 채굴 (Coroutines)**: 메인 UI 조작과 독립적으로 동작하는 비동기 코루틴 루프가 플레이어의 광물 자원을 실시간으로 채굴합니다.
- **섹터 스캔 및 탐사**: 객체지향의 다형성(Polymorphism)을 활용해 `Planet`, `BlackHole` 등 다양한 우주 천체를 스캔하고, 컬렉션 확장 함수를 통해 자원이 있는 행성만 필터링합니다.
- **데이터 세이브 & 로드 (File I/O)**: `use` 블록을 활용한 안전한 자원 해제 방식으로 함선의 현재 연료, 광물, 상태를 로컬 디스크(`save.txt`)에 저장하고 복원합니다.

## 🛠 기술 스택 및 개념 적용 내역

### 1. Object-Oriented Programming (객체지향 설계)
- `CelestialBody` 추상 클래스와 이를 상속받는 `Planet`, `BlackHole` 구현 클래스
- `SpaceshipModule` 인터페이스 및 `Engine`, `MiningLaser` 등 다양한 커스텀 파츠 다형성 구현

### 2. Functional Programming & Collections (함수형 프로그래밍)
- `filter`, `any`, `filterIsInstance` 등 고차함수를 활용한 우주 공간 스캔 로직
- `apply`, `let` 등 코틀린 스코프 함수(Scope Functions)를 활용한 간결한 객체 상태 관리
- 커스텀 확장 함수(`List<CelestialBody>.filterPlanetsWithResources()`)를 통한 컬렉션 제어

### 3. Advanced Kotlin (심화 기술)
- **Type-safe Builders (DSL)**: `@DslMarker` 애노테이션을 활용한 커스텀 우주선 조립 빌더(`spaceship { ... }`) 구현
- **Coroutines & StateFlow**: `CoroutineScope.launch`와 `StateFlow`를 연동하여 비동기 백그라운드 작업의 로그를 Compose UI에 실시간(Reactive)으로 렌더링

## 🚀 실행 방법 (How to Run)
이 프로젝트는 **Gradle Toolchain(Java 17)**을 사용하므로, 사용자의 PC에 자바 환경이 완벽히 구성되어 있지 않아도 Gradle이 알아서 필요한 JDK를 임시로 다운로드하여 실행해 줍니다. (Foojay 플러그인 적용 완료)
```bash
# 1. 레포지토리 클론
git clone https://github.com/사용자계정/SpaceExplorer.git
cd SpaceExplorer
# 2. Gradle을 통한 앱 실행 (Compose Desktop 윈도우 창 팝업)
./gradlew run
```
