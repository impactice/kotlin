package models

/**
 * [개념 적용 ①: 상속(Inheritance)]
 * 추상 클래스인 Character를 상속받아 구체화된 '적(우주 해적 등)' 클래스입니다.
 * 
 * @property expReward 적을 처치했을 때 플레이어에게 지급할 경험치(EXP) 보상량.
 */
class Enemy(
    name: String,
    maxHp: Int,
    hp: Int = maxHp, // 기본적으로 생성 시 체력을 최대 체력으로 꽉 채워 생성합니다.
    attackPower: Int,
    val expReward: Int = 10
) : Character(name, maxHp, hp, attackPower) {

    /**
     * 데미지 처리 함수.
     * 체력이 0 미만으로 내려가지 않도록 Kotlin 표준 라이브러리의 `coerceAtLeast` 함수를 사용했습니다.
     */
    override fun takeDamage(amount: Int) {
        hp = (hp - amount).coerceAtLeast(0)
    }

    /**
     * 캐릭터가 살아있는지 체크하는 프로퍼티 오버라이딩.
     * 체력이 0보다 크면 살아있다고 판단합니다.
     */
    override val isAlive: Boolean
        get() = hp > 0
}
