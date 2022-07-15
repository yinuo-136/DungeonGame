package dungeonmania;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dungeonmania.DungeonManiaController;
import dungeonmania.buildableEntity.Bow;
import dungeonmania.buildableEntity.Shield;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;

public class BuildableTests {
    @Test
    @DisplayName("Test Bow Unit")
    public void testBowUnit() {
        Bow bow = new Bow("bow1", 10);

        assertEquals("bow1", bow.getId());
        assertEquals("bow", bow.getType());
        assertEquals(10, bow.getDurability());

        assertEquals("bow1", bow.getItemResponse().getId());
        assertEquals("bow", bow.getItemResponse().getType());

    }

    @Test
    @DisplayName("Test Shield Unit")
    public void testShieldUnit() {
        Shield shield = new Shield("shield1", 10, 10);

        assertEquals("shield1", shield.getId());
        assertEquals("shield", shield.getType());
        assertEquals(10, shield.getDefenseBonus());
        assertEquals(10, shield.getDurability());

        assertEquals("shield1", shield.getItemResponse().getId());
        assertEquals("shield", shield.getItemResponse().getType());
        
    }

    @Test
    @DisplayName("Test Bow Destroyed")
    public void testBowDestroyed() {
        Bow bow = new Bow("bow1", 10);

        bow.setDurability(0);

        assertTrue(bow.isItemDestroyed());

    }

    @Test
    @DisplayName("Test Shield Destroyed")
    public void testShieldDestroyed() {
        Shield shield = new Shield("shield1", 10, 10);

        shield.setDurability(0);

        assertTrue(shield.isItemDestroyed());
        
    }

    @Test
    @DisplayName("Test invalid craft request")
    public void testInvalidCraft() {
        assertThrows(IllegalArgumentException.class,
            () -> {
                DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("build_bow", "simple");
            controller.tick(dungeonmania.util.Direction.RIGHT);
            controller.tick(dungeonmania.util.Direction.RIGHT);
            controller.tick(dungeonmania.util.Direction.RIGHT);
            controller.tick(dungeonmania.util.Direction.RIGHT);
            controller.build("Random Item Name");
            });
    }

    @Test
    @DisplayName("Test crafting bow without required items")
    public void testInsufficientItemForBow() {
        assertThrows(InvalidActionException.class,
            () -> {
                DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("build_bow", "simple");
            controller.build("bow");
            });
    }

    @Test
    @DisplayName("Test crafting shield without required items")
    public void testInsufficientItemForShield() {
        assertThrows(InvalidActionException.class,
            () -> {
                DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("build_shield", "simple");
            controller.build("shield");
            });
    }

    @Test
    @DisplayName("Test buildable wood and arrow")
    public void testWoodAndArrow() throws IllegalArgumentException, InvalidActionException{
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("build_bow", "simple");
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        DungeonResponse response = controller.build("bow");
        List<ItemResponse> inventory = response.getInventory();
        boolean succeed = false;
        for (ItemResponse item : inventory) {
            if (item.getType() == "bow")
                succeed = true;
        }
        assertTrue(succeed);
    }

    //No map found that can build sheild using treasure
/*     @Test
    @DisplayName("Test buildable treasure")
    public void testTreasure() throws IllegalArgumentException, InvalidActionException{
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("build_bow", "simple");
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        DungeonResponse response = controller.build("Bow");
        List<ItemResponse> inventory = response.getInventory();
        boolean succeed = false;
        for (ItemResponse item : inventory) {
            if (item.getType() == "Bow")
                succeed = true;
        }
        assertTrue(succeed);
    } */
    
    @Test
    @DisplayName("Test buildable key")
    public void testUnusedKey() throws IllegalArgumentException, InvalidActionException{
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("build_shield", "simple");
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        controller.tick(dungeonmania.util.Direction.RIGHT);
        DungeonResponse response = controller.build("shield");
        List<ItemResponse> inventory = response.getInventory();
        boolean succeed = false;
        for (ItemResponse item : inventory) {
            if (item.getType() == "shield")
                succeed = true;
        }
        assertTrue(succeed);
    }

}
