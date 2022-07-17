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
