package dungeonmania.buildableEntity;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class Shield implements InvItem,Buildable {
    private String id;
    private String type = "shield";

    private int sheildBonus;
    private int durability;

    public Shield(String id, int sheildBonus, int durability){
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

    public int getSheildBonus() {
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
