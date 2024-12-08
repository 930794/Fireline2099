import greenfoot.*;

public class upgrades extends Actor {
<<<<<<< Updated upstream
    public void attackUp(Player player) {
        player.attack += 5;
    }

    public void fastAttack(Player player) {
        player.speed += 2;
    }

    public void heal(Player player) {
        player.health += 20;
    }

    public void addProjectile(Player player) {
        // Logic to allow multiple projectiles at once
    }

    public void pierce(Player player) {
        // Logic for bullets to pierce through enemies
    }

    public void act() {
        // Upgrade logic can be added here if necessary
=======
    private String[] options = {"Attack Up", "Fast Attack", "Heal", "Add Projectile", "Pierce"};
    private boolean initialized = false;
    private MyWorld world; // Reference to the world
    private String selectedUpgrade = null; // Track the selected upgrade

    public upgrades(MyWorld world) {
        this.world = world; // Save reference to interact with the world
    }

    public void displayUpgradeOptions() {
        for (int i = 0; i < 3; i++) {
            String upgrade = options[Greenfoot.getRandomNumber(options.length)];
            upgradeVisuals visual = new upgradeVisuals(upgrade);
            getWorld().addObject(visual, getWorld().getWidth() / 2, 200 + i * 100);
        }
    }

    public void act() {
        if (!initialized) {
            displayUpgradeOptions(); // Display options after being added to the world
            initialized = true;
        }

        if (Greenfoot.mouseClicked(this)) {
            Actor clickedUpgrade = Greenfoot.getMouseInfo().getActor();
            if (clickedUpgrade instanceof upgradeVisuals) {
                selectedUpgrade = ((upgradeVisuals) clickedUpgrade).getUpgrade();
                applyUpgrade(selectedUpgrade);

                // Remove all upgrade visuals and this menu object
                getWorld().removeObjects(getWorld().getObjects(upgradeVisuals.class));
                getWorld().removeObject(this);
            }
        }
    }

    private void applyUpgrade(String upgrade) {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        switch (upgrade) {
            case "Attack Up":
                player.increaseAttack(5);
                break;
            case "Fast Attack":
                player.decreaseShootCooldown(5);
                break;
            case "Heal":
                player.heal(20);
                break;
            case "Add Projectile":
                player.addProjectile();
                break;
            case "Pierce":
                player.enablePiercing();
                break;
        }

        // Resume the game after applying the upgrade
        world.applyUpgrade();
>>>>>>> Stashed changes
    }
}
