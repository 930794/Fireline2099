import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class bossEnemy extends Enemy {
    private int specialAttackCooldown = 100; // Cooldown for special attack
    private String specialAttackImage; // Path to the special attack image
    private int aoeDamage = 50; // Damage dealt by the AOE attack

    public bossEnemy(int speed, int health, int attack, String specialAttackImage) {
        super(speed, health, attack); // Call superclass constructor
        this.specialAttackImage = specialAttackImage; // Store the attack image path
    }

    public void useSpecialAttack() {
        if (specialAttackCooldown > 0) {
            specialAttackCooldown--;
        } else {
            // Create a visual representation of the special attack
            SpecialEffect effect = new SpecialEffect(specialAttackImage, aoeDamage);
            getWorld().addObject(effect, getX(), getY());
            
            // Reset the cooldown
            specialAttackCooldown = 200; // Longer cooldown for boss specials
        }
    }

    public void act() {
        super.act(); // Call superclass act method
        useSpecialAttack();
    }
}
