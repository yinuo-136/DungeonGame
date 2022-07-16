package dungeonmania.goal.basicGoal;

import dungeonmania.DungeonInfo;
import dungeonmania.goal.Goal;

public class exitGoal implements Goal{
    private DungeonInfo dungeonInfo;

    public exitGoal(DungeonInfo info) {
        this.dungeonInfo = info;
    }

    @Override
    public String evalGoal() {
        return ":exit";
    }
    
}
