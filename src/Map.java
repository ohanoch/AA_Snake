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
        this.resetGrid();
    }

    public void resetGrid(){
        for (int i=0; i<gridWidth; i++){
            for (int j=0; j<gridHeight; j++){
                this.grid[i][j]=' ';
            }
        }
    }

    public void printMap(){
        for(int i=-1; i<gridHeight; i++){
            for (int j=-1; j<gridWidth; j++){
                if (i==-1){
                     if (j==-1){
                         System.err.print(" ");
                     }else{

                         System.err.print(j%10);
                     }
                }else{
                    if (j==-1){
                        System.err.print(i%10);
                    }else{
                        System.err.print(grid[i][j]);
                    }
                }
            }
            System.err.println("|");
        }
    }
}
