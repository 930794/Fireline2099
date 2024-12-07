import greenfoot.*;

public class bossEnemy extends Enemy {
    private int specialAttackCooldown = 100;

    public bossEnemy(int speed, int health, int attack, String specialAttack) {
        this.speed = speed;
        this.health = health;
        this.attack = attack;
    }

    private void useSpecialAttack() {
        if (specialAttackCooldown <= 0) {
            Player player = findPlayer();
            if (player != null) {
                // Spawn a bullet aimed at the player
                bullets specialBullet = new bullets(3, 0, attack, player);
                getWorld().addObject(specialBullet, getX(), getY());
            }
            specialAttackCooldown = 100; // Reset cooldown
        }
        specialAttackCooldown--;
    }

    private Player findPlayer() {
        java.util.List<Player> players = getWorld().getObjects(Player.class);
        return players.isEmpty() ? null : players.get(0);
    }

    public void act() {
        super.act();
        useSpecialAttack();
    }
}
