package dungeonmania.useableItem.Potion;

import dungeonmania.useableItem.Item;

public abstract class Potion implements Item{
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
