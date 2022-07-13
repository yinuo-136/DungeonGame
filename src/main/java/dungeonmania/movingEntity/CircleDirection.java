package dungeonmania.movingEntity;

import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class CircleDirection implements SpiderMovingState{
    private Spider spider;

    public CircleDirection(Spider spider) {
        this.spider = spider;
    }


    @Override
    public void onClockwise(Spider spider) {
        spider.setCurrentState(new CircleDirection(spider));
    }

    @Override
    public void onCounterClockwise(Spider spider) {
        spider.setCurrentState(new ReverseDirection(spider));
    }


    @Override
    public void move() {
        List<Position> adjacentPositions = spider.getSpawnPosition().getAdjacentPositions();
        if (spider.getPos() == spider.getSpawnPosition()) {
            spider.setPosition(spider.getPos().translateBy(Direction.UP));
        }
        int index = adjacentPositions.indexOf(spider.getPos());
        // if there the location doesnt have boulders in it, move to the next location
        // else change state to reverse direction
        spider.setPosition(adjacentPositions.get((index + 1) % adjacentPositions.size()));
        
    }
    
    
}
