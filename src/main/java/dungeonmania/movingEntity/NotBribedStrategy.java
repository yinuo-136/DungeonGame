package dungeonmania.movingEntity;

import java.util.ArrayList;

import dungeonmania.Entity;
import dungeonmania.util.Direction;

public class NotBribedStrategy implements MercenaryMovingStrategy {
    
    public String getStatename() {
        return "NotBribed";
    }

    @Override
    public void move(Entity movingEntity) {
        DijkstraAlgoPathFinder pathFinder = new DijkstraAlgoPathFinder();
        Direction direction = pathFinder.findNextPath(movingEntity);
        if (direction == null) {
            return;
        }
        movingEntity.setPos(movingEntity.getPos().translateBy(direction));
    }
    
}
