import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player class representing the main character.
 */
public class Player extends Entity {
    public Player(int speed, int health, int attack){
        this.speed = speed;
        this.health = health;
        this.attack = attack;
    }
    public void control() {
        if (Greenfoot.isKeyDown("left")) move(-speed, 0);
        if (Greenfoot.isKeyDown("right")) move(speed, 0);
        if (Greenfoot.isKeyDown("up")) move(0, -speed);
        if (Greenfoot.isKeyDown("down")) move(0, speed);
    }
    public void useSkill(){
        
    }
    public void act() {
        control();
    }
}
