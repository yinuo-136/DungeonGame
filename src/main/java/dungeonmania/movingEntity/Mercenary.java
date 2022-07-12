package dungeonmania.movingEntity;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary extends Entity implements Moving {
    private String id;
    private Position position;
    //private boolean isAlive = true;
    private double health;
    private static int damage;
    private MercenaryMovingStrategy currentState = new NotBribedStrategy();
    private static int costToBribe;
    private static int bribeRadius;
    private String type = "mercenary";

    public Mercenary(Position position, String id) {
        this.position = position;
        this.id = id;
    }
    
    public void move(Player player) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - (this.damage / 10));
            this.setHealth(this.getHealth() - (player.getAttack() / 5));
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
            return true;
        }
        return false;
    }

    @Override
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
    
    
}
