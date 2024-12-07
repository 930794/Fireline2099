import greenfoot.*;

public abstract class Entity extends Actor {
    protected int health;
    protected int speed;
    protected int attack;

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            getWorld().removeObject(this);
            MyWorld.numberofEnemies--;
            MyWorld.score++;
        }
    }

    public void move(int dx, int dy) {
        int oldX = getX();
        int oldY = getY();

        setLocation(oldX + dx, oldY + dy);

        // Check for collision with cover
        if (isTouching(cover.class)) {
            setLocation(oldX, oldY); // Revert to old position if colliding with cover
        }
    }
}
