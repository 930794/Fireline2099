import greenfoot.*;
public class bullets extends Entity {
    private double dx;
    private double dy;
    public bullets(int speed, int health, int attack) {
        this.speed = speed;
        this.attack = attack;
        this.dx = 0;
        this.dy = -speed;
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
        if (isTouching(cover.class)) {
            getWorld().removeObject(this);
            return;
        }
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            ((Entity) enemy).takeDamage(attack);
            getWorld().removeObject(this);
        } 
        else if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}