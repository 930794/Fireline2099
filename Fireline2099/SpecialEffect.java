import greenfoot.*;

public class SpecialEffect extends Actor {
    private int duration = 5; // Sets duration of attack to 5 times
    private int damage; // Special damage instance variable
    
    public SpecialEffect(String imagePath, int damage) { // Constructs special effect object
        setImage(new GreenfootImage(imagePath)); // Sets image to selected special image
        this.damage = damage; // Sets damage to SpecialEffect damage
    }
    public void act() {
        Player player = (Player) getOneIntersectingObject(Player.class); // Gets player this is currently touching
        if (player != null) { // Checks if player is not null
            player.takeDamage(damage); // Player takes damage according to current damage
        }
        duration--; // Reduces duration
        if (duration <= 0) { // Checks if duration is less than or equal to zero
            getWorld().removeObject(this); // Removes SpecialEffect once duration is used up
        }
    }
}