import greenfoot.*;

public class bullets extends Entity {
    private double dx;
    private double dy;

    public bullets(int speed, int health, int attack, Actor target) {
    this.speed = speed;
    this.attack = attack;

    if (target != null) {
        double distance = Math.hypot(target.getX() - getX(), target.getY() - getY());
        dx = (target.getX() - getX()) / distance * speed;
        dy = (target.getY() - getY()) / distance * speed;
    } else {
        dx = 0;
        dy = -speed; // Default straight up if no target
    }
}


    public void act() {
        setLocation((int) (getX() + dx), (int) (getY() + dy));

        // Check for collision with an enemy
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            ((Entity) enemy).takeDamage(attack);
            getWorld().removeObject(this);
        } else if (isAtEdge()) {
            getWorld().removeObject(this); // Remove bullet if it goes off-screen
        }
    }
}
