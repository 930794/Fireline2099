import greenfoot.*;

public class Player extends Entity {
    private int shootCooldown = 20;

    public Player(int speed, int health, int attack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;
    }

    public void control() {
        if (Greenfoot.isKeyDown("left")) setLocation(getX() - speed, getY());
        if (Greenfoot.isKeyDown("right")) setLocation(getX() + speed, getY());
        if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - speed);
        if (Greenfoot.isKeyDown("down")) setLocation(getX(), getY() + speed);
    }
    private void autoShoot() {
    if (shootCooldown > 0) {
        shootCooldown--;
    } else {
        // Find the nearest enemy
        Enemy nearestEnemy = findNearestEnemy();
        getWorld().addObject(new bullets(5, 0, attack, nearestEnemy), getX(), getY());
        shootCooldown = 20;
    }
}

private Enemy findNearestEnemy() {
    java.util.List<Enemy> enemies = getWorld().getObjects(Enemy.class);
    Enemy nearest = null;
    double shortestDistance = Double.MAX_VALUE;

    for (Enemy enemy : enemies) {
        double distance = Math.hypot(enemy.getX() - getX(), enemy.getY() - getY());
        if (distance < shortestDistance) {
            shortestDistance = distance;
            nearest = enemy;
        }
    }

    return nearest;
}


    public void act() {
        control();
        if (!(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || 
              Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down"))) {
            autoShoot();
        }
    }
}
