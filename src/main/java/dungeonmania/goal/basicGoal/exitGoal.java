package dungeonmania.goal.basicGoal;

import dungeonmania.DungeonInfo;
import dungeonmania.Entity;
import dungeonmania.goal.Goal;
import dungeonmania.player.Player;
import dungeonmania.staticEntities.Exit;
import dungeonmania.util.Position;

public class exitGoal implements Goal{
    private DungeonInfo dungeonInfo;

    public exitGoal(DungeonInfo info) {
        this.dungeonInfo = info;
    }

    @Override
    public String evalGoal() {
        Player player = dungeonInfo.getPlayer();
        if (player == null) {
            return ":exit";
        }
        Position p = player.getPos();
        Position exit = null;
        for (Entity e : dungeonInfo.getEntityMap().values()) {
            if (e instanceof Exit) {
                exit = e.getPos();
            }
        } 

        if (p.equals(exit)) {
            return "";
        }
        return ":exit";
    }
    
}
