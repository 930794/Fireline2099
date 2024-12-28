import greenfoot.*;  
public class cover extends Actor {
    public boolean isOverlappingEnemy() {
        return !getIntersectingObjects(Enemy.class).isEmpty();
    }
    public void act() {
        
    }
}