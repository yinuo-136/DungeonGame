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

import javax.tools.StandardJavaFileManager.PathFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.movingEntity.DijkstraAlgoPathFinder;
import dungeonmania.movingEntity.Mercenary;
import dungeonmania.movingEntity.Spider;
import dungeonmania.movingEntity.ZombieToast;
import dungeonmania.player.Player;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.staticEntities.Wall;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;



public class MovingEntityTests {
    // @Test
    // @DisplayName("Test the zombie and other mob basic movement")
    // public void testBasicMovement() {
    //     // Test movement
    //     Position startPos = new Position(2, 2);
    //     ZombieToast zombie = new ZombieToast(startPos, "1");
    //     Position expectPos = new Position(3, 2);
    //     Position expectPos2 = new Position(1, 2);
    //     Position expectPos3 = new Position(2, 1);
    //     Position expectPos4 = new Position(2, 3);
    //     List<Position> expectPositions = new ArrayList<Position>();
    //     expectPositions.add(expectPos);
    //     expectPositions.add(expectPos2);
    //     expectPositions.add(expectPos3);
    //     expectPositions.add(expectPos4);
    //     zombie.move();
    //     assertTrue(expectPositions.contains(zombie.getPosition()));

    //     // // Test zombie cannot pass through wall
    //     // // x x x
    //     // // x - -
    //     // // x x x
    //     // Wall w = new Wall(new Position(1, 1), "1");
    //     // Wall w2 = new Wall(new Position(2, 1), "2");
    //     // Wall w3 = new Wall(new Position(3, 1), "3");
    //     // Wall w4 = new Wall(new Position(1, 2), "4");
    //     // Wall w5 = new Wall(new Position(3, 3), "5");
    //     // Wall w6 = new Wall(new Position(2, 3), "6");
    //     // Wall w7 = new Wall(new Position(1, 3), "7");
    //     // zombie.move();
    //     // assertEquals(expectPos, zombie.getPosition());

    // }

    // @Test
    // @DisplayName("Test the zombie attack player") 
    // public void testZombieAttackPlayer() {
    //     Position startPlayerPos = new Position(1, 1);
    //     Position startZombiePos = new Position(1,1);
    //     Player player = new Player(startPlayerPos, 10, 10);
    //     ZombieToast zombie = new ZombieToast(startZombiePos, "1");
    //     double playerhealth = player.getHealth();
    //     double enemieshealth = zombie.getHealth();
    //     zombie.attack(player);
    //     assertEquals(playerhealth-(zombie.getDamage()/10), player.getHealth());
    //     assertEquals(enemieshealth-(player.getAttack()/5), zombie.getHealth());
    // }

    // @Test
    // @DisplayName("Test spider movement")
    // public void testSpiderMovement(){
    //     // x x x
    //     // x s x
    //     // x x x
    //     // test basic spider movement, without blocking
    //     Position startPos = new Position(2, 2);
    //     Spider spider = new Spider(startPos, "1");
    //     Position expectPos = new Position(2, 1);
    //     spider.move();
    //     assertEquals(expectPos, spider.getPos());
    //     Position newExpectPos = new Position(3, 1);
    //     spider.move();
    //     // x x s
    //     // x x x
    //     // x x x
    //     assertEquals(newExpectPos, spider.getPos());
    //     for (int i = 1; i <= 7; i++) {
    //         spider.move();
    //     }
    //     assertEquals(expectPos, spider.getPos());

    // }

    // @Test
    // @DisplayName("Test Mercenary movement")
    // public void testMercenaryMovement(){
    //     // Test Mercenary follow player
    //     // x x x
    //     // x M - - P
    //     // x x x
    //     Wall w = new Wall(new Position(1, 1), "1");
    //     Wall w2 = new Wall(new Position(2, 1), "2");
    //     Wall w3 = new Wall(new Position(3, 1), "2");
    //     Wall w4 = new Wall(new Position(1, 2), "3");
    //     Wall w5 = new Wall(new Position(3, 3), "4");
    //     Wall w6 = new Wall(new Position(2, 3), "5");
    //     Wall w7 = new Wall(new Position(1, 3), "6");

    //     Position startMPos = new Position(2, 2);
    //     Position startPPos = new Position(5, 2);
    //     Mercenary mercenary = new Mercenary(startMPos, "1");
    //     Player player = new Player(startPPos, 10, 0);
    //     mercenary.move(player);
    //     Position expectMPos = new Position(3, 2);
    //     assertEquals(expectMPos, mercenary.getPosition());
    //     mercenary.move(player);
    //     Position expectMPos2 = new Position(4, 2);
    //     mercenary.move(player);
    //     assertEquals(expectMPos2, mercenary.getPosition());
    // }

    // @Test
    // @DisplayName("Test bribe Mercenary")
    // public void testBribeMercenary(){

    // }
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
}
