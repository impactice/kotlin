package models

abstract class CelestialBody(
    val name: String,
    val distance: Int
)

class Planet(
    name: String,
    distance: Int,
    val resources: Map<String, Int> // Resource name to amount
) : CelestialBody(name, distance)

class BlackHole(
    name: String,
    distance: Int,
    val dangerLevel: Int
) : CelestialBody(name, distance)
