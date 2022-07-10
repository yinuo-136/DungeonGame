package dungeonmania.movingEntity;

import dungeonmania.player.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider implements Moving {
    private Position position;
    private Position spawnPosition;
    //private boolean isAlive = true;
    private double health;
    private int damage;
    private SpiderMovingState currentState = new CircleDirection(this); 
    
    public Spider(Position position, double health, int damage) {
        this.position = position;
        this.spawnPosition = position;
        this.health = health;
        this.damage = damage;
    }
    
    public void move() {
        currentState.move();
    }
    
    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - this.damage);
            this.setHealth(this.getHealth() - player.getAttack());
        }
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return position;
    }
    
    public void setCurrentState(SpiderMovingState state) {
        currentState = state;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }

    public boolean isAlive() {
        if (this.getHealth() > 0){
            return true;
        }
        return false;
    }

    public SpiderMovingState getCurrentState() {
        return currentState;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    
}
    