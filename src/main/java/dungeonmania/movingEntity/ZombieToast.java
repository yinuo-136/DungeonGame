package dungeonmania.movingEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moving, ZombieType, Serializable {
    private String id;
    private double health;
    private double damage;
    private Position position;
    private String type = "zombie_toast";
    private MercenaryMovingStrategy currentState = new RandomStrategy();
    private MercenaryMovingStrategy prevState = new RandomStrategy();

    public ZombieToast(Position position, String id) {
        this.id = id;
        this.position = position;
    }

    public void setConfig(){
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void move() {
        currentState.move(this);
    }

    // @Override
    // public void attack(Player player) {
    //     while(player.isAlive() || this.isAlive()) {
    //         player.setHealth(player.getHealth() - damage);
    //         this.setHealth(this.getHealth() - player.getAttack());
    //     }
    //     // then remove the player or the enemy depend on who died first.
    // }

    @Override
    public Position getPos() {
        return position;
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

    // public boolean isAlive() {
    //     if (this.getHealth() > 0){
    //         return true;
    //     }
    //     return false;
    // }

    @Override
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, position, false);
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

    public void revertStrategy() {
        this.currentState = prevState;
    }
    
}
