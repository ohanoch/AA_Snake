/**
 * Created by or on 7/29/17.
 */
public class Map {
    public static int gridWidth;
    public static int gridHeight;
    public static char[][] grid;

    public Map(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.grid = new char[gridWidth][gridHeight];
    }
}
