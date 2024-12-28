import greenfoot.*;
public class bossEnemy extends Enemy {
    private int specialAttackCooldown = 100;
    private String specialAttackImage; 
    private int specialDamage = 50; 
    public bossEnemy(int speed, int health, int attack, String specialAttackImage) {
        super(speed, health, attack);
        this.specialAttackImage = specialAttackImage;
    }
    public void useSpecialAttack() {
        if (specialAttackCooldown > 0) {
            specialAttackCooldown--;
        } else {
            SpecialEffect effect = new SpecialEffect(specialAttackImage, specialDamage);
            getWorld().addObject(effect, getX(), getY());
            specialAttackCooldown = 200;
        }
    }
    public void act() {
        super.act();
        useSpecialAttack();
    }
}