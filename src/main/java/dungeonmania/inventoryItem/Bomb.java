package dungeonmania.inventoryItem;

import dungeonmania.DungeonInfo;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.staticEntities.PlacedBomb;
import dungeonmania.util.Position;

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
        int x = info.getPlayer().getPos().getX();
        int y = info.getPlayer().getPos().getY();
        Position bomb_pos = new Position(x, y);
        PlacedBomb bomb = new PlacedBomb(bomb_pos, this.id, this.radius);
        bomb.setDungeonInfo(this.info);
        info.getEntityMap().put(this.id, bomb);
        info.addTick(bomb);
        info.getItemList().remove(this);
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
        
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        info = dungeonInfo;
    }
    
}
