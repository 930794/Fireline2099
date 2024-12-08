import greenfoot.*;  

<<<<<<< Updated upstream
public class myWorld extends World {
<<<<<<< Updated upstream
    public myWorld() {    
        super(600, 800, 1);  // Create a world with 600x800 cells, each 1x1 pixels
=======
public class MyWorld extends World {
    public static int score;
    public static int numberofEnemies;
    private int stage = 1;
    private boolean upgradesActive = false; // To track if upgrades are being shown
    Player player = new Player(10, 100, 50); // Initializes a player with 10 speed, 100 health, and 50 attack

    public MyWorld() {    
        super(500, 800, 1);
>>>>>>> Stashed changes
        populateWorld();
    }

    public void populateWorld() {
        Player player = new Player();
        addObject(player, getWidth() / 2, getHeight() - 50);
=======
    public static int score;
    private int level = 1;
    private Player player;
    private boolean gameOverFlag = false;
>>>>>>> Stashed changes

    public myWorld() {    
        super(600, 800, 1);
        score = 0;
<<<<<<< Updated upstream
        setupLevel();
    }

    private void setupLevel() {
        removeObjects(getObjects(null));
        addScoreDisplay();
        player = new Player(3, 100, 10);
        addObject(player, getWidth() / 2, getHeight() - 50);
        spawnEnemies();
    }

    private void spawnEnemies() {
        int enemyCount = 5 + level * 2;
        for (int i = 0; i < enemyCount; i++) {
            addObject(new Enemy(2 + level, 20 + level * 5, 5), 
                Greenfoot.getRandomNumber(getWidth()), 
                Greenfoot.getRandomNumber(getHeight() / 2));
        }

        if (level % 5 == 0) {
            addObject(new bossEnemy(1 + level, 200, 20, "fireball"),
                getWidth() / 2, 100);
        }
    }

<<<<<<< Updated upstream
=======
    private void addScoreDisplay() {
        showText("Score: " + score, getWidth() / 2, 20);
        showText("Level: " + level, 70, 20);
    }

    private void displayUpgrades() {
        String[] upgrades = {"Attack Up", "Fast Attack", "Heal", "Add Projectile", "Pierce"};
        for (int i = 0; i < 3; i++) {
            String randomUpgrade = upgrades[Greenfoot.getRandomNumber(upgrades.length)];
            addObject(new upgradeVisuals(randomUpgrade), 
                getWidth() / 4 + i * getWidth() / 4, getHeight() / 2);
        }
    }

    public void checkEnemies() {
        if (getObjects(Enemy.class).isEmpty() && getObjects(bossEnemy.class).isEmpty() && !gameOverFlag) {
            addObject(new portal(), getWidth() / 2, 100);
            displayUpgrades();
        }
    }

    public void nextLevel() {
        removeObjects(getObjects(upgradeVisuals.class)); // Clear upgrades
        level++;
        setupLevel();
=======
        numberofEnemies = 0;
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(2, 100, 1);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }

    public void nextLevel() {
        stage++;
        upgradesActive = true; // Flag that upgrade selection is active
        showUpgradeOptions();

        player.setLocation(getWidth() / 2, getHeight() - 50);
        if (stage % 5 == 0) {
            bossEnemy boss = new bossEnemy(10, 150, 30, "Fire");
            addObject(boss, getWidth() / 2, 100);
            numberofEnemies++;
        }
        for (int i = 0; i < 5 + stage; i++) {
            Enemy enemy = new Enemy(2, 100 + stage * 10, 1 + stage * 2);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }

    private void showUpgradeOptions() {
        upgrades upgradeMenu = new upgrades(this); // Pass reference to the world
        addObject(upgradeMenu, getWidth() / 2, getHeight() / 2);
    }

    public void applyUpgrade() {
        // Called after an upgrade is applied to the player
        upgradesActive = false; // Resume game
    }

    public void removePortal(portal portal) {
        removeObject(portal);
        nextLevel();
    }

    public void checkEnemies() {
        if (numberofEnemies == 0 && getObjects(portal.class).isEmpty() && !upgradesActive) {
            portal portal = new portal();
            addObject(portal, getWidth() / 2, getHeight() / 2);
        }
    }

    public void gameOver() {
        showText("GAME OVER", getWidth() / 2, getHeight() / 2);
        Greenfoot.stop();
>>>>>>> Stashed changes
    }

    public void gameOver() {
        showText("Game Over", getWidth() / 2, getHeight() / 2);
        gameOverFlag = true;
        Greenfoot.stop();
    }

>>>>>>> Stashed changes
    public void act() {
<<<<<<< Updated upstream
        checkEnemies();
=======
        // Freeze game logic while upgrades are active
        if (upgradesActive) return;

        checkEnemies();
        if (getObjects(Player.class).isEmpty()) {
            gameOver();
        }
>>>>>>> Stashed changes
    }
}
