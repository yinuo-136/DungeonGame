package dungeonmania.goal.basicGoal;

import dungeonmania.DungeonInfo;
import dungeonmania.Entity;
import dungeonmania.goal.Goal;
import dungeonmania.movingEntity.Moving;
import dungeonmania.staticEntities.ZombieToastSpawner;

public class enemiesGoal implements Goal{
    private DungeonInfo dungeonInfo;
    private int enemy_goal;

    public enemiesGoal(DungeonInfo info, int enemy_goal) {
        this.dungeonInfo = info;
        this.enemy_goal = enemy_goal;
    }

    @Override
    public String evalGoal() {
        for (Entity e : dungeonInfo.getEntityMap().values()) {
            if (e instanceof Moving || e instanceof ZombieToastSpawner) {
                return ":enemies";               
            }
        }
        return "";
    }
    
}
