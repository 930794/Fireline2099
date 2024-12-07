import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A vertical world for the game.
 */
public class MyWorld extends World {
    public static int score;
    public static int numberofEnemies;
    private int stage = 1;
    private String[] special = {"Fire"};

    public MyWorld() {    
        super(500, 800, 1);  // Create a world with 500x800 cells, each 1x1 pixels
        populateWorld();
    }

    public void populateWorld() {
        Player player = new Player(10, 100, 20); // Initializes a player with 10 speed, 100 health, and 20 attack
        addObject(player, getWidth() / 2, getHeight() - 50);
        score = 0;
        numberofEnemies = 0;

        // Add initial enemies
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(2, 100, 20);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }

    public void nextLevel() {
        stage++; // Increment the stage number

        // If the stage is a multiple of 5, add a boss
        if (stage % 5 == 0) {
            bossEnemy boss = new bossEnemy(10, 150, 30, special[0]); // Stronger boss stats
            addObject(boss, getWidth() / 2, 100);
            numberofEnemies++; // Count the boss as an enemy
        }

        // Add regular enemies for the new stage
        for (int i = 0; i < 5 + stage; i++) { // Increase enemies as the stage progresses
            Enemy enemy = new Enemy(2, 100 + stage * 10, 20 + stage * 2); // Enemies scale in difficulty
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }

    public void checkEnemies() {
        if (numberofEnemies == 0 && getObjects(portal.class).isEmpty()) { // Spawn portal only if none exists
            portal portal = new portal();
            addObject(portal, getWidth() / 2, getHeight() / 2); // Center the portal
        }
    }

    public void removePortal(portal portal) {
        removeObject(portal); // Remove the portal after interaction
        nextLevel(); // Move to the next level
    }

    public void act() {
        // Display stats on the screen
        checkEnemies(); // Check if it's time to spawn a portal
    }
}
