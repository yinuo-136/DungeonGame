package dungeonmania.buildableEntity;

import java.util.List;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class Bow implements InvItem, Buildable {

    private String id;
    private static String type = "bow";

    private int durability;

    public Bow(String id, int durability){
        this.id = id;
        this.durability = durability;
    }

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void craft(DungeonInfo info) throws InvalidActionException {
        List<String> woodIdList = info.getInvItemIdsListByType("wood");
        List<String> arrowIdList = info.getInvItemIdsListByType("arrow");

        if ((woodIdList.size()<1) || (arrowIdList.size()<3) ) {
            throw new InvalidActionException("Insufficient Materials to craft bow!");
        }

        //Remove one wood from inventory
        info.removeInvItemById(woodIdList.get(0));

        //Remove three arrows from inventory
        info.removeInvItemById(arrowIdList.get(0));
        info.removeInvItemById(arrowIdList.get(1));
        info.removeInvItemById(arrowIdList.get(2));
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
