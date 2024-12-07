import greenfoot.*;

public class upgrades extends Actor {
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
    }
}
