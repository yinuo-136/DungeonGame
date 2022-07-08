package dungeonmania.movingEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import dungeonmania.util.Direction;

// public class DijkstraAlgoPathFinder {
    //dijkstra's algorithm
    // public static void findPath(int[][] map, int startX, int startY, int endX, int endY) {
    //     int[][] dist = new int[map.length][map[0].length];
    //     int[][] prev = new int[map.length][map[0].length];
    //     for (int i = 0; i < map.length; i++) {
    //         for (int j = 0; j < map[0].length; j++) {
    //             dist[i][j] = Integer.MAX_VALUE;
    //             prev[i][j] = -1;
    //         }
    //     }
    //     dist[startX][startY] = 0;
    //     prev[startX][startY] = -1;
    //     for (int i = 0; i < map.length; i++) {
    //         for (int j = 0; j < map[0].length; j++) {
    //             for (int k = 0; k < 4; k++) {
    //                 int x = i + Direction.values()[k].getOffset().getX();
    //                 int y = j + Direction.values()[k].getOffset().getY();
    //                 if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
    //                     if (dist[i][j] + 1 < dist[x][y]) {
    //                         dist[x][y] = dist[i][j] + 1;
    //                         prev[x][y] = k;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     int x = endX;
    //     int y = endY;
    //     while (prev[x][y] != -1) {
    //         System.out.println("(" + x + ", " + y + ")");
    //         int k = prev[x][y];
    //         x = x + Direction.values()[k].getOffset().getX();
    //         y = y + Direction.values()[k].getOffset().getY();
    //     }
    //     System.out.println("(" + x + ", " + y + ")");
    // }
// } 
public class DijkstraAlgoPathFinder {
 
    // Driver Program
    public static void main(String args[])
    {
        // No of vertices
        int v = 8;
 
        // Adjacency list for storing which vertices are connected
        ArrayList<ArrayList<Integer>> adj =
                            new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
 
        // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 1, 2);
        addEdge(adj, 3, 4);
        addEdge(adj, 3, 7);
        addEdge(adj, 4, 5);
        addEdge(adj, 4, 6);
        addEdge(adj, 4, 7);
        addEdge(adj, 5, 6);
        addEdge(adj, 6, 7);
        int source = 0, dest = 7;
        printShortestDistance(adj, source, dest, v);
    }
 
    // function to form edge between two vertices
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
 
    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static void printShortestDistance(
                     ArrayList<ArrayList<Integer>> adj,
                             int s, int dest, int v)
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];
 
        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                                         "are not connected");
            return;
        }
 
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
 
        // Print distance
        System.out.println("Shortest path length is: " + dist[dest]);
 
        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }
 
    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                                  int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];
 
        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
 
                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}

