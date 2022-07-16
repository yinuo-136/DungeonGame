package dungeonmania.movingEntity;

import java.util.List;
import java.util.Random;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Spider extends Entity implements Moving {
    private static int damage;
    private static int SpawnRate;
    private static int timeToSpawn;
    private static double classHealth;

    private String id;
    private Position position;
    private Position spawnPosition;
    private double health;
    private SpiderMovingState currentState = new CircleDirection(this); 
    private String type = "spider";
    
    public Spider(Position position, String id) {
        this.position = position;
        this.spawnPosition = position;
        this.id = id;
        this.health = classHealth;
    }
    
    public void setConfig(){
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
    public Position getPos() {
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
    
    @Override
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public String getType() {
        return type;
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
        return dungeonInfo.getEntitiesStringByPosition(pos);
    }

    @Override
    public void setPos(Position pos) {
        this.position = pos;
    }

    public static void setDamage(int damage) {
        Spider.damage = damage;
    }

    public static void setSpawnRate(int spawnRate) {
        SpawnRate = spawnRate;
    }

    public static void setClassHealth(double classHealth) {
        Spider.classHealth = classHealth;
    }
   
    public static void setTimeToSpawn(int timeToSpawn) {
        Spider.timeToSpawn = timeToSpawn;
    }

    public static boolean spawn() {
        if (SpawnRate == 0) {
            return false;
        }
        timeToSpawn = timeToSpawn - 1;
        if (timeToSpawn <= 0) {
            timeToSpawn = SpawnRate;
            return true;
        }

        return false;
    }

    public static Position generateSpawnPos(Position p) {
        Random r = new Random();
        int x = r.ints(-10, 10).findFirst().getAsInt();
        int y = r.ints(-10, 10).findFirst().getAsInt();
        return p.translateBy(x, y);
    }
}
    
