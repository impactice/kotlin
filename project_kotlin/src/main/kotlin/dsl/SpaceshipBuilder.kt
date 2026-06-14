package dsl

import models.Spaceship
import models.SpaceshipModule

/**
 * [선택 구현: DSL(Domain Specific Language) 패턴과 빌더(Builder) 패턴]
 * 코틀린의 장점 중 하나인 타입 안전한 빌더(Type-Safe Builder)를 구현하기 위한 어노테이션입니다.
 * 이 어노테이션을 붙이면, 스코프(Scope) 바깥쪽의 수신 객체 메서드가 오인 호출되는 것을 막아줍니다.
 */
@DslMarker
annotation class SpaceshipDsl

/**
 * 우주선 객체를 직관적이고 가독성 좋게 생성하기 위한 빌더 클래스입니다.
 */
@SpaceshipDsl
class SpaceshipBuilder {
    private var name: String = "Unknown Ship"
    private var shipClass: models.ShipClass = models.ShipClass.EXPLORER
    private val modules = mutableListOf<SpaceshipModule>()
    private val crew = mutableListOf<String>()

    fun name(value: String) {
        this.name = value
    }

    fun shipClass(value: models.ShipClass) {
        this.shipClass = value
    }

    /**
     * [개념 적용 ③: 수신 객체 지정 람다(Lambda with Receiver)]
     * 매개변수 `setup: ModuleBuilder.() -> Unit`는 ModuleBuilder를 `this`로 가지는 람다입니다.
     * 덕분에 호출하는 쪽에서 `add(...)` 등을 `builder.add(...)` 없이 직접 호출할 수 있습니다.
     */
    fun modules(setup: ModuleBuilder.() -> Unit) {
        val builder = ModuleBuilder()
        builder.setup()
        modules.addAll(builder.build())
    }

    fun crew(setup: CrewBuilder.() -> Unit) {
        val builder = CrewBuilder()
        builder.setup()
        crew.addAll(builder.build())
    }

    fun build(): Spaceship {
        return Spaceship(name, shipClass, modules, crew)
    }
}

@SpaceshipDsl
class ModuleBuilder {
    private val modules = mutableListOf<SpaceshipModule>()
    fun add(module: SpaceshipModule) {
        modules.add(module)
    }
    fun build() = modules
}

@SpaceshipDsl
class CrewBuilder {
    private val crew = mutableListOf<String>()
    fun add(member: String) {
        crew.add(member)
    }
    fun build() = crew
}

/**
 * 전역으로 노출된 DSL 진입점 함수입니다.
 * 
 * [개념 적용 ③: 고차함수(Higher-Order Function)]
 * 람다 블록을 파라미터로 받아, 내부에서 빌더를 초기화하고 반환해줍니다.
 * 
 * @param setup 수신 객체가 SpaceshipBuilder인 람다식
 * @return 람다식에 의해 조립된 완전한 Spaceship 객체
 */
fun spaceship(setup: SpaceshipBuilder.() -> Unit): Spaceship {
    val builder = SpaceshipBuilder()
    builder.setup()
    return builder.build()
}
