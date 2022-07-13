package dungeonmania.movingEntity;

import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ReverseDirection implements SpiderMovingState{
    private Spider spider;

    public ReverseDirection(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void onClockwise(Spider spider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCounterClockwise(Spider spider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void move() {
        List<Position> adjacentPositions = spider.getSpawnPosition().getAdjacentPositions();
        if (spider.getPos() == spider.getSpawnPosition()) {
            spider.setPosition(spider.getPos().translateBy(Direction.UP));
        }
        int index = adjacentPositions.indexOf(spider.getPos());
        // if there the location doesnt have boulders in it, move to the next location
        // else change state to circle direction
        spider.setPosition(adjacentPositions.get((index - 1) % adjacentPositions.size()));
        
    }
    
}
