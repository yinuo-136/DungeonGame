package dungeonmania.movingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dungeonmania.player.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast implements Moving {
    private double health;
    private int damage;
    private Position position;
    //private boolean isAlive = true;
    private List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT);
    
    public ZombieToast(Position position, double health, int damage) {
        this.position = position;
        this.health = health;
        this.damage = damage;
    }

    public void move() {
        Random rand = new Random();
        Direction randDirection = directions.get(rand.nextInt(directions.size()));
        
        position = position.translateBy(randDirection);
    }

    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - this.damage);
            this.setHealth(this.getHealth() - player.getAttack());
        }
        // then remove the player or the enemy depend on who died first.
    }

    @Override
    public Position getPosition() {
        
        return position;
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

    public boolean isAlive() {
        if (this.getHealth() > 0){
            return true;
        }
        return false;
    }

    
    
}
