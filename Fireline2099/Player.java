import greenfoot.*; 

public class Player extends Entity {
    public double shootCooldown = 20.0; // Sets cooldown to shoot to 20
    private double maxShootCooldown = 20.0; // Sets max cooldown to shoot to 20 as well
    private GreenfootImage idle = new GreenfootImage("idle.png"); // Create object with image file for the idle state
    private GreenfootImage[] walkFrames = {new GreenfootImage("walk1.png"),
                                           new GreenfootImage("walk2.png"),
                                           new GreenfootImage("walk3.png"),
                                           new GreenfootImage("walk4.png"),
                                           new GreenfootImage("walk5.png"),
                                           new GreenfootImage("walk6.png")}; // Array with walking animation frames
    private int animationFrame = 0; // Starts animationFrame at 0 (first frame in walkFrames array)
    private int animationCounter = 0; // Starts animationCounter at 0
    private boolean facingRight = true; // Player starts facing right (or east)
    boolean isMoving = false; // Player starts not moving
    
    public Player(int speed, int health, int attack) { // Constructs player
        this.speed = speed;
        this.health = health;
        this.attack = attack;
        setImage(idle); // Starts player in idle state
    }
    public void control() { // Player control method
        isMoving = false; // Sets isMoving to false when player is not moving
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) { // Checks if user is pressing "a" or left key (2 movement options)
            move(-speed, 0); // Moves left (negative speed x value)
            isMoving = true; // Player is moving
            faceDirection(false); // Player is not currently facing right
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) { // Checks if user is pressing "d" or right key (2 movement options)
            move(speed, 0); // Moves right (positive speed x value)
            isMoving = true; // Player is moving
            faceDirection(true); // Player is currently facing right
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) { // Checks if user is pressing "w" or up key (2 movement options)
            move(0, -speed); // Moves up (negative speed y value)
            isMoving = true; // Player is moving
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) { // Checks if user is pressing "s" or down key (2 movement options)
            move(0, speed); // Moves down (positive speed y value)
            isMoving = true; // Player is moving
        }
        if (isMoving) { // Checks if player is moving
            animateWalking(); // Plays walking animation
        } else { // If player is not moving
            setImage(idle); // Player idle state
        }
    }
    private void faceDirection(boolean right) { // Changes player facing direction
        if (facingRight != right) { // Checks if facingRight and right are not equal, which showcases a change in direction
            facingRight = right; // Switches facingRight state
            for (int i = 0; i < walkFrames.length; i++) { // Loops through all walking frames
                walkFrames[i].mirrorHorizontally(); // Flips each frame horizontally
            }
            idle.mirrorHorizontally(); // Flips idle state horizontally
        }
    }
    private void animateWalking() { // Animated walking method
        animationCounter++; // Increments animation counter
        if (animationCounter % 5 == 0) { // Commences every 5 counts of animationCounter
            setImage(walkFrames[animationFrame]); // Sets image to current animationFrame value
            animationFrame = (animationFrame++) % walkFrames.length; // Goes to next animation frame, and performes modulo division to just loop through the 6 frames
        }
    }
    private void autoShoot() { // Automatically shoot when standing still
        if (shootCooldown > 0) { // Checks to see if cooldown is greater than zero
            shootCooldown--; // Reduces cooldown by 1
        } else { // Shoots when cooldown is less than or equal to zero
            Enemy nearestEnemy = findNearestEnemy(); // Finds nearest enemy object
            if (nearestEnemy != null) { // Checks if the nearest enemy is not void
                bullets bullet = new bullets(5, attack); // Create a bullet object with 5 speed and attack of current attack value 
                getWorld().addObject(bullet, getX(), getY()); // Adds bullet to where the player is
                bullet.setTarget(nearestEnemy); // Set the bullet trajectory to closest enemy
                shootCooldown = maxShootCooldown; // Resets shootCooldown
            }
        }
    }
    private Enemy findNearestEnemy() { // Find the nearest enemy object
        Enemy nearestEnemy = null; // Assigns nearest enemy as null (has not been found yet)
        double nearestDistance = Double.MAX_VALUE; // Start nearest distance as max double value (farthest distance)
        for (Enemy enemy : getWorld().getObjects(Enemy.class)) { // Loops through all enemy objects in the world
            double distance = Math.hypot(enemy.getX() - getX(), enemy.getY() - getY()); // Finds distance between enemy object and player
            if (distance < nearestDistance) { // Sees if calculated distance is the smaller distance
                nearestDistance = distance; // This distance value becomes the nearest distance
                nearestEnemy = enemy; // Nearest enemy becomes one with the nearest distance
            }
        }
        return nearestEnemy; // Returns the nearest enemy object
    }
    public void increaseAttack(double amount) { // Increase player attack by specified amount
        attack *= amount; // Add amount to atttack
    }
    public void decreaseShootCooldown(double amount) { // Decrease player cooldown by specified amount
        if (maxShootCooldown > 1) { // Checks if cooldown is greater than 1
            maxShootCooldown *= amount; // Reduces max cooldown by amount
        }
    }
    public void heal(double amount) { // Increase player health by specified amount
        health *= amount; // Adds amount to health
    }
    public void act() {
        control(); // Adds player control
        if (!isMoving) { // Checks to see if player is not moving
            autoShoot(); // Shoots when not moving
        }
    }
}