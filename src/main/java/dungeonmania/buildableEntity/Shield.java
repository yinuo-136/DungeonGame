package dungeonmania.buildableEntity;

import java.util.List;

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
        List<String> woodIdList = info.getInvItemIdsListByType("wood");
        List<String> treasureIdList = info.getInvItemIdsListByType("treasure");
        List<String> keyIdList = info.getInvItemIdsListByType("key");
         

        if ((woodIdList.size()<2) || ((treasureIdList.size()<1) && (keyIdList.size()<1)) ) {
            throw new InvalidActionException("Insufficient Materials to craft Shield!");
        }

        //Remove one wood from inventory
        info.removeInvItemById(woodIdList.get(0));
        info.removeInvItemById(woodIdList.get(1));

        //Remove Treasure or Key from Inventory (Give priority to Treasure)
        if (treasureIdList.size() < 1) {
            info.removeInvItemById(keyIdList.get(0));
        } else {
            info.removeInvItemById(treasureIdList.get(0));
        }
        
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
