import greenfoot.*;  

public abstract class Entity extends Actor {
    protected int health;
    protected int speed;
    protected int attack;

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            getWorld().removeObject(this);
        }
    }
}
