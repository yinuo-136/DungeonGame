package dungeonmania.movingEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dungeonmania.util.Direction;

public class RandomStrategy implements MercenaryMovingStrategy {
    private List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT);
    private List<String> movingConstrintItemList = Arrays.asList("Wall", "Boulder");

    public void move(Moving entity) {
        Random rand = new Random();
        Direction randDirection = directions.get(rand.nextInt(directions.size()));
        while (entity.getEntitiesByPosition(entity.getPosition().translateBy(randDirection)).contains(movingConstrintItemList)) {
            randDirection = directions.get(rand.nextInt(directions.size()));
        }
        entity.setPosition(entity.getPosition().translateBy(randDirection));
    }

    @Override
    public String getStatename() {
        // TODO Auto-generated method stub
        return "Random";
    }


}
