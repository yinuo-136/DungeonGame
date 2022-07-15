package dungeonmania.buildableEntity;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class Bow implements InvItem, Buildable {

    private String id;
    private String type = "bow";

    private int attackBonus;
    private int durability;

    public Bow(String id, int attackBonus, int durability){
        //TODO
    }

    public String getId() {
        //TODO
        return null;
    }
    public String getType() {
        //TODO
        return null;
    }

    public int getAttackBonus() {
        //TODO
        return 0;
    }

    public int getDurability() {
        //TODO
        return 0;
    }

    public void setDurability(int durability) {
        //TODO
    }

    public void craft() throws InvalidActionException {
        //TODO
    }

    public Boolean checkItemDestroyed() {
        return false;
    }
    
    @Override
    public void use() {
        //TODO
    }

    @Override
    public ItemResponse getItemResponse(){
        //TODO
        return null;
    }
}
