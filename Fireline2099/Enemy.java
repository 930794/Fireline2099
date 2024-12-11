import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy class representing regular enemies.
 */
public class Enemy extends Entity {
    public Enemy(int speed, int health, int attack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;        
    }
    public void attackPlayer() {
        // Check if there is at least one Player in the world
        if (!getWorld().getObjects(Player.class).isEmpty()) {
            // Get the first Player object
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            
            if (player != null) {
                // Move toward the Player
                turnTowards(player.getX(), player.getY());
                move((int) Math.round(Math.cos(Math.toRadians(getRotation())) * speed), 
                     (int) Math.round(Math.sin(Math.toRadians(getRotation())) * speed));

                // Check for collision with the Player
                if (intersects(player)) {
                    player.takeDamage(attack); // Reduce Player's health
                }
            }
        }
    }
    public void act() {
        attackPlayer();
    }
}
