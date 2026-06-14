package dsl

import models.Spaceship
import models.SpaceshipModule

@DslMarker
annotation class SpaceshipDsl

@SpaceshipDsl
class SpaceshipBuilder {
    private var name: String = "Unknown Ship"
    private val modules = mutableListOf<SpaceshipModule>()
    private val crew = mutableListOf<String>()

    fun name(value: String) {
        this.name = value
    }

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
        return Spaceship(name, modules, crew)
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

fun spaceship(setup: SpaceshipBuilder.() -> Unit): Spaceship {
    val builder = SpaceshipBuilder()
    builder.setup()
    return builder.build()
}
