package dungeonmania.movingEntity;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider implements Moving {
    private Position position;
    private boolean isAlive = true;
    private double health;
    private int damage;
    
    public Spider(Position position, double health, int damage) {
        this.position = position;
        this.health = health;
        this.damage = damage;
    }
    
    public void move() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void attack(Player player) {
        // TODO Auto-generated method stub
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return position;
    }
    
}
    
