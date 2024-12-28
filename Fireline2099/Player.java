import greenfoot.*; 
public class Player extends Entity {
    public int shootCooldown = 20;

    private GreenfootImage[] walkFrames = new GreenfootImage[6]; // Array for 6 walking frames
    private GreenfootImage idle = new GreenfootImage("idle.png"); // Idle frame
    private int animationFrame = 0; // Tracks the current animation frame
    private int animationCounter = 0; // Controls animation speed

    public Player(int speed, int health, int attack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;

        // Load walking animation frames
        for (int i = 0; i < 6; i++) {
            walkFrames[i] = new GreenfootImage("walk" + (i + 1) + ".png");
        }

        setImage(idle); // Set initial image to idle
    }

    public void control() {
        boolean isMoving = false;

        // Movement logic
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            move(-speed, 0);
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            move(speed, 0);
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            move(0, -speed);
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            move(0, speed);
            isMoving = true;
        }

        // Animation logic
        if (isMoving) {
            animateWalking();
        } else {
            setImage(idle); // Reset to idle when not moving
        }
    }

    private void animateWalking() {
        animationCounter++;

        // Adjust animation speed by changing the modulus value
        if (animationCounter % 5 == 0) {
            setImage(walkFrames[animationFrame]); // Set current frame
            animationFrame = (animationFrame + 1) % walkFrames.length; // Cycle through frames
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