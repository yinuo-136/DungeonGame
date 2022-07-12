package dungeonmania.inventoryItem.Potion;

import dungeonmania.inventoryItem.InvItem;

public abstract class Potion implements InvItem{
    int duration;

    public Potion(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
