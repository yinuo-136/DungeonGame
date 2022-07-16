package dungeonmania.goal.basicGoal;

import dungeonmania.DungeonInfo;
import dungeonmania.goal.Goal;

public class enemiesGoal implements Goal{
    private DungeonInfo dungeonInfo;
    
    public enemiesGoal(DungeonInfo info) {
        this.dungeonInfo = info;
    }

    @Override
    public String evalGoal() {
        return ":enemies";
    }
    
}
