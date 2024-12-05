import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy class representing regular enemies.
 */
public class Enemy extends Entity {
    public Enemy(int speed, int health, int attack){
        
    }
    public void attackPlayer() {
        // Basic enemy AI to move towards the player
        Actor player = getWorld().getObjects(Player.class).get(0);
        if (player != null) {
            turnTowards(player.getX(), player.getY());
            move(2);
        }
    }
    public void act() {
        attackPlayer();
    }
}
