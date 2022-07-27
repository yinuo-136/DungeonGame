package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class Sceptre implements InvItem, Buildable{
    private String id;
    private static String type = "sceptre";
 
    private DungeonInfo dungeonInfo;

    public Sceptre() {
    }

    public Sceptre(String id) {
    }

    @Override
    public void craft() throws InvalidActionException {
        // TODO Auto-generated method stub
    }

    @Override
    public Boolean checkCraftable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
    }

    @Override
    public ItemResponse getItemResponse() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

}
