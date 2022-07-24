package dungeonmania.inventoryItem;

import dungeonmania.DungeonInfo;
import dungeonmania.response.models.ItemResponse;

public class Sunstone implements InvItem{
    String id;
    private String type = "sun_stone";

    public Sunstone(String id){
        this.id = id;
    }

    @Override
    public void use() {
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {}

    @Override
    public String getId() {
        return this.id;
    }
}
