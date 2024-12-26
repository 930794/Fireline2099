import greenfoot.*;
public class upgrades extends Actor {
    private int upgradeType; // 0: attackUp, 1: fastAttack, 2: heal, 3: addProjectile, 4: pierce

    public void setUpgradeType(int type) {
        this.upgradeType = type;
        switch (type) {
            case 0: setImage("attackUp.png"); break;
            case 1: setImage("fastAttack.png"); break;
            case 2: setImage("heal.png"); break;
            case 3: setImage("addProjectile.png"); break;
            case 4: setImage("pierce.png"); break;
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
                    case 3: player.addProjectile(); break;
                    case 4: player.increasePierce(); break;
                }
                getWorld().removeObject(this); // Remove upgrade after selection
            }
        }
    }
}
