import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BossEnemy class representing a more powerful enemy.
 */
public class bossEnemy extends Enemy {
    private int specialAttackCooldown = 100;
    public bossEnemy(int speed, int health, int attack, String specialAttack){
        
    }
    public void useSpecialAttack() {
        // Special attack logic
        if (specialAttackCooldown == 0) {
            System.out.println("Boss performs special attack!");
            specialAttackCooldown = 100;
        }
        specialAttackCooldown--;
    }

    public void act() {
        super.act();
        useSpecialAttack();
    }
}
