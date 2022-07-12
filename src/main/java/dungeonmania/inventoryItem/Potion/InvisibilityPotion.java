package dungeonmania.inventoryItem.Potion;
import dungeonmania.inventoryItem.InvItem;

public class InvisibilityPotion implements Potion, InvItem {

    private static int duration;
    
    public InvisibilityPotion(int duration) {
        InvisibilityPotion.duration = duration;
    }
    @Override
    public int getDuration() {
        return duration;
    }
    @Override
    public void setDuration(int duration) {
        InvisibilityPotion.duration = duration;
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
