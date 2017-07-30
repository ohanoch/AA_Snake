/**
 * Created by or on 7/29/17.
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public void setL(int x){ this.x = x; }
    public void setR(int y){ this.y = y; }

    public boolean equals(Coordinates coord2){
        if (this.x == coord2.getX() && this.y == coord2.getY()){
            return true;
        }
        return false;
    }

    public int distance(Coordinates coord2){
        return Math.abs(this.x - coord2.getX())+Math.abs(this.y + coord2.getY());
    }
}
