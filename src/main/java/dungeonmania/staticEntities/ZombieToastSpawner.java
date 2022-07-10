package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity{
    private String id;
    private Position pos;
    private static int Spawntime;
    private int RemainingTimeToSpawn;

    public ZombieToastSpawner(Position p, String id) {
        this.id = id;
        this.pos = p;  
        RemainingTimeToSpawn = Spawntime;
    }

    public Position getPos() {
        return pos;
    }

    public static int getSpawntime() {
        return Spawntime;
    }

    public static void setSpawntime(int spawntime) {
        Spawntime = spawntime;
    }

    public void setRemainingTimeToSpawn(int remainingTimeToSpawn) {
        RemainingTimeToSpawn = remainingTimeToSpawn;
    }

    public String getId() {
        return id;
    }

    //TODO: Spawn a zombie toast.
    
}
