package dungeonmania.movingEntity;

import java.util.ArrayList;

import dungeonmania.Entity;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class NotBribedStrategy implements MercenaryMovingStrategy {
    
    public String getStatename() {
        return "NotBribed";
    }

    @Override
    public void move(Entity movingEntity) {
        DijkstraAlgoPathFinder pathFinder = new DijkstraAlgoPathFinder();
        Position targetPos = movingEntity.getDungeonInfo().getPlayer().getPos();
        Direction direction = pathFinder.findNextPath(movingEntity, targetPos);
        if (direction == null) {
            return;
        }
        movingEntity.setPos(movingEntity.getPos().translateBy(direction));
    }
    
}
