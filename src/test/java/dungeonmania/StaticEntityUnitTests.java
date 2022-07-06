package dungeonmania;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dungeonmania.staticEntities.Wall;
import dungeonmania.util.Position;

public class StaticEntityUnitTests {
    @Test
    @DisplayName("Wall test")
    public void testWall(){
        Wall w = new Wall(1, 1);
        assertEquals(new Position(1, 1), w.getPos());
    }
}
