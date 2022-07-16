package dungeonmania;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dungeonmania.staticEntities.Boulder;
import dungeonmania.staticEntities.Door;
import dungeonmania.staticEntities.Exit;
import dungeonmania.staticEntities.FloorSwitch;
import dungeonmania.staticEntities.Portal;
import dungeonmania.staticEntities.Wall;
import dungeonmania.staticEntities.ZombieToastSpawner;
import dungeonmania.util.Position;

public class StaticEntityUnitTests {
    @Test
    @DisplayName("Wall test")
    public void testWall(){
        Wall w = new Wall(new Position(1, 1), "1");
        assertEquals(new Position(1, 1), w.getPos());
    }

    @Test
    @DisplayName("Exit test")
    public void testExit(){
        Exit e = new Exit(new Position(3, 2), "1");
        assertEquals(new Position(3, 2), e.getPos());
    }

    @Test
    @DisplayName("Boulder test")
    public void testBoulder(){
        Boulder b = new Boulder(new Position(10, 10), "1");
        assertEquals(new Position(10, 10), b.getPos());
        b.setPos(new Position(10, 9));
        assertEquals(new Position(10, 9), b.getPos());
    }

    @Test
    @DisplayName("Door test")
    public void testDoor(){
        Door d = new Door(new Position(3, 2), 1, "1");
        assertEquals(new Position(3, 2), d.getPos());
        assertEquals(1, d.getKey());
        assertEquals(false, d.isOpen());
    }

    @Test
    @DisplayName("FloorSwitch test")
    public void testFloorSwitch(){
        FloorSwitch f = new FloorSwitch(new Position(10, 10), "1");
        assertEquals(new Position(10, 10), f.getPos());
        assertEquals(false, f.isTriggered());
        f.setTriggered(true);
        assertEquals(true, f.isTriggered());
    }

    @Test
    @DisplayName("Portal test")
    public void testPortal(){
        Portal p = new Portal(new Position(10, 10), "red", "1");
        assertEquals(new Position(10, 10), p.getPos());
        assertEquals("red", p.getColour());
    }

    @Test
    @DisplayName("ZombieToastSpawner test")
    public void testZombieToastSpawner(){
        ZombieToastSpawner z = new ZombieToastSpawner(new Position(10, 10), "1");
        assertEquals(new Position(10, 10), z.getPos());
    }
}


