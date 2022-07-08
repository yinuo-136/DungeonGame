// package dungeonmania;

// import java.util.List;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import dungeonmania.DungeonManiaController;
// import dungeonmania.exceptions.InvalidActionException;
// import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.response.models.ItemResponse;

// public class ItemTests {
//     @Test
//     @DisplayName("Test using invisibility potion")
//     public void testConsumeInvisibilityPotion() throws IllegalArgumentException, InvalidActionException {
//         DungeonManiaController controller = new DungeonManiaController();
//         controller.newGame("advanced", "simple");
//         DungeonResponse response = controller.tick(dungeonmania.util.Direction.RIGHT);
//         List<ItemResponse> inventory = response.getInventory();
//         response = controller.tick(inventory.get(1).getId());
//         inventory = response.getInventory();
//         assertTrue(inventory.size() == 0);
//     }

//     @Test
//     @DisplayName("Test using invincibility potion")
//     public void testConsumeInvincibilityPotion() throws IllegalArgumentException, InvalidActionException {
//         DungeonManiaController controller = new DungeonManiaController();
//         controller.newGame("advanced", "simple");
//         DungeonResponse response = controller.tick(dungeonmania.util.Direction.RIGHT);
//         List<ItemResponse> inventory = response.getInventory();
//         controller.tick(inventory.get(1).getId());
//         response = controller.tick(dungeonmania.util.Direction.RIGHT);
//         inventory = response.getInventory();
//         response = controller.tick(inventory.get(1).getId());
//         inventory = response.getInventory();
//         assertTrue(inventory.size() == 0);
//     }

//     // Need tests for the effectiveness of potion
// }
