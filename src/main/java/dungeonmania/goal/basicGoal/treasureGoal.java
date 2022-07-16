package dungeonmania.goal.basicGoal;

import dungeonmania.DungeonInfo;
import dungeonmania.goal.Goal;

public class treasureGoal implements Goal{
    private DungeonInfo dungeonInfo;
    
    public treasureGoal(DungeonInfo info) {
        this.dungeonInfo = info;
    }

    @Override
    public String evalGoal() {
        return ":treasure";
    }
    
    
}
