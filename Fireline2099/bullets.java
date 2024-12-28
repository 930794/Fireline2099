import greenfoot.*;

public class bullets extends Entity {
    private double dx;
    private double dy;

    public bullets(int speed, int health, int attack) {
        this.speed = speed;
        this.attack = attack;
        this.dx = 0;
        this.dy = -speed; // Default straight up if no target
    }

    public void setTarget(Actor target) {
        if (target != null) {
            double distance = Math.hypot(target.getX() - getX(), target.getY() - getY());
            dx = (target.getX() - getX()) / distance * speed;
            dy = (target.getY() - getY()) / distance * speed;
        }
    }

    public void act() {
        setLocation((int) (getX() + dx), (int) (getY() + dy));

        // Check for collision with cover
        if (isTouching(cover.class)) {
            getWorld().removeObject(this);
            return;
        }

        // Check for collision with an enemy
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            ((Entity) enemy).takeDamage(attack);
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
