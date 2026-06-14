package models

/**
 * 게임 상에서 발견할 수 있는 '천체'들의 공통 속성을 묶어둔 추상 클래스입니다.
 */
abstract class CelestialBody(
    val name: String,
    val distance: Int
)

/**
 * 상속을 활용하여 구체적인 '행성' 객체를 정의합니다.
 */
class Planet(
    name: String,
    distance: Int,
    val resources: Map<String, Int>
) : CelestialBody(name, distance)

/**
 * 상속을 활용하여 구체적인 '블랙홀' 객체를 정의합니다.
 */
class BlackHole(
    name: String,
    distance: Int,
    val dangerLevel: Int
) : CelestialBody(name, distance)
