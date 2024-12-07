import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Portal class for level transitions.
 */
public class portal extends Actor {
    private boolean used = false; // To prevent multiple interactions in a single frame

    public void act() {
        // Check if the player touches the portal
        if (!used) { // Only allow interaction if not already used
            Player player = (Player) getOneIntersectingObject(Player.class);
            if (player != null) {
                used = true; // Mark as used to prevent further interactions
                MyWorld world = (MyWorld) getWorld();
                world.removePortal(this); // Remove the portal and transition to the next level
            }
        }
    }
}
