import greenfoot.*;

public abstract class Entity extends Actor {
    // Instance variables health, speed, and attack that can only be used by this class and its subclasses
    protected int health;
    protected int speed;
    protected int attack;

    public void takeDamage(int damage) { // Selected entity takes damage for specified amount
        health -= damage; // Subtracts damage from health
        if (health <= 0) { // If health is less than or equal to 0, the specific entity is removed
            getWorld().removeObject(this); // Removes specific entity
            if (this instanceof Enemy || this instanceof bossEnemy) { // Checks to see if specific entity is an enemy or boss enemy
                Greenfoot.playSound("EnemyDeath.mp3"); // Play enemy death sound
                MyWorld.numberofEnemies--; // Reduces number of enemies
                MyWorld.score++; // Increases user score
            }
        }
    }
    public void move(int dx, int dy) { // Method to move entities
        int oldX = getX(); // Gets current x value
        int oldY = getY(); // Gets current y value
        setLocation(oldX + dx, oldY + dy); // Displacement by a value of dx and dy
        if (isTouching(cover.class)) { // Checks to see if entity is touching cover
            setLocation(oldX, oldY); // If entity is touching cover, it does not move (goes back to old x and old y)
        }
    }
}