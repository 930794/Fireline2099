import greenfoot.*;
public class upgrades extends Actor {
    private int upgradeType;
    public upgrades(int type) {
        this.upgradeType = type;
        setImage(getImageForUpgradeType(type));
    }
    private String getImageForUpgradeType(int type) {
        switch (type) {
            case 0: return "attackUp.png";
            case 1: return "fastAttack.png";
            case 2: return "heal.png";
            default: throw new IllegalArgumentException("Invalid upgrade type: " + type);
        }
    }
    private void applyUpgrade(Player player) {
        switch (upgradeType) {
            case 0: player.increaseAttack(20); break;
            case 1: player.decreaseShootCooldown(1); break;
            case 2: player.heal(100); break;
        }
    }
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            if (player != null) {
                applyUpgrade(player);
                MyWorld world = (MyWorld) getWorld();
                world.setUpgradesSelected();
                world.removeObjects(world.getObjects(upgrades.class));
            }
        }
    }
}