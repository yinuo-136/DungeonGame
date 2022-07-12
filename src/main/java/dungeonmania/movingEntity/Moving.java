package dungeonmania.movingEntity;

import dungeonmania.player.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public interface Moving {
    public void attack(Player player);
    public Position getPosition();
<<<<<<< HEAD
    public double getHealth();
=======
    public String getType();
    public double getHealth();
    public int getDamage();
>>>>>>> master
}
