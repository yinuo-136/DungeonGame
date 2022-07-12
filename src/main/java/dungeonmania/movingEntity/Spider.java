package dungeonmania.movingEntity;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moving {
    private String id;
    private Position position;
    private Position spawnPosition;
    private static double classHealth;
    private double health;
    private static int damage;
    private SpiderMovingState currentState = new CircleDirection(this); 
    private String type = "spider";
    
    public Spider(Position position, String id) {
        this.position = position;
        this.spawnPosition = position;
        this.id = id;
        this.health = classHealth;
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

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Spider";
    }
    
    public String getId() {
        return id;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, position, false);
        return response;
    }

    @Override
    public int getDamage() {
        // TODO Auto-generated method stub
        return damage;
    }
    
}
    
