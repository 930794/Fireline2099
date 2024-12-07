import greenfoot.*;

public class portal extends Actor {
    public void act() {
        Actor player = getOneIntersectingObject(Player.class);
        if (player != null) {
            ((myWorld) getWorld()).nextLevel();
            getWorld().removeObject(this);
        }
    }
}
