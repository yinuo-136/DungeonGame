package dungeonmania.inventoryItem;

import dungeonmania.DungeonInfo;
import dungeonmania.response.models.ItemResponse;

public class Bomb implements InvItem {

    private String id;
    private String type = "bomb";
    private int radius;

    public Bomb(String id, int radius) {
        this.id = id;
        this.radius = radius;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
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
    
}
