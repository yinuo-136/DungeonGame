package dungeonmania.movingEntity;

import java.io.Serializable;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary extends Entity implements Moving, Serializable, MercenaryType  {
    private String id;
    private Position position;
    private double health;
    private double damage;
    private static int bribedDamage;
    private static int bribedDefence;
    private MercenaryMovingStrategy currentState = new NotBribedStrategy();
    private MercenaryMovingStrategy prevState = new NotBribedStrategy();

    private int costToBribe;
    private int bribeRadius;
    private String type = "mercenary";
    private boolean bribed = false;

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
    public Position getPos() {
        return position;
    }

    public int getCostToBribe() {
        return costToBribe;
    }

    public boolean checkBribeAmoountEnough(int bribeAmount){
        if (bribeAmount >= costToBribe) {
            return true;
        }
        return false;
    }

    public boolean checkBribeDistance(Player player){
        if (Math.abs(player.getPos().getX() - position.getX()) >  bribeRadius || Math.abs(player.getPos().getY() - position.getY()) > bribeRadius) {
            // if the player is not within the radius of the mercenary bribe radius
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bribeAmount
     * @return true if the mercenary was bribed, false otherwise.
     */
    public boolean bribe(int bribeAmount) {
        if (checkBribeAmoountEnough(bribeAmount)) {
            currentState = new BribedStrategy();
            damage = bribedDamage;
            bribed = true;
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
    
    public void setStrategy(MercenaryMovingStrategy strategy) {
        this.currentState = strategy;
    }

    public MercenaryMovingStrategy getCurrentState() {
        return currentState;
    }

    public Boolean getBribed() {
        return bribed;
    }

    public void setPlayerInvisibleStrategy(Player player) {
        prevState = currentState;
        this.currentState = new RandomStrategy();
    }

    public void revertStrategy() {
        this.currentState = prevState;
    }

    public void setPlayerInvincibleStrategy() {
        prevState = currentState;
        this.currentState = new RunAwayStrategy();
    }
}
