package dungeonmania.inventoryItem.Potion;
import dungeonmania.inventoryItem.InvItem;

public class InvincibilityPotion implements Potion, InvItem {


    private int duration;
    
    public InvincibilityPotion(int duration) {
        this.duration = duration;
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
    public void getItemResponse() {
        // TODO Auto-generated method stub
        
    }
    
}
