import greenfoot.*;

public class Enemy extends Entity {
    public Enemy(int speed, int health, int attack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;
    }

    public void act() {
        Actor player = getWorld().getObjects(Player.class).get(0);
        if (player != null) {
            turnTowards(player.getX(), player.getY());
            move(speed);
        }
    }
}
