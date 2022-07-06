package dungeonmania.movingEntity;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider implements Moving {
    private Position position;
    private boolean isAlive = true;
    private double health;
    private int damage;
    private SpiderMovingState currentState = new CircleDirection(this); 
    
    public Spider(Position position, double health, int damage) {
        this.position = position;
        this.health = health;
        this.damage = damage;
    }
    
    public void move() {
        
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
    
    public void setCurrentState(SpiderMovingState state) {
        currentState = state;
    }
}
    
