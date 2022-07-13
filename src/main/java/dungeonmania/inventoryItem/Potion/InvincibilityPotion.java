package dungeonmania.inventoryItem.Potion;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class InvincibilityPotion implements Potion, InvItem {


    private int duration;
    private String id;
    private String type = "invincibility_potion";
    
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
        // TODO Auto-generated method stub
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
        
    }
    
}
