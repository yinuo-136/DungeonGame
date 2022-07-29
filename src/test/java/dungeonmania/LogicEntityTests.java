package dungeonmania;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import static dungeonmania.TestUtils.countEntityOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicEntityTests {
    @Test
    @DisplayName("Test the logic behavior of light bulb")
    public void testLogicLightBulb() {
        DungeonManiaController dmc = new DungeonManiaController();

        //test and logic
        DungeonResponse DungonRes = dmc.newGame("lightbulbAND", "c_movementTest_testMovementDown");
        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_off"));

        DungonRes = dmc.newGame("lightbulbANDtwo", "c_movementTest_testMovementDown");
        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_on"));

        DungonRes = dmc.newGame("lightbulbANDthree", "c_movementTest_testMovementDown");
        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_off"));

        //test or logic
        DungonRes = dmc.newGame("lightbulbOR", "c_movementTest_testMovementDown");
        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_off"));

        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_on"));

        //test xor logic
        DungonRes = dmc.newGame("lightbulbXOR", "c_movementTest_testMovementDown");

        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_off"));

        DungonRes = dmc.newGame("lightbulbXORtwo", "c_movementTest_testMovementDown");

        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_on"));

        //test co_and logic
        DungonRes = dmc.newGame("lightbulbCOAND", "c_movementTest_testMovementDown");

        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_off"));

        DungonRes = dmc.newGame("lightbulbCOANDtwo", "c_movementTest_testMovementDown");

        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_on"));

        DungonRes = dmc.newGame("lightbulbCOANDthree", "c_movementTest_testMovementDown");

        DungonRes = dmc.tick(Direction.RIGHT);
        DungonRes = dmc.tick(Direction.DOWN);
        DungonRes = dmc.tick(Direction.RIGHT);

        assertEquals(1, countEntityOfType(DungonRes, "light_bulb_off"));

        
    } 
}
