package models

/**
 * [개념 적용 ①: 추상 클래스(Abstract Class)와 상속]
 * RPG 게임의 기본이 되는 '캐릭터'의 공통 속성(이름, 체력, 공격력)을 정의한 추상 클래스입니다.
 * 이 클래스를 단독으로 인스턴스화할 수는 없으며, Spaceship(플레이어)과 Enemy(적)가 이를 상속받아 구체화합니다.
 */
abstract class Character(
    val name: String,
    open var maxHp: Int,
    open var hp: Int,
    open var attackPower: Int
) {
    /**
     * [개념 적용 ①: 추상 메서드(Abstract Method)]
     * 캐릭터마다 데미지를 받는 공식이나 처리(예: 방어력 적용 등)가 다를 수 있으므로,
     * 자식 클래스에서 반드시 구현하도록 추상 메서드로 선언합니다.
     */
    abstract fun takeDamage(amount: Int)
    
    /**
     * 캐릭터가 살아있는지 여부를 판단하는 프로퍼티.
     * 자식 클래스에서 Getter를 커스텀하여 구현합니다.
     */
    abstract val isAlive: Boolean
}
