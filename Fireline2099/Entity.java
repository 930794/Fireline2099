import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract Entity class for all game objects.
 */
public abstract class Entity extends Actor {
    protected int x, y;
    protected GreenfootImage image;
    public int speed;
    public int health;
    public int attack;

    public void move(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
    }
    public void shoot(){
        
    }
}
