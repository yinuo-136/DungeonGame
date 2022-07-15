package dungeonmania.inventoryItem;

import dungeonmania.DungeonInfo;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.staticEntities.PlacedBomb;

public class Bomb implements InvItem {

    private String id;
    private String type = "bomb";
    private int radius;
    private DungeonInfo info;

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
        PlacedBomb bomb = new PlacedBomb(info.getPlayer().getPos(), this.id, this.radius);
        info.getItemList().remove(this);
        info.getEntityMap().put(this.id, bomb);
        info.addTick(bomb);
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
        
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {}
    
}
