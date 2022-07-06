package dungeonmania.movingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast implements Moving {
    private double health;
    private int attackDamage;
    private Position position;
    private boolean isAlive = true;
    private List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT);
    
    public ZombieToast(Position position, double health, int attackDamage) {
        this.position = position;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public void move() {
        Random rand = new Random();
        Direction randDirection = directions.get(rand.nextInt(directions.size()));
        
        position = position.translateBy(randDirection);
    }

    @Override
    public void attack(Player player) {
        
    }

    @Override
    public Position getPosition() {
        
        return position;
    }

    public double getHealth() {
        return health;
    }

    public int getDamage() {
        return attackDamage;
    }

    public boolean isAlive() {
        return isAlive;
    }

    
    
}
