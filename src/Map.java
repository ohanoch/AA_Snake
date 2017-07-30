/**
 * Created by or on 7/29/17.
 */
public class Map {
    private int gridWidth;
    private int gridHeight;
    public static char[][] grid;

    public Map(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.grid = new char[gridWidth][gridHeight];
    }
}
