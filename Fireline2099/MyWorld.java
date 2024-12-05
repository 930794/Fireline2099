import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A vertical world for the game.
 */
public class myWorld extends World {
    public myWorld() {    
        super(600, 800, 1);  // Create a world with 600x800 cells, each 1x1 pixels
        populateWorld();
    }

    public void populateWorld() {
        Player player = new Player();
        addObject(player, getWidth() / 2, getHeight() - 50);

        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
        }

        bossEnemy boss = new bossEnemy();
        addObject(boss, getWidth() / 2, 100);
    }

    public void act() {
        // World-specific actions can go here
    }
}
