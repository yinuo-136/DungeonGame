package dungeonmania.goal.basicGoal;

import dungeonmania.DungeonInfo;
import dungeonmania.goal.Goal;

public class bouldersGoal implements Goal{
    private DungeonInfo dungeonInfo;
    
    public bouldersGoal(DungeonInfo info) {
        this.dungeonInfo = info;
    }

    @Override
    public String evalGoal() {
        return ":boulders";
    }
    
}
