import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Entity {
    private int shootCooldown = 20;
    
    public Player(int speed, int health, int attack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;
    }

    public void control() {
        if (Greenfoot.isKeyDown("left")||Greenfoot.isKeyDown("a")) move(-speed, 0);
        if (Greenfoot.isKeyDown("right")||Greenfoot.isKeyDown("d")) move(speed, 0);
        if (Greenfoot.isKeyDown("up")||Greenfoot.isKeyDown("w")) move(0, -speed);
        if (Greenfoot.isKeyDown("down")||Greenfoot.isKeyDown("s")) move(0, speed);
    }

    private void autoShoot() {
        if (shootCooldown > 0) {
            shootCooldown--;
        } else {
            // Find the nearest enemy
            Enemy nearestEnemy = findNearestEnemy();
            if (nearestEnemy != null) {
                // Create the bullet and add it to the world first
                bullets bullet = new bullets(5, 1, attack);
                getWorld().addObject(bullet, getX(), getY());

                // Set the target after the bullet is in the world
                bullet.setTarget(nearestEnemy);

                shootCooldown = 20; // Reset cooldown
            }
        }
    }

    private Enemy findNearestEnemy() {
        Enemy nearestEnemy = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Object obj : getWorld().getObjects(Enemy.class)) {
            Enemy enemy = (Enemy) obj;
            double distance = Math.hypot(enemy.getX() - getX(), enemy.getY() - getY());
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestEnemy = enemy;
            }
        }

        return nearestEnemy;
    }

    public void act() {
        control();
        if (!(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || 
              Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")||
              Greenfoot.isKeyDown("a")||Greenfoot.isKeyDown("d")||Greenfoot.isKeyDown("w")||Greenfoot.isKeyDown("s"))) {
            autoShoot();
        }
    }
}
