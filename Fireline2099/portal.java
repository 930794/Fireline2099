import greenfoot.*;

public class portal extends Actor {
    private boolean used = false; // Portal starts unused
    
    public void act() {
        if (!used) { // Checks if portal is unused
            Player player = (Player) getOneIntersectingObject(Player.class); // Gets player object touching portal
            if (player != null) { // Checks if player is not null
                used = true; // Portal becomes used
                MyWorld world = (MyWorld) getWorld(); // Creates a MyWorld cast object
                world.removePortal(this); // Calls the removePortal method in MyWorld, removing this portal
            }
        }
    }
}