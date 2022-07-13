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
        // if the random direction is not a wall or a boulder, move the entity.
        while (entity.getEntitiesStringByPosition(entity.getPos().translateBy(randDirection)).stream().anyMatch(element -> movingConstrintItemList.contains(element))) {
            randDirection = directions.get(rand.nextInt(directions.size()));
        }
        entity.setPos(entity.getPos().translateBy(randDirection));
    }

    @Override
    public String getStatename() {
        return "Random";
    }


}
