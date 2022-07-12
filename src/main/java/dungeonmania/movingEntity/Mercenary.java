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
    private static double classHealth;
    private double health;
    private static int damage;
    private static int bribedDamage;
    private static int bribedDefence;
    private MercenaryMovingStrategy currentState = new NotBribedStrategy();
    private static int costToBribe;
    private static int bribeRadius;
    private String type = "mercenary";

    public Mercenary(Position position, String id) {
        this.position = position;
        this.id = id;
        this.health = classHealth;
    }
    
    public void move(Player player) {
        currentState.move(this);
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

    public boolean bribe(int bribeAmount) {
        if (bribeAmount >= costToBribe) {
            currentState = new BribedStrategy();
            damage = bribedDamage;
            return true;
        }
        return false;
    }

    public double getHealth() {
        return health;
    }  

    public void setHealth(double health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }
    
    public static void setDamage(int damage) {
        Mercenary.damage = damage;
    }

    public boolean isAlive() {
        if (this.getHealth() > 0){
            return true;
        }
        return false;
    }

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

    public static void setCostToBribe(int costToBribe) {
        Mercenary.costToBribe = costToBribe;
    }

    public static void setBribeRadius(int bribeRadius) {
        Mercenary.bribeRadius = bribeRadius;
    }

    @Override
    public List<String> getEntitiesByPosition(Position pos) {
        // TODO Auto-generated method stub
        return getEntitiesByPosition(position);
    }

    @Override
    public void setPosition(Position pos) {
        this.position = pos;
    }
    
    
}
