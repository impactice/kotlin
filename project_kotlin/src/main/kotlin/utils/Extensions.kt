package utils

import models.Spaceship
import models.CelestialBody
import models.Planet

// 확장 함수 1: 특정 모듈 장착 여부 확인
fun Spaceship.hasModule(moduleName: String): Boolean {
    return this.modules.any { it.name == moduleName }
}

// 확장 함수 2: 자원이 있는 행성만 필터링 (컬렉션 + 고차함수)
fun List<CelestialBody>.filterPlanetsWithResources(): List<Planet> {
    return this.filterIsInstance<Planet>().filter { it.resources.isNotEmpty() }
}

// 확장 함수 3: 특정 거리 이내의 천체 스캔
fun List<CelestialBody>.scanNearby(maxDistance: Int): List<CelestialBody> {
    return this.filter { it.distance <= maxDistance }
}

// 스코프 함수 활용 예제
fun Spaceship.applyRepair(amount: Int) {
    this.apply {
        // 내구도 등 속성에 대한 로직 추가 가능
        println("수리 진행 중... ($amount)")
    }
}
