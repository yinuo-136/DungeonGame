package dungeonmania.movingEntity;

import java.util.List;
import java.util.Random;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Assassin extends Entity implements Moving, MercenaryType {
    private String id;
    private double health;
    private double damage;
    private Position position;
    private String type = "assassin";
    private MercenaryMovingStrategy currentState = new NotBribedStrategy();
    private int costToBribe;
    private int bribeRadius;
    private Double bribe_fail_rate;
    private int assassin_recon_radius;
    private static int bribedDamage;
    private boolean bribed = false;

    public Assassin(Position position, String id) {
        this.id = id;
        this.position = position;
    }

    public void setConfig(){
        this.health = dungeonInfo.getSpecificConfig("mercenary_health");
        this.damage = dungeonInfo.getSpecificConfig("mercenary_attack");
        this.costToBribe = dungeonInfo.getSpecificConfig("bribe_amount");
        this.bribeRadius = dungeonInfo.getSpecificConfig("bribe_radius");
        this.bribe_fail_rate = dungeonInfo.getSpecificConfigDouble("assassin_bribe_fail_rate");
        this.assassin_recon_radius = dungeonInfo.getSpecificConfig("assassin_recon_radius");
    }

    public int getCostToBribe() {
        return costToBribe;
    }

    public boolean chanceTrue(){
        Random rand = new Random();
        if (rand.nextInt(100) - bribe_fail_rate*100 > 0) {
            return true;
        }
        return false;
    }

    public boolean checkBribeAmoountEnough(int bribeAmount) {
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
        if (checkBribeAmoountEnough(bribeAmount) && chanceTrue()) {
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
    
    public Boolean getBribed() {
        return bribed;
    }
}
