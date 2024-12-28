import greenfoot.*;
public class SpecialEffect extends Actor {
    private int duration = 50;
    private int damage; 
    public SpecialEffect(String imagePath, int damage) {
        setImage(new GreenfootImage(imagePath));
        this.damage = damage;
    }
    public void act() {
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            player.takeDamage(damage);
        }
        duration--;
        if (duration <= 0) {
            getWorld().removeObject(this);
        }
    }
}