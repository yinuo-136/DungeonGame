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

// public class BuildableTests {
//     @Test
//     @DisplayName("Test buildable wood and arrow")
//     public void testWoodAndArrow() throws IllegalArgumentException, InvalidActionException{
//         DungeonManiaController controller = new DungeonManiaController();
//         controller.newGame("build_bow", "simple");
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         DungeonResponse response = controller.build("Bow");
//         List<ItemResponse> inventory = response.getInventory();
//         boolean succeed = false;
//         for (ItemResponse item : inventory) {
//             if (item.getType() == "Bow")
//                 succeed = true;
//         }
//         assertTrue(succeed);
//     }

//     // No map found that can build sheild using treasure
//     // @Test
//     // @DisplayName("Test buildable treasure")
//     // public void testTreasure() throws IllegalArgumentException, InvalidActionException{
//     //     DungeonManiaController controller = new DungeonManiaController();
//     //     controller.newGame("build_bow", "simple");
//     //     controller.tick(dungeonmania.util.Direction.RIGHT);
//     //     controller.tick(dungeonmania.util.Direction.RIGHT);
//     //     controller.tick(dungeonmania.util.Direction.RIGHT);
//     //     controller.tick(dungeonmania.util.Direction.RIGHT);
//     //     DungeonResponse response = controller.build("Bow");
//     //     List<ItemResponse> inventory = response.getInventory();
//     //     boolean succeed = false;
//     //     for (ItemResponse item : inventory) {
//     //         if (item.getType() == "Bow")
//     //             succeed = true;
//     //     }
//     //     assertTrue(succeed);
//     // }
    
//     @Test
//     @DisplayName("Test buildable key")
//     public void testUnusedKey() throws IllegalArgumentException, InvalidActionException{
//         DungeonManiaController controller = new DungeonManiaController();
//         controller.newGame("build_shield", "simple");
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         controller.tick(dungeonmania.util.Direction.RIGHT);
//         DungeonResponse response = controller.build("Shield");
//         List<ItemResponse> inventory = response.getInventory();
//         boolean succeed = false;
//         for (ItemResponse item : inventory) {
//             if (item.getType() == "Shield")
//                 succeed = true;
//         }
//         assertTrue(succeed);
//     }

// }
