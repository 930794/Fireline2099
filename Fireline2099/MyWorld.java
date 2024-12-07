import greenfoot.*;  

public class myWorld extends World {
<<<<<<< Updated upstream
    public myWorld() {    
        super(600, 800, 1);  // Create a world with 600x800 cells, each 1x1 pixels
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
    }

    public void gameOver() {
        showText("Game Over", getWidth() / 2, getHeight() / 2);
        gameOverFlag = true;
        Greenfoot.stop();
    }

>>>>>>> Stashed changes
    public void act() {
        checkEnemies();
    }
}
