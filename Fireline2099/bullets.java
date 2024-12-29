import greenfoot.*;

public class bullets extends Entity {
    private double dx; // Horizontal movement variable
    private double dy; // Vertical movement variable
    
    public bullets(int speed, int attack) { // Creates bullet object with specified speed and attack values
        this.speed = speed;
        this.attack = attack;
    }
    public void setTarget(Actor target) { // Sets target as nearest enemy found
        if (target != null) { // Checks if target is not null
            double distance = Math.hypot(target.getX() - getX(), target.getY() - getY()); // Gets Euclidean distance of target
            dx = (target.getX() - getX()) / distance * speed; // Gets the horizontal movement vector, multiplied by speed value
            dy = (target.getY() - getY()) / distance * speed; // Gets the vertical movement vector, multiplied by speed value
        }
    }
    public void act() {
        setLocation((int) (getX() + dx), (int) (getY() + dy)); // Moves bullet to where the enemy is
        if (isTouching(cover.class)) { // Checks if bullet is touching cover
            getWorld().removeObject(this); // Removes bullet
            return; // Returns to code execution
        }
        Actor enemy = getOneIntersectingObject(Enemy.class); // Gets enemy which bullet is touching
        if (enemy != null) { // Checks if enemy is not null
            ((Entity) enemy).takeDamage(attack); // Enemy entity takes damage for bullet attack value
            getWorld().removeObject(this); // Removes bullet
        } 
        else if (isAtEdge()) { // Checks if bullet is touching edge of screen
            getWorld().removeObject(this); // Removes bullet
        }
    }
}