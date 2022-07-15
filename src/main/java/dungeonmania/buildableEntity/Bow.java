package dungeonmania.buildableEntity;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class Bow implements InvItem, Buildable {

    private String id;
    private static String type = "bow";

    private int attackBonus;
    private int durability;

    public Bow(String id, int attackBonus, int durability){
        this.id = id;
        this.attackBonus = attackBonus;
        this.durability = durability;
    }

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void craft() throws InvalidActionException {
        //TODO
    }

    public Boolean isItemDestroyed() {
        if (durability > 0) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void use() {
        //TODO
    }

    @Override
    public ItemResponse getItemResponse(){
        return new ItemResponse(this.id, type);
    }
}
