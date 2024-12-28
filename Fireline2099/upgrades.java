import greenfoot.*;
public class upgrades extends Actor {
    private int upgradeType;
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
                switch (upgradeType) {
                    case 0: player.increaseAttack(20); break;
                    case 1: player.decreaseShootCooldown(1); break;
                    case 2: player.heal(100); break;
                }
                MyWorld world = (MyWorld) getWorld();
                world.setUpgradesSelected();
                getWorld().removeObjects(getWorld().getObjects(upgrades.class));
            }
        }
    }
}