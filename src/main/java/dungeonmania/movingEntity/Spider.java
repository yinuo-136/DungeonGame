package dungeonmania.movingEntity;

import java.util.List;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moving {
    private int damage;
    private int SpawnRate;

    private String id;
    private Position position;
    private Position spawnPosition;
    private double health;
    private int timeToSpawn;
    private SpiderMovingState currentState = new CircleDirection(this); 
    private String type = "Spider";
    
    public Spider(Position position, String id) {
        this.position = position;
        this.spawnPosition = position;
        this.id = id;
        
    }
    
    public void setConfig(){
        this.health = dungeonInfo.getSpecificConfig("spider_health");
        this.damage = dungeonInfo.getSpecificConfig("spider_attack");
        this.SpawnRate = dungeonInfo.getSpecificConfig("spider_spawn_rate");
        this.timeToSpawn = SpawnRate;
    }

    public void move() {
        currentState.move();
    }
    
    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - damage);
            this.setHealth(this.getHealth() - player.getAttack());
        }
    }

    @Override
<<<<<<< HEAD
    public Position getPosition() {
=======
    public Position getPos() {
        // TODO Auto-generated method stub
>>>>>>> origin/master
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
    
    @Override
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public String getType() {
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
        return damage;
    }

    @Override
    public List<String> getEntitiesStringByPosition(Position pos) {
        return getEntitiesStringByPosition(position);
    }
    
    
}
    
