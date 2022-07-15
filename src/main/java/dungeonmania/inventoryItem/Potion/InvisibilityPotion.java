package dungeonmania.inventoryItem.Potion;
import dungeonmania.DungeonInfo;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class InvisibilityPotion implements Potion, InvItem {
    private int duration;
    private String id;
    private String type = "invisibility_potion";
    private DungeonInfo dungeonInfo;
    
    public InvisibilityPotion(int duration, String id) {
        this.duration = duration;
        this.id = id;
    }
    @Override
    public int getDuration() {
        return duration;
    }
    @Override
    public void setDuration(int duration) {
        this.duration = duration;
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
    public void setConfig() {
        this.duration = dungeonInfo.getSpecificConfig("invisibility_potion_duration");
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        this.dungeonInfo = dungeonInfo;
    }
}
