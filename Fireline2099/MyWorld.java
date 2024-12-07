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
        super(500, 800, 1);  // Create a world with 600x800 cells, each 1x1 pixels
        populateWorld();
    }
    public void populateWorld() {
        Player player = new Player(10, 100, 20); // Initializes a player with 10 speed, 100 health, and 20 attack
        addObject(player, getWidth() / 2, getHeight() - 50);

        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(10, 100, 20);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies+=i;
        }
    }
    public void nextLevel(){
        if(stage%5 == 0){
            bossEnemy boss = new bossEnemy(10, 100, 20, special[0]);
            addObject(boss, getWidth() / 2, 100);
        }
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(10, 100, 20);
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2));
            numberofEnemies+=i;
        }
    }
    public void checkEnemies(){
        if(numberofEnemies == 0){
            
        }
    }
    public void gameOver(){
        
    }
    public void act() {
        // World-specific actions can go here
    }
}
