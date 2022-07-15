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
    private DungeonInfo dungeonInfo;

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

    public void craft() throws InvalidActionException {
        List<String> woodIdList = dungeonInfo.getInvItemIdsListByType("wood");
        List<String> arrowIdList = dungeonInfo.getInvItemIdsListByType("arrow");

        if ((woodIdList.size()<1) || (arrowIdList.size()<3) ) {
            throw new InvalidActionException("Insufficient Materials to craft bow!");
        }

        //Remove one wood from inventory
        dungeonInfo.removeInvItemById(woodIdList.get(0));

        //Remove three arrows from inventory
        dungeonInfo.removeInvItemById(arrowIdList.get(0));
        dungeonInfo.removeInvItemById(arrowIdList.get(1));
        dungeonInfo.removeInvItemById(arrowIdList.get(2));
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

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        this.dungeonInfo = dungeonInfo;
    }
}
