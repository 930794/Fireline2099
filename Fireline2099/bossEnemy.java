import greenfoot.*;

public class bossEnemy extends Enemy {
    private int specialAttackCooldown = 200; // Special attack cooldown of 200 frames
    private String specialAttackImage; // Instance variable to hold special attack image
    private int specialDamage = 10; // Sets special attack damage to 10
    
    public bossEnemy(int speed, int health, int attack, String specialAttackImage) { // Creats boss enemy with special attack
        super(speed, health, attack); // Calls and reuses enemy constructor class to initialize speed, health, and attack
        this.specialAttackImage = specialAttackImage; 
    }
    public void useSpecialAttack() { // Method to use the special attack
        if (specialAttackCooldown > 0) { // Checks if cooldown is greater than 0 to reduce timer
            specialAttackCooldown--; // Subtracts 1 from cooldown
        } else { // If cooldown is 0 or less than 0
            SpecialEffect effect = new SpecialEffect(specialAttackImage, specialDamage); // Creats special attack effect object with the special image
            getWorld().addObject(effect, getX(), getY()); // Adds effect to where the boss enemy is
            specialAttackCooldown = 200; // Resets cooldown
        }
    }
    public void act() {
        super.act(); // Uses the enemy parent class act method, just moves to and attacks the player
        useSpecialAttack(); // Tries to use special attack
    }
}