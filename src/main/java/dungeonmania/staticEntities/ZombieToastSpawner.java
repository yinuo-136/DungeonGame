package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class ZombieToastSpawner {
    private Position pos;
    private static int Spawntime;
    private int RemainingTimeToSpawn;

    public ZombieToastSpawner(int x, int y) {
        this.pos = new Position(x, y);  
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

    //TODO: Spawn a zombie toast.
    
}
