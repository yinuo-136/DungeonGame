package dungeonmania.movingEntity;

import dungeonmania.util.Position;

public class Player {
    // this is a dummy player class to test the movement of the player
    // it is not a real player class
    private Position position;
    private boolean isAlive;
    private double health;
    private int damage;

    public Player(Position position, double health, int damage) {
        this.position = position;
        this.isAlive = true;
        this.health = health;
        this.damage = damage;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    
}
