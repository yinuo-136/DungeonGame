package dungeonmania;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class DungeonGenerator {
    Position start;
    Position end;
    int width;
    int height;

    private Boolean[][] DungeonArray;
    DungeonGenerator(Position start, Position end) {
        width = Math.abs(start.getX() - end.getX()) + 1;
        height = Math.abs(start.getY() - end.getY()) + 1;
        this.start = start;
        this.end = end;
        DungeonArray = new Boolean[width][height];
        Arrays.fill(DungeonArray, false);
    }

    public List<EntityResponse> generate(){
        DungeonArray[0][0] = true;
        List<Position> options = new ArrayList<>();
        options.add(new Position(0,2));
        options.add(new Position(2,0));
        while (options.size() != 0) {
            Random random = new Random();
            int removed_index = random.nextInt(options.size()) + 1;
            List<Position> neighbours = new ArrayList<>();
            Position next = options.get(removed_index);
            int removed_X = next.getX();
            int removed_Y = next.getY();
            // all cardinal neighbour of distance 2
            // above 2
            if (removed_Y - 2 >= 0 ) {
                if (DungeonArray[removed_Y - 2][removed_X] == true)
                    neighbours.add(new Position(removed_X, removed_Y - 2));
            }
            // right 2
            if (removed_X + 2 < width) {
                if (DungeonArray[removed_Y][removed_X + 2] == true)
                    neighbours.add(new Position(removed_X + 2, removed_Y));
            }
            // left 2
            if (removed_X - 2 >= 0) {
                if (DungeonArray[removed_Y][removed_X - 2] == true)
                    neighbours.add(new Position(removed_X - 2, removed_Y));
            }
            // below 2
            if (removed_Y + 2 < height) {
                if (DungeonArray[removed_Y - 2][removed_X] == true)
                    neighbours.add(new Position(removed_X, removed_Y - 2));
            }
            if (neighbours.size() != 0) {
                removed_index = random.nextInt(neighbours.size()) + 1;
                Position neighbour = neighbours.get(removed_index);
                DungeonArray[removed_Y][removed_X] = true;
                int x_moved = neighbour.getX() - removed_X;
                int y_moved = neighbour.getY() - removed_Y;
                if (x_moved == 0) {
                    if (y_moved < 0)
                        DungeonArray[removed_Y - 1][removed_X] = true;
                    else
                        DungeonArray[removed_Y + 1][removed_X] = true;
                }
                else {
                    if (x_moved < 0)
                        DungeonArray[removed_Y][removed_X - 1] = true;
                    else
                        DungeonArray[removed_Y][removed_X + 1] = true;
                }
                DungeonArray[neighbour.getY()][neighbour.getX()] = true;
                neighbours.remove(neighbour);
            }
            neighbours = new ArrayList<>();
            // all cardinal neighbour of distance 2
            // above 2
            if (removed_Y - 2 >= 0 ) {
                if (DungeonArray[removed_Y - 2][removed_X] == false)
                    neighbours.add(new Position(removed_X, removed_Y - 2));
            }
            // right 2
            if (removed_X + 2 < width) {
                if (DungeonArray[removed_Y][removed_X + 2] == false)
                    neighbours.add(new Position(removed_X + 2, removed_Y));
            }
            // left 2
            if (removed_X - 2 >= 0) {
                if (DungeonArray[removed_Y][removed_X - 2] == false)
                    neighbours.add(new Position(removed_X - 2, removed_Y));
            }
            // below 2
            if (removed_Y + 2 < height) {
                if (DungeonArray[removed_Y - 2][removed_X] == false)
                    neighbours.add(new Position(removed_X, removed_Y - 2));
            }
            options.addAll(neighbours);
            options.remove(next);
        }
        if (DungeonArray[end.getY()][end.getX()] == false)
            DungeonArray[end.getY()][end.getX()] = true;
        List<Position> neighbours = new ArrayList<>();
        neighbours.add(new Position(height - 1, width - 2));
        neighbours.add(new Position(height - 2, width - 1));
        Boolean has_empty_neighbour = false;
        for (Position neighbour : neighbours) {
            if(DungeonArray[neighbour.getY()][neighbour.getX()] == true)
                has_empty_neighbour = true;
        }
        if (!has_empty_neighbour) {
            Random random = new Random();
            Position neighbour_to_make_empty = neighbours.get(random.nextInt(neighbours.size()) + 1);
            DungeonArray[neighbour_to_make_empty.getY()][neighbour_to_make_empty.getX()] = true;
        }
        List<EntityResponse>  entities = new ArrayList<>();
        int id = 2;
        
        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                Position current_pos = new Position(start.getX() + (j - 1), start.getY() + (i - 1));
                if (i == 0 || i == height + 1 || j == 0 || j == width + 1){
                    entities.add(new EntityResponse( String.valueOf(id), "wall", current_pos, false));
                    id ++;
                }
                else {
                    if (DungeonArray[i - 1][j - 1] == false) {
                        entities.add(new EntityResponse( String.valueOf(id), "wall", current_pos, false));
                        id ++;
                    }
                }
            }
        }
        entities.add(new EntityResponse("0", "player", start, false));
        entities.add(new EntityResponse("0", "exit", end, false));
        return entities;
    }
}
