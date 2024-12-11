import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A vertical world for the game.
 */
public class MyWorld extends World {
    public static int score;
    public static int numberofEnemies;
    private int stage = 1;
    private String[] special = {"Fire","Ice","Earth","Water","Wind"};
    Player player = new Player(10, 100, 100); // Initializes a player with 10 speed, 100 health, and 20 attack
    public MyWorld() {    
        super(400, 600, 1);  // Create a world with 400x600 cells, each 1x1 pixels
        populateWorld();
    }
    public void populateWorld() {
        addObject(player, getWidth() / 2, getHeight() - 50);
        score = 0;
        numberofEnemies = 0;
        // Add initial enemies
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(2, 100, 1);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }
    public void nextLevel() {
        stage++; // Increment the stage number
        player.setLocation(getWidth() / 2, getHeight() - 50);
        // If the stage is a multiple of 5, add a boss
        if (stage % 5 == 0) {
            bossEnemy boss = new bossEnemy(10, 150, 30, special[Greenfoot.getRandomNumber(special.length)]); // Stronger boss stats
            addObject(boss, getWidth() / 2, 100);
            numberofEnemies++; // Count the boss as an enemy
        }

        // Add regular enemies for the new stage
        for (int i = 0; i < 5 + stage; i++) { // Increase enemies as the stage progresses
            Enemy enemy = new Enemy(2, 100 + stage * 10, 1 + stage * 2); // Enemies scale in difficulty
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }
    public void gameOver() {
        showText("GAME OVER", getWidth() / 2, getHeight() / 2);
        Greenfoot.stop(); // Stop the game
    }
    public static void resetGame(){ // Stops the program, then stops music and sets up a new world
        Greenfoot.stop();
        Greenfoot.setWorld(new MyWorld());
    }
    public void checkEnemies() {
        if (numberofEnemies == 0 && getObjects(portal.class).isEmpty()) { // Spawn portal only if none exists
            portal portal = new portal();
            addObject(portal, getWidth()/2, getHeight()-getHeight()); // Center the portal
        }
    }
    public void removePortal(portal portal) {
        removeObject(portal); // Remove the portal after interaction
        nextLevel(); // Move to the next level
    }
    public void act() {
        // Display stats on the screen
        checkEnemies(); // Check if it's time to spawn a portal

        // Check if the player exists (if not, trigger game over)
        if (getObjects(Player.class).isEmpty()) {
            gameOver();
        }
    }
}
