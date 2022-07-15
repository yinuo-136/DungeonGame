package dungeonmania.inventoryItem.Potion;
import dungeonmania.DungeonInfo;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.player.InvincibleState;
import dungeonmania.player.Player;
import dungeonmania.response.models.ItemResponse;

public class InvincibilityPotion implements Potion, InvItem {

    private int duration;
    private String id;
    private String type = "invincibility_potion";
    private DungeonInfo dungeonInfo;
    
    public InvincibilityPotion(int duration, String id) {
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
        Player player = dungeonInfo.getPlayer();
        player.addPotion(this);
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
        
    }
    @Override
    public void setConfig() {
        this.duration = dungeonInfo.getSpecificConfig("invincibility_potion_duration");
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        this.dungeonInfo = dungeonInfo;
    }
    @Override
    public void takeAction() {
        Player player = dungeonInfo.getPlayer();
        player.setPlayerState(new InvincibleState(player, duration));
    }
    
    
}
