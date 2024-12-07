import greenfoot.*;

public class upgradeVisuals extends upgrades {
    private String upgradeType;

    public upgradeVisuals(String upgradeType) {
        this.upgradeType = upgradeType;
        setImage(new GreenfootImage(upgradeType, 20, Color.WHITE, Color.BLACK));
    }

    public void applyUpgrade(Player player) {
        switch (upgradeType) {
            case "Attack Up":
                attackUp(player);
                break;
            case "Fast Attack":
                fastAttack(player);
                break;
            case "Heal":
                heal(player);
                break;
            case "Add Projectile":
                addProjectile(player);
                break;
            case "Pierce":
                pierce(player);
                break;
        }
    }

    public void act() {
        Actor player = getOneIntersectingObject(Player.class);
        if (player != null) {
            applyUpgrade((Player) player);
            getWorld().removeObject(this);
        }
    }
}
