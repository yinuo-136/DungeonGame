package dungeonmania.inventoryItem;

import dungeonmania.DungeonInfo;
import dungeonmania.response.models.ItemResponse;

public class Treasure implements InvItem {

    private String id;
    private String type = "treasure";

    public Treasure(String id){
        this.id = id;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
        
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {}

    @Override
    public String getId() {
        return id;
    }

}
