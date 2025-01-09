import greenfoot.*;

public class MyWorld extends World {
    public static int score; // Integer for player's score
    public static int numberofEnemies; // Integer for number of enemies in a stage
    private int stage; // Integer for stage number
    private String[] special = {"fire.png", "ice.png", "earth.png", "water.png", "wind.png"}; // Boss special attack image files
    private boolean upgradesSelected = false; // Boolean to know if an upgrade was selected
    private boolean devMode = true; // Mode to see player stats for testing
    Player player = new Player(5, 100, 50); // Create a player with 10 speed, 100 health, and 50 attack
    
    public MyWorld() {    
        super(400, 600, 1); // Instnatiate a 400x600 world
        populateWorld(); // Populates the world with enemies and the player
    }
    public void populateWorld() { // Starts the world for the first time
        addObject(player, getWidth() / 2, getHeight() - 50); // Adds the player near the bottom of the world
        score = 0; // Sets score to 0
        numberofEnemies = 0; // Sets number of enemies to 0
        stage = 0; // Sets stage to first stage (0th stage)
        placeRandomCovers(); // Adds cover around the world
        for (int i = 0; i < 5; i++) { // Create 5 enemies
            Enemy enemy = new Enemy(2, 100, 1); // Creates enemy with 2 speed, 100 health, and 1 attack
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber((getHeight() / 2) - 1)); // Adds enemies randomly near the top half of the world
            numberofEnemies++; // Increases number of enmies per iteration
        }
    }
    public void nextStage() { // Prepares the world for the next stage
        stage++; // Increases stage number
        upgradesSelected = false; // Resets upgradesSelected to allow for next upgrade selection
        player.setLocation(getWidth() / 2, getHeight() - 50); // Moves player to starting location
        placeRandomCovers(); // Adds cover around the world
        if (stage % 5 == 0) { // Adds boss enemy every 5 stages
            bossEnemy boss = new bossEnemy(5, 150, 15, special[Greenfoot.getRandomNumber(special.length)]); // Creates boss enemy with 5 speed, 150 health, and 15 attack, also with random special skill
            addObject(boss, getWidth() / 2, getHeight() - getHeight()); // Places boss enemy at the top of the screen
            numberofEnemies++; // Increases number of enemies
        }
        for (int i = 0; i < 5 + stage; i++) { // Creates 5 enemies, plus the stage number (i.e, 6 in stage 1)
            Enemy enemy = new Enemy(2, 100 + stage * 5, 1 + stage * 2); // Creates enemy with 2 speed, 100 (plus 5 times the stage number) health, and 1 (plus 2 times the stage number) attack
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 2)); // Adds enemies randomly near the top half of the world
            numberofEnemies++; // Increases number of enmies per iteration
        }
    }
    public void placeRandomCovers() { // Adds cover around the world
        removeObjects(getObjects(cover.class)); // First removes previous cover
        for (int i = 0; i < 5; i++) { // Adds 5 cover objects
            cover newCover = new cover();
            addObject(newCover, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight() / 4) + getHeight() / 2); // Adds cover randomly around the bottom of the world, but not close to where the player spawns
        }
    }
    public void gameOver() { // Game over screen
        showText("GAME OVER", getWidth() / 2, getHeight() / 2); // Shows game over in the middle of the screen
        Greenfoot.stop(); // Stops game
    }
    public void checkEnemies() { // Checks to see if all enemies are dead to procceed
        if (numberofEnemies == 0 && !upgradesSelected && getObjects(upgrades.class).isEmpty()) { // Checks to see if enemies are gone, no upgrades were selected, and if there are no objects from the upgrades class
            for (int i = 0; i < 3; i++) { // Creates 3 upgrade choices
                upgrades upgrade = new upgrades(i); // Creates an ith upgrade choice
                addObject(upgrade, 100 + i * 100, getHeight() / 2); // Spaces out the upgrade choices by 100 pixels near the middle of the world
            }
        } else if (numberofEnemies == 0 && upgradesSelected && getObjects(portal.class).isEmpty()) { // Checks to see if enemies are gone, an upgrade was selected, and if there isn't a portal object
            portal portal = new portal(); 
            addObject(portal, getWidth() / 2, getHeight() - getHeight() + 15); // Creates a portal near the top of the screen
        }
    }
    public void setUpgradesSelected() { // Sets upgradesSelected as true when user selects an upgrade
        upgradesSelected = true;
    }
    public void removePortal(portal portal) { // Removes portal after user uses it
        removeObject(portal); 
        nextStage(); // Proceeds to next stage after portal is used
    }
    public void displayStats() { // Displays user stats near the top of the screen
        showText("Health: " + player.health, (getWidth() / 2 - 100), 580);
        showText("Score: " + score, (getWidth() / 2), 580);
        showText("Stage: " + stage , (getWidth() / 2 + 100), 580);
        if (devMode) { // If dev mode is selected, show more detailed stats
            showText("Speed: " + player.speed, (getWidth() / 2 - 100), 40);
            showText("Attack: " + player.attack, (getWidth() / 2 - 100), 60);
            showText("Shoot Cooldown: " + player.shootCooldown, (getWidth() / 2 - 100), 80);
        }
    }
    public void act() {
        displayStats(); // Displays user stats
        checkEnemies(); // Checks to see if enemies are defeated
        if (getObjects(Player.class).isEmpty()) { // Game is over when player is empty/deleted
            gameOver();
        }
    }
}