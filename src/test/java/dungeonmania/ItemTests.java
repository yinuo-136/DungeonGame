package dungeonmania;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ItemTests {
    @Test
    @DisplayName("Test using invisibility potion")
    public void testConsumeInvisibilityPotion() throws IllegalArgumentException, InvalidActionException {
        // pick up invincibility potion
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("advanced", "simple");
        controller.tick(Direction.RIGHT);
        // use invincibility potion
        DungeonResponse response = controller.tick("invincibility_potion");
        List<ItemResponse> inventory = response.getInventory();
        assertTrue(inventory.size() == 0);
    }

    @Test
    @DisplayName("Test using invincibility potion")
    public void testConsumeInvincibilityPotion() throws IllegalArgumentException, InvalidActionException {
        // pick up and use invincibility potion before invisibility potion
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("advanced", "simple");
        controller.tick(Direction.RIGHT);
        controller.tick("invincibility_potion");
        // pick up and use invincibility potion
        controller.tick(Direction.RIGHT);
        DungeonResponse response = controller.tick("invisibility_potion");
        List<ItemResponse> inventory = response.getInventory();
        assertTrue(inventory.size() == 0);
    }

    @Test
    @DisplayName("Using Bomb")
    public void testPlacingBomb() throws IllegalArgumentException, InvalidActionException {
        // pick up the bomb
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("bombs", "simple");
        controller.tick(Direction.DOWN);
        controller.tick(Direction.RIGHT);
        // placing the bomb
        DungeonResponse response = controller.tick("bomb");
        List<ItemResponse> inventory = response.getInventory();
        assertTrue(inventory.size() == 0);
    }

    @Test
    @DisplayName("Placing a bomb on an already active switch")
    public void testPlacingBombOnActiveSwitch() throws IllegalArgumentException, InvalidActionException {
        // push the boulder to activate switch
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("bombs", "simple");
        controller.tick(Direction.RIGHT);
        // pick up the bomb and place it at 4,3 which is adjacent to active switch
        controller.tick(Direction.DOWN);
        controller.tick(Direction.RIGHT);
        DungeonResponse response = controller.tick("bomb");
        List<EntityResponse> entities = response.getEntities();
        List<Position> bombRadius = new ArrayList<>();
        // bomb placed at 4,3
        // Everything in 1 radius should be gone except the player
        // 1 radius means should be a 3 x 3 square with player being in the center
        int y_coord = 2;
        int i = 0;
        int j = 0;
        while (i < 3) {
            int x_coord = 3;
            while (j < 3) {
                bombRadius.add(new Position(x_coord, y_coord));
                x_coord ++;
                j ++;
            }
            i ++;
            y_coord ++;
        }
        for (EntityResponse e : entities) {
            if (e.getType() != "player")
                assertFalse(bombRadius.contains(e.getPosition()));
        }
    }

    @Test
    @DisplayName("Placing a bomb on an already active switch")
    public void testPlacingBombThenActivatingSwitch() throws IllegalArgumentException, InvalidActionException {
        // pick up bomb and place at 4,3 adjacent to unactive switch
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("bombs", "simple");
        controller.tick(Direction.DOWN);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse response = controller.tick("bomb");
        // activate switch
        controller.tick(Direction.LEFT);
        controller.tick(Direction.LEFT);
        controller.tick(Direction.UP);
        controller.tick(Direction.RIGHT);
        List<EntityResponse> entities = response.getEntities();
        List<Position> bombRadius = new ArrayList<>();
        // bomb placed at 4,3
        // Everything in 1 radius should be gone except the player
        // so it should be a 3 x 3 square
        int y_coord = 2;
        int i = 0;
        int j = 0;
        while (i < 3) {
            int x_coord = 3;
            while (j < 3) {
                bombRadius.add(new Position(x_coord, y_coord));
                x_coord ++;
                j ++;
            }
            i ++;
            y_coord ++;
        }
        for (EntityResponse e : entities) {
            if (e.getType() != "player")
                assertFalse(bombRadius.contains(e.getPosition()));
        }
    }

    @Test
    @DisplayName("Testing larger bomb radius")
    public void testBombRadius2() throws IllegalArgumentException, InvalidActionException {
        // push the boulder to activate switch
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("bombs", "bomb_radius_2");
        controller.tick(Direction.RIGHT);
        // pick up the bomb and place it at 4,3 which is adjacent to active switch
        controller.tick(Direction.DOWN);
        controller.tick(Direction.RIGHT);
        DungeonResponse response = controller.tick("bomb");
        List<EntityResponse> entities = response.getEntities();
        List<Position> bombRadius = new ArrayList<>();
        // bomb placed at 4,3
        // Everything in 2 radius should be gone except the player
        // 1 radius means should be a 5 x 5 square with player being in the center
        int y_coord = 2;
        int i = 0;
        int j = 0;
        while (i < 5) {
            int x_coord = 3;
            while (j < 5) {
                bombRadius.add(new Position(x_coord, y_coord));
                x_coord ++;
                j ++;
            }
            i ++;
            y_coord ++;
        }
        for (EntityResponse e : entities) {
            if (e.getType() != "player")
                assertFalse(bombRadius.contains(e.getPosition()));
        }
    }
    //Need tests for the effectiveness of potion
}
