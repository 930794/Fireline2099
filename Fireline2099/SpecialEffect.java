import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SpecialEffect extends Actor {
    private int duration = 50; // Effect lasts for 50 frames
    private int damage; // Damage dealt by the effect

    public SpecialEffect(String imagePath, int damage) {
        setImage(new GreenfootImage(imagePath)); // Set the special attack image
        this.damage = damage;
    }

    public void act() {
        // Damage the player if they're within range
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            player.takeDamage(damage);
        }

        // Reduce the duration and remove the effect once it ends
        duration--;
        if (duration <= 0) {
            getWorld().removeObject(this);
        }
    }
}
