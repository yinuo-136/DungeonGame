package dungeonmania.movingEntity;

import java.util.List;
import java.util.Random;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Hydra extends Entity implements Moving, ZombieType {
    private String id;
    private Position position;
    private double health;
    private double damage;
    private MercenaryMovingStrategy currentState = new RandomStrategy();
    private String type = "hydra";
    private boolean bribed = false;
    private double hydra_health_increase_rate;
    private int hydra_health_increase_amount;

    public Hydra(Position position, String id) {
        this.id = id;
        this.position = position;
    }

    public boolean chanceTrue(){
        // if the random number is greater than 0 after subtract the rate(between 0 to 1) times by 100, then return true.
        Random rand = new Random();
        if (rand.nextInt(100) - hydra_health_increase_rate*100 > 0) {
            return true;
        }
        return false;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public void move() {
        currentState.move(this);
    }

    @Override
    public List<String> getEntitiesStringByPosition(Position pos) {
        return dungeonInfo.getEntitiesStringByPosition(pos);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Position getPos() {
        return position;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, position, false);
        return response;
    }

    @Override
    public void setConfig() {
        this.health = dungeonInfo.getSpecificConfig("hydra_health");
        this.damage = dungeonInfo.getSpecificConfig("hydra_attack");
        this.hydra_health_increase_rate = dungeonInfo.getSpecificConfigDouble("hydra_health_increase_rate");
        this.hydra_health_increase_amount = dungeonInfo.getSpecificConfig("hydra_health_increase_amount");
    }

    public int gethydra_health_increase_amount(){
        return hydra_health_increase_amount;
    }
    
}
