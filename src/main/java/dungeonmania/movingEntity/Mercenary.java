package dungeonmania.movingEntity;

import java.util.List;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary extends Entity implements Moving {
    private String id;
    private Position position;
    private double health;
    private double damage;
    private static int bribedDamage;
    private static int bribedDefence;
    private MercenaryMovingStrategy currentState = new NotBribedStrategy();
    private int costToBribe;
    private int bribeRadius;
    private String type = "mercenary";

    public Mercenary(Position position, String id) {
        this.position = position;
        this.id = id;
    }
    
    public void setConfig(){
        this.health = dungeonInfo.getSpecificConfig("mercenary_health");
        this.damage = dungeonInfo.getSpecificConfig("mercenary_attack");
        this.costToBribe = dungeonInfo.getSpecificConfig("bribe_amount");
        this.bribeRadius = dungeonInfo.getSpecificConfig("bribe_radius");
    }

    public void move() {
        currentState.move(this);
    }
    
    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - (this.damage / 10));
            this.setHealth(this.getHealth() - (player.getAttack() / 5));
        }
    }

    @Override
    public Position getPos() {
        return position;
    }

    public boolean bribe(int bribeAmount) {
        if (bribeAmount >= costToBribe) {
            currentState = new BribedStrategy();
            damage = bribedDamage;
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

    public boolean isAlive() {
        if (this.getHealth() > 0){
            return true;
        }
        return false;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, position, true);
        return response;
    }

    @Override
    public void setPos(Position pos) {
        this.position = pos;
    }

    @Override
    public List<String> getEntitiesStringByPosition(Position pos) {
        return dungeonInfo.getEntitiesStringByPosition(pos);
    }
    
    
}
