import greenfoot.*;

public class upgrades extends Actor {
    private int upgradeType; // 0: attackUp, 1: fastAttack, 2: heal, 3: pierce

    public void setUpgradeType(int type) {
        this.upgradeType = type;
        switch (type) {
            case 0: setImage("attackUp.png"); break;
            case 1: setImage("fastAttack.png"); break;
            case 2: setImage("heal.png"); break;
         }
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            if (player != null) {
                // Apply the selected upgrade to the player
                switch (upgradeType) {
                    case 0: player.increaseAttack(20); break;
                    case 1: player.decreaseShootCooldown(1); break;
                    case 2: player.heal(100); break;
                }

                // Notify the world that upgrades have been selected
                MyWorld world = (MyWorld) getWorld();
                world.setUpgradesSelected();

                // Remove all upgrade options after one is selected
                getWorld().removeObjects(getWorld().getObjects(upgrades.class));
            }
        }
    }
}