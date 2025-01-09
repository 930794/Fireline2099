import greenfoot.*;

public class upgrades extends Actor {
    private int upgradeType; // Instance variable for upgrade type
    
    public upgrades(int type) {
        this.upgradeType = type; // Get upgrade type
        setImage(getImageForUpgradeType(type)); // Set image to corresponding upgrade type
    }
    private String getImageForUpgradeType(int type) { // Get image file for corresponding upgrade type
        switch (type) { // Switch statement with 3 cases
            case 0: return "attackUp.png"; // Return image for increasing attack damage
            case 1: return "fastAttack.png"; // Return image for reducing attack cooldown
            case 2: return "heal.png"; // Return image for healing player
            default: throw new IllegalArgumentException("Invalid upgrade type: " + type); // Error message if a value less than 0 or greater than 2 is passed, to return something
        }
    }
    private void applyUpgrade(Player player) { // Applies upgrade depending on upgradeType
        switch (upgradeType) { // Switch statement with 3 cases
            case 0: player.increaseAttack(1.25); break; // Increases player attack by 25 percent
            case 1: player.decreaseShootCooldown(0.90); break; // Decreases shoot cooldown by 10 percent
            case 2: player.heal(1.25); break; // Increases player health point by 25 percent
        }
    }
    public void act() {
        if (Greenfoot.mouseClicked(this)) { // Checks if user mouse is clicking upgrade choice
            Player player = (Player) getWorld().getObjects(Player.class).get(0); // Gets first player object
            if (player != null) { // Checks if player object is not null
                applyUpgrade(player); // Applies selected upgrade type to player
                MyWorld world = (MyWorld) getWorld(); // Creates a MyWorld cast object
                world.setUpgradesSelected(); // Calls method in MyWorld to set upgradesSelected as true
                world.removeObjects(world.getObjects(upgrades.class)); // Removes all upgrade objects
            }
        }
    }
}