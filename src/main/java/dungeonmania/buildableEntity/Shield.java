package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class Shield implements InvItem,Buildable {
    private String id;
    private static String type = "shield";

    private int defenseBonus;
    private int durability;

    public Shield(String id, int defenseBonus, int durability){
        this.id = id;
        this.defenseBonus = defenseBonus;
        this.durability = durability;
    }

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void craft(DungeonInfo info) throws InvalidActionException {
        //TODO
    }

    public Boolean isItemDestroyed() {
        if (durability > 0) {
            return false;
        }

        return true;
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
