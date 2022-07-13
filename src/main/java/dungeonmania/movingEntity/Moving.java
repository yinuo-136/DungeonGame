package dungeonmania.movingEntity;

import java.util.List;

import dungeonmania.player.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public interface Moving {
    public void attack(Player player);
    public Position getPos();
    public void setPos(Position pos);
    public String getType();
    public double getHealth();
    public int getDamage();
    public void move();
    public List<String> getEntitiesStringByPosition(Position pos);
}
