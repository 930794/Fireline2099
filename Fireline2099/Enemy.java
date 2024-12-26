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
    if (!getWorld().getObjects(Player.class).isEmpty()) {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        
        if (player != null) {
            // Check for nearby enemies and adjust movement to avoid clustering
            for (Enemy nearbyEnemy : getWorld().getObjects(Enemy.class)) {
                if (nearbyEnemy != this && Math.hypot(getX() - nearbyEnemy.getX(), getY() - nearbyEnemy.getY()) < 30) {
                    move((int) Math.round(Math.cos(Math.toRadians(getRotation() + 90)) * speed), 
                         (int) Math.round(Math.sin(Math.toRadians(getRotation() + 90)) * speed));
                }
            }

            // Move toward the player
            turnTowards(player.getX(), player.getY());
            move((int) Math.round(Math.cos(Math.toRadians(getRotation())) * speed), 
                 (int) Math.round(Math.sin(Math.toRadians(getRotation())) * speed));

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
