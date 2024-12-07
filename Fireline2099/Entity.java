import greenfoot.*;  

public abstract class Entity extends Actor {
    protected int health;
    protected int speed;
    protected int attack;
    protected int x, y;

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            getWorld().removeObject(this);
            MyWorld.numberofEnemies-=1;
            MyWorld.score+=1;
        }
    }
    public void move(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
    }
}