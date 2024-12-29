import greenfoot.*;

public class Enemy extends Entity {
    public Enemy(int speed, int health, int attack) { // Constructs enemy object
        // Speed, health, and attack values stored with entity class
        this.speed = speed;
        this.health = health;
        this.attack = attack;        
    }
    public void attackPlayer() { // Move to and attack player
    if (!getWorld().getObjects(Player.class).isEmpty()) { // Checks to see if a player objects exists
        Player player = (Player) getWorld().getObjects(Player.class).get(0); // Gets the first (and only) player object
        if (player != null) { // Checks to see if the player is not null
            for (Enemy nearbyEnemy : getWorld().getObjects(Enemy.class)) { // Loops through all enemy objects in the world
                if (nearbyEnemy != this && Math.hypot(getX() - nearbyEnemy.getX(), getY() - nearbyEnemy.getY()) < 30) { // If the nearest enemy is not itself, and if the nearest enemy is less than 30 units close (calculated with euclidean distance)
                    move((int) Math.round(Math.cos(Math.toRadians(getRotation() + 90)) * speed),(int) Math.round(Math.sin(Math.toRadians(getRotation() + 90)) * speed)); // Moves the enemy in a perpendicular direction relative to its current rotation to avoid touching another enemy
                }
            }
            turnTowards(player.getX(), player.getY()); // Turns towards player's position
            move((int) Math.round(Math.cos(Math.toRadians(getRotation())) * speed),(int) Math.round(Math.sin(Math.toRadians(getRotation())) * speed)); 
            // Moves towards the current rotation of the enemy (which is where the player is)
            // With radian values, cos gets x value, and sin gets y value, and they are multiplied by speed to scale the displacement
            if (intersects(player)) { // Checks to see if enemy is touching player
                player.takeDamage(attack); // Player takes damage according to enemy attack value
            }
        }
    }
    }
    public void act() {
        attackPlayer(); // Attacks player
    }
}