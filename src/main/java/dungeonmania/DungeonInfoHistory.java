package dungeonmania;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DungeonInfoHistory implements Serializable{
    private List<DungeonInfo> dungeonInfoHistory = new ArrayList<>();

    public void addDungeonInfo(DungeonInfo dungeonInfo) {
        dungeonInfoHistory.add(dungeonInfo);
    }

    public void getDungeonInfo(int index) {
        dungeonInfoHistory.get(index);
    }

    public int getDungeonInfoSize() {
        return dungeonInfoHistory.size();
    }
}
