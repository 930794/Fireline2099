import greenfoot.*;
public class MyWorld extends World {
    public static int score;
    public static int numberofEnemies;
    private int stage;
    private String[] special = {"fire.png", "ice.png", "earth.png", "water.png", "wind.png"};
    private boolean upgradesSelected = false;
    private boolean devMode = false; 
    Player player = new Player(10, 100, 50);
    public MyWorld() {    
        super(400, 600, 1);
        populateWorld();
    }
    public void populateWorld() {
        addObject(player, getWidth() / 2, getHeight() - 50);
        score = 0;
        numberofEnemies = 0;
        stage = 1;
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(2, 100, 1);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber((getHeight() / 2) - 1));
            numberofEnemies++;
        }
        placeRandomCovers(); 
    }
    public void placeRandomCovers() {
        removeObjects(getObjects(cover.class));
        for (int i = 0; i < 5; i++) {
            cover newCover = new cover();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight() / 4) + getHeight() / 2;
            addObject(newCover, x, y);
        }
    }
    public void nextLevel() {
        stage++;
        upgradesSelected = false; 
        player.setLocation(getWidth() / 2, getHeight() - 50);
        placeRandomCovers(); 
        if (stage % 5 == 0) {
            bossEnemy boss = new bossEnemy(5, 150, 30, special[Greenfoot.getRandomNumber(special.length)]);
            addObject(boss, getWidth() / 2, getHeight() - getHeight());
            numberofEnemies++;
        }
        for (int i = 0; i < 5 + stage; i++) { 
            Enemy enemy = new Enemy(2, 100 + stage * 10, 1 + stage * 2);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies++;
        }
    }
    public void gameOver() {
        showText("GAME OVER", getWidth() / 2, getHeight() / 2);
        Greenfoot.stop();
    }
    public static void resetGame() { 
        Greenfoot.setWorld(new MyWorld());
    }
    public void checkEnemies() {
        if (numberofEnemies == 0 && !upgradesSelected && getObjects(upgrades.class).isEmpty()) {
            for (int i = 0; i < 3; i++) { 
                upgrades upgrade = new upgrades(i);
                addObject(upgrade, 100 + i * 100, getHeight() / 2);
            }
        } else if (upgradesSelected && getObjects(portal.class).isEmpty()) {
            portal portal = new portal();
            addObject(portal, getWidth() / 2, getHeight() - getHeight() + 15);
        }
    }
    public void setUpgradesSelected() {
        upgradesSelected = true;
    }
    public void removePortal(portal portal) {
        removeObject(portal); 
        nextLevel(); 
    }
    public void displayStats() {
        showText("Health: " + player.health, 100, 20);
        showText("Score: " + score, getWidth() / 2, 20);
        showText("Stage: " + stage, getWidth() - 100, 20);
        if (devMode) {
            showText("Speed: " + player.speed, 100, 40);
            showText("Attack: " + player.attack, 100, 60);
            showText("Shoot Cooldown: " + player.shootCooldown, 100, 80);
        }
    }
    public void act() {
        displayStats();
        checkEnemies();
        if (getObjects(Player.class).isEmpty()) {
            gameOver();
        }
    }
}