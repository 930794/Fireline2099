import greenfoot.*; 
public class Player extends Entity {
    public int shootCooldown = 20;
    private GreenfootImage[] walkFrames = new GreenfootImage[6];
    private GreenfootImage idle = new GreenfootImage("idle.png");
    private int animationFrame = 0;
    private int animationCounter = 0;
    private boolean facingRight = true;
    public Player(int speed, int health, int attack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;
        for (int i = 0; i < walkFrames.length; i++) {
            walkFrames[i] = new GreenfootImage("walk" + (i + 1) + ".png");
        }
        setImage(idle);
    }
    public void control() {
        boolean isMoving = false;
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            move(-speed, 0);
            isMoving = true;
            faceDirection(false);
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            move(speed, 0);
            isMoving = true;
            faceDirection(true);
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            move(0, -speed);
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            move(0, speed);
            isMoving = true;
        }
        if (isMoving) {
            animateWalking();
        } else {
            setImage(idle);
        }
    }
    private void faceDirection(boolean right) {
        if (facingRight != right) {
            facingRight = right;
            for (int i = 0; i < walkFrames.length; i++) {
                walkFrames[i].mirrorHorizontally();
            }
            idle.mirrorHorizontally();
        }
    }
    private void animateWalking() {
        animationCounter++;
        if (animationCounter % 5 == 0) {
            setImage(walkFrames[animationFrame]); 
            animationFrame = (animationFrame + 1) % walkFrames.length;
        }
    }
    private void autoShoot() {
        if (shootCooldown > 0) {
            shootCooldown--;
        } else {
            Enemy nearestEnemy = findNearestEnemy();
            if (nearestEnemy != null) {
                bullets bullet = new bullets(5, 1, attack);
                getWorld().addObject(bullet, getX(), getY());
                bullet.setTarget(nearestEnemy);
                shootCooldown = 20;
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
    public void increaseAttack(int amount) {
        attack += amount;
    }
    public void decreaseShootCooldown(int amount) {
        if (shootCooldown > 1) {
            shootCooldown -= amount;
        }
    }
    public void heal(int amount) {
        health += amount;
    }
    public void act() {
        control();
        if (!(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") ||
              Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("s"))) {
            autoShoot();
        }
    }
}