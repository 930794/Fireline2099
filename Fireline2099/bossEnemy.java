import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BossEnemy class representing a more powerful enemy.
 */
public class bossEnemy extends Enemy {
    private int specialAttackCooldown = 100;
    private String specialAttack;

    public bossEnemy(int speed, int health, int attack, String specialAttack) {
        super(speed, health, attack); // Call superclass constructor
        this.specialAttack = specialAttack;
    }

    public void useSpecialAttack() {
        if (specialAttackCooldown > 0) {
            specialAttackCooldown--;
        } else {
            System.out.println("Boss performs special attack!");
            specialAttackCooldown = 100;
        }
    }

    public void act() {
        super.act(); // Call superclass act method
        useSpecialAttack();
    }
}
