import greenfoot.*;
public class portal extends Actor {
    private boolean used = false;
    public void act() {
        if (!used) {
            Player player = (Player) getOneIntersectingObject(Player.class);
            if (player != null) {
                used = true;
                MyWorld world = (MyWorld) getWorld();
                world.removePortal(this);
            }
        }
    }
}