import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A vertical world for the game.
 */
public class MyWorld extends World {
    public static int score;
    public static int numberofEnemies;
    private int stage = 1;
    private String[] special = {"fire.png", "ice.png", "earth.png", "water.png", "wind.png"};
    private boolean upgradesSelected = false; // Track if an upgrade has been selected
    private boolean devMode = true; // Toggle developer mode here
    private static final int NUM_COVER = 5; // Number of cover objects per stage

    Player player = new Player(10, 100, 1000); // Initializes a player with 10 speed, 100 health, and 20 attack

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
        placeRandomCovers(); // Add initial covers
    }

    public void placeRandomCovers() {
        // Remove existing covers
        removeObjects(getObjects(cover.class));
        // Add random cover objects
        for (int i = 0; i < NUM_COVER; i++) {
            cover newCover = new cover();
            boolean validLocation = false;
            int attempts = 0;

            // Attempt to find a valid location
            while (!validLocation && attempts < 100) {
                int x = Greenfoot.getRandomNumber(getWidth());
                int y = Greenfoot.getRandomNumber(getHeight() / 4) + getHeight() / 4;
                addObject(newCover, x, y);
    
                // Check for intersection with Enemy
                if (!newCover.isOverlappingEnemy()) {
                    validLocation = true;
                } else {
                    removeObject(newCover); // Remove and retry
                }
    
                attempts++;
            }
        // If no valid location is found after attempts, stop trying
        if (!validLocation) {
            System.out.println("Failed to place cover " + i + " without intersection.");
        }
        }
    }
    public void nextLevel() {
        stage++; // Increment the stage number
        upgradesSelected = false; // Reset the upgrade selection flag
        player.setLocation(getWidth() / 2, getHeight() - 50);
        placeRandomCovers(); // Reposition cover objects

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

    public static void resetGame() { // Stops the program, then stops music and sets up a new world (not used yet)
        Greenfoot.setWorld(new MyWorld());
    }

    public void checkEnemies() {
        if (numberofEnemies == 0 && !upgradesSelected && getObjects(upgrades.class).isEmpty()) {
            // Add upgrade options
            for (int i = 0; i < 3; i++) { // Show 3 random upgrade options
                upgrades upgrade = new upgrades();
                upgrade.setUpgradeType(Greenfoot.getRandomNumber(3)); // Randomly assign an upgrade type (0-2)
                addObject(upgrade, 100 + i * 100, getHeight() / 2);
            }
        } else if (upgradesSelected && getObjects(portal.class).isEmpty()) {
            // Spawn portal only after upgrades are selected
            portal portal = new portal();
            addObject(portal, getWidth() / 2, getHeight() - getHeight());
        }
    }

    public void setUpgradesSelected() {
        upgradesSelected = true; // Called when an upgrade is selected
    }

    public void removePortal(portal portal) {
        removeObject(portal); // Remove the portal after interaction
        nextLevel(); // Move to the next level
    }

    public void displayStats() {
        // Display player's health, score, and stage level
        showText("Health: " + player.health, 100, 20);
        showText("Score: " + score, getWidth() / 2, 20);
        showText("Stage: " + stage, getWidth() - 100, 20);

        // Display detailed player stats if devMode is enabled
        if (devMode) {
            showText("Speed: " + player.speed, 100, 40);
            showText("Attack: " + player.attack, 100, 60);
            showText("Shoot Cooldown: " + player.shootCooldown, 100, 80);
        }
    }

    public void act() {
        // Display stats on the screen
        displayStats();
        checkEnemies(); // Check if it's time to spawn a portal

        // Check if the player exists (if not, trigger game over)
        if (getObjects(Player.class).isEmpty()) {
            gameOver();
        }
    }
}
