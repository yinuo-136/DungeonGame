package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;
import static dungeonmania.TestUtils.getGoals;
import static dungeonmania.TestUtils.countEntityOfType;
import static dungeonmania.TestUtils.getValueFromConfigFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.movingEntity.DijkstraAlgoPathFinder;
import dungeonmania.movingEntity.NewDijkstraAlgoPathFinder;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import dungeonmania.exceptions.InvalidActionException;


public class MovingEntityTests {
    @Test
    public void testDijskras() {
        // No of vertices
        int v = 8;

        // Adjacency list for storing which vertices are connected
        ArrayList<ArrayList<Integer>> adj =
                            new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        DijkstraAlgoPathFinder pathFinder = new DijkstraAlgoPathFinder();
        // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        pathFinder.addEdge(adj, 0, 1);
        pathFinder.addEdge(adj, 0, 3);
        pathFinder.addEdge(adj, 1, 2);
        pathFinder.addEdge(adj, 3, 4);
        pathFinder.addEdge(adj, 3, 7);
        pathFinder.addEdge(adj, 4, 5);
        pathFinder.addEdge(adj, 4, 6);
        pathFinder.addEdge(adj, 4, 7);
        pathFinder.addEdge(adj, 5, 6);
        pathFinder.addEdge(adj, 6, 7);
        int source = 0, dest = 7;
        List<Integer> path = Arrays.asList(0,3,7);
        assertEquals(3, path.get(1));
        assertEquals(path, pathFinder.printShortestDistance(adj, source, dest, v));
    }

    @Test
    @DisplayName("Test reverse movement of spiders")
    public void SpiderReverseMovement(){
        // x x B
        // x S x
        // x x x

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_ReverseMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        Position expectedPosition = pos.translateBy(Direction.UP);
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition, getEntities(res, "spider").get(0).getPosition());

        //   4 5 6
        // 4 x S B
        // 5 x x x
        // 6 x x x
        // spider move counterclockwise cause boulder
        Position expectedPosition2 = new Position(4, 4);
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition2, getEntities(res, "spider").get(0).getPosition());

        Position expectedPosition3 = new Position(4, 5);
        res = dmc.tick(Direction.UP);
        assertEquals(expectedPosition3, getEntities(res, "spider").get(0).getPosition());
    }

    @Test
    @DisplayName("Test spiders stuck in between boulder")
    public void SpiderBetweenTwoBoulder(){
        // B x B
        // x S x
        // x x x

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_spiderStuckTwoBoulder", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        Position expectedPosition = pos.translateBy(Direction.UP);
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition, getEntities(res, "spider").get(0).getPosition());

        //   4 5 6
        // 4 B S B
        // 5 x x x
        // 6 x x x
        // spider move counterclockwise cause boulder
        Position expectedPosition2 = new Position(5, 4);
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition2, getEntities(res, "spider").get(0).getPosition());
        
        res = dmc.tick(Direction.UP);
        assertEquals(expectedPosition2, getEntities(res, "spider").get(0).getPosition());
    }

    @Test
    @DisplayName("Test spiders in between boulder")
    public void SpiderBetweenTwoBoulder2(){
        // x x B
        // B S x
        // x x x

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_spiderTest_spiderTwoBoulder", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "spider").get(0).getPosition();

        Position expectedPosition = pos.translateBy(Direction.UP);
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition, getEntities(res, "spider").get(0).getPosition());

        //   4 5 6
        // 4 x S B
        // 5 B x x
        // 6 x x x
        // spider move counterclockwise cause boulder
        Position expectedPosition2 = new Position(4, 4);
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition2, getEntities(res, "spider").get(0).getPosition());

        Position expectedPosition3 = new Position(5, 4);
        res = dmc.tick(Direction.UP);
        assertEquals(expectedPosition3, getEntities(res, "spider").get(0).getPosition());
    }

    @Test
    public void testZombieBasicMovement() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_zombieTest_BasicMovement", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();
        Position expectedPosition = pos.translateBy(Direction.LEFT);

        //   1 2 3
        // 0 W W W
        // 1 x Z W
        // 2 W W W
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition, getEntities(res, "zombie_toast").get(0).getPosition());

    }
    @Test
    public void testMercenarybasicMovement() {
        // test if mercenary will approach player
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos.translateBy(Direction.LEFT);

        //   0 1 2 3
        // 0 x W W W
        // 1 P x M W
        // 2 x W W W
        res = dmc.tick(Direction.LEFT);
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
    }

    @Test
    public void testMercenarybasicMovement2() {
        // test if mercenary will approach if it is directly above player
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mercenaryTest_BasicMovement", "c_battleTests_basicMercenaryMercenaryDies");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos.translateBy(Direction.UP);

        res = dmc.tick(Direction.UP);
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
        
    }
    
    @Test
    public void testMercenaryBribed() throws IllegalArgumentException, InvalidActionException{
        // test if mercenary can be bribe and its behaviour after bribed
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mercenaryTest_bribe", "c_movementTest_testMovementDown");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos.translateBy(Direction.LEFT);

        res = dmc.tick(Direction.RIGHT);
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());

        String mercenaryId = getEntities(res, "mercenary").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.interact(mercenaryId));
        res = dmc.tick(Direction.LEFT);
        Position expectedPosition2 = expectedPosition.translateBy(Direction.LEFT);
        assertEquals(expectedPosition2, getEntities(res, "mercenary").get(0).getPosition());
    }

    @Test
    public void testZombieTrapInWall(){
        //test zombie does not move if it is trapped in a wall
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_trapZombieWithWall", "c_spiderTest_basicMovement");
        Position pos = getEntities(res, "zombie_toast").get(0).getPosition();
        Position expectedPosition = pos;
        res = dmc.tick(Direction.DOWN);
        assertEquals(expectedPosition, getEntities(res, "zombie_toast").get(0).getPosition());
    }

    @Test
    public void testMercenaryPlayerOutOfRange(){
        //test if mercenary will not move if player is out of range
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mercenaryTest_playerNotFound", "c_movementTest_testMovementDown");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos;
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
    }

    @Test 
    public void testMercenaryAwayAndToward(){
        //test if mercenary will move away from player if the player is in invincible state and move towards player if it is not
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mercenaryAwayAndToward", "c_mercenaryAwayAndToward");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos.translateBy(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        String invinciblePotionId = getInventory(res, "invincibility_potion").get(0).getId();
        //make sure the mercenary working property, so it get closer to the player
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
        //consume the potion
        res = assertDoesNotThrow(() -> dmc.tick(invinciblePotionId));
        for (int i = 0; i < 2; i++) {
            res = dmc.tick(Direction.RIGHT);
            res = dmc.tick(Direction.LEFT);
        }
        for (int i = 0; i < 4; i++) {
            res = dmc.tick(Direction.UP);
        }
        for (int i = 0; i < 2; i++) {
            res = dmc.tick(Direction.RIGHT);
            res = dmc.tick(Direction.LEFT);
        }
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(1, 2), getEntities(res, "mercenary").get(0).getPosition());

    }
    @Test 
    public void testMercenaryPlayerUnreachable(){
        //test if mercenary will not move toward player if it is unreachable
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mercenaryPlayerUnreachable", "c_mercenaryAwayAndToward");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos;
        res = dmc.tick(Direction.LEFT);
        String invinciblePotionId = getInventory(res, "invincibility_potion").get(0).getId();
        //make sure the mercenary working property, so it dont move
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
        //consume the potion
        res = assertDoesNotThrow(() -> dmc.tick(invinciblePotionId));
        res = dmc.tick(Direction.LEFT);
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
        res = dmc.tick(Direction.RIGHT);
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());        
    }

    @Test
    @DisplayName("Test if movement entity would try to avoid swamp tile")
    public void testSwampTile(){
        //   0 1 2 3 4
        // 0 w w w w w
        // 1 P x S M w
        // 2 x x x x w
        // 3 w w w w w
        
        // check if the mencerary would go around the swamp tile to reach player
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest", "c_swamp");
        Position pos = getEntities(res, "mercenary").get(0).getPosition();
        Position expectedPosition = pos.translateBy(Direction.DOWN);
        res = dmc.tick(Direction.UP);
        assertEquals(expectedPosition, getEntities(res, "mercenary").get(0).getPosition());
        res = dmc.tick(Direction.UP);
        Position expectedPosition2 = expectedPosition.translateBy(Direction.LEFT);
        assertEquals(expectedPosition2, getEntities(res, "mercenary").get(0).getPosition());
        res = dmc.tick(Direction.UP);
        Position expectedPosition3 = expectedPosition2.translateBy(Direction.LEFT);
        assertEquals(expectedPosition3, getEntities(res, "mercenary").get(0).getPosition());

    }
}
