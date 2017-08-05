import java.util.Arrays;
import java.util.LinkedList;

public class Snake {
    private int snakeNumber;
    private LinkedList<Coordinates> body = new LinkedList<>();
    private String state;
    private int invisibiltyTime;
    private int length;
    private int kills;
    private int maxLength;
    public static int allMaxLength;
    public static int bestSnake;

    public Snake (int snakeNumber, String[] snakeData){
        this.snakeNumber = snakeNumber;
        this.state = snakeData[0];
        this.length = Integer.parseInt(snakeData[1]);
        this.kills = Integer.parseInt(snakeData[2]);
        if (state.equals("invisible")){
            this.invisibiltyTime = Integer.parseInt(snakeData[3]);
            setBody(Arrays.copyOfRange(snakeData, 4, snakeData.length));
        } else {
            this.invisibiltyTime = 0;
            if (state.equals("alive")){
                setBody(Arrays.copyOfRange(snakeData, 3, snakeData.length));
            }
        }
        if (length > maxLength){
            maxLength = length;
        }
        if(maxLength > allMaxLength){
            allMaxLength = maxLength;
            bestSnake = snakeNumber;
        }
    }


    public void setSnakeNumber(int snakeNumber){this.snakeNumber=snakeNumber;}
    Coordinates lastPart = new Coordinates(-1,-1);
    public void setBody (String[] body){
        for (int i=0; i<body.length; i++){
            Coordinates currPart = new Coordinates(Integer.parseInt(body[i].split(",")[0]),Integer.parseInt(body[i].split(",")[1]));
            (this.body).addLast(currPart);
            if (i==0){
                Map.grid[currPart.getX()][currPart.getY()]='H';
            } else {
                Map.grid[currPart.getX()][currPart.getY()]='B';
                if (lastPart.getX() == currPart.getX()){
                    if (lastPart.getY() > currPart.getY()){
                        for (int j=1; j< lastPart.getY()-currPart.getY(); j++){
                            Map.grid[currPart.getX()][currPart.getY()+j]='B';
                        }
                    }else{
                        for (int j=1; j< currPart.getY() - lastPart.getY(); j++){
                            Map.grid[currPart.getX()][lastPart.getY()+j]='B';
                        }
                    }
                } else {
                    if (lastPart.getX() > currPart.getX()){
                        for (int j=1; j< lastPart.getX()-currPart.getX(); j++){
                            Map.grid[currPart.getX()+j][currPart.getY()]='B';
                        }
                    }else{
                        for (int j=1; j< currPart.getX() - lastPart.getX(); j++){
                            Map.grid[lastPart.getX()+j][currPart.getY()]='B';
                        }
                    }
                }
            }
            lastPart = currPart;
        }
    }
    public void setState(String state){this.state=state;}
    public void setInvisibiltyTime(int invisibiltyTime){this.invisibiltyTime=invisibiltyTime;}
    public void setLength(int length){this.length=length;}
    public void setKills(int kills){this.kills=kills;}
    public void setMaxLength(int maxLength){this.maxLength=maxLength;}
    public void setAllMaxLength(int snakeLength){allMaxLength=snakeLength;}
    public void setBestSnake(int snakeNum){bestSnake=snakeNum;}

    public int getSnakeNumber(){return snakeNumber;}
    public LinkedList<Coordinates> getBody(){return body;}
    public String getState(){return state;}
    public int getInvisibilityTime(){return invisibiltyTime;}
    public int getLength(){return length;}
    public int getKills(){return kills;}
    public int getMaxLength(){return maxLength;}
    public int getAllMaxLength(){return allMaxLength;}
    public int getBestSnake(){return bestSnake;}

    public Coordinates getHead(){return this.body.get(0);}
    public String getDirection(){
        if (this.getHead().getX() == body.get(1).getX()){
            if (this.getHead().getY() > body.get(1).getY()){
                return "up";
            } else {
                return "down";
            }
        } else {
            if (this.getHead().getX() > body.get(1).getX()){
                return "right";
            } else {
                return "left";
            }
        }
    }
    public void setSafeZone(){
        if (this.getDirection().equals("up")){
            if(this.getHead().getY()+1 < Map.gridHeight){
                Map.grid[this.getHead().getX()][this.getHead().getY()+1]='*';
            }
            if(this.getHead().getX()+1 < Map.gridWidth) {
                Map.grid[this.getHead().getX() + 1][this.getHead().getY()] = '*';
            }
            if(this.getHead().getX()-1 >= 0) {
                Map.grid[this.getHead().getX() - 1][this.getHead().getY()] = '*';
            }
        }
        if (this.getDirection().equals("down")){
            if(this.getHead().getY()-1 >= 0){
                Map.grid[this.getHead().getX()][this.getHead().getY()-1]='*';
            }
            if(this.getHead().getX()+1 < Map.gridWidth) {
                Map.grid[this.getHead().getX() + 1][this.getHead().getY()] = '*';
            }
            if(this.getHead().getX()-1 >= 0) {
                Map.grid[this.getHead().getX() - 1][this.getHead().getY()] = '*';
            }
        }
        if (this.getDirection().equals("left")){
            if(this.getHead().getY()+1 < Map.gridHeight){
                Map.grid[this.getHead().getX()][this.getHead().getY()+1]='*';
            }
            if(this.getHead().getY()-1 >= 0){
                Map.grid[this.getHead().getX()][this.getHead().getY()-1]='*';
            }
            if(this.getHead().getX()-1 >= 0) {
                Map.grid[this.getHead().getX() - 1][this.getHead().getY()] = '*';
            }
        }
        if (this.getDirection().equals("right")){
            if(this.getHead().getY()+1 < Map.gridHeight){
                Map.grid[this.getHead().getX()][this.getHead().getY()+1]='*';
            }
            if(this.getHead().getY()-1 >= 0){
                Map.grid[this.getHead().getX()][this.getHead().getY()-1]='*';
            }
            if(this.getHead().getX()+1 < Map.gridWidth) {
                Map.grid[this.getHead().getX() + 1][this.getHead().getY()] = '*';
            }
        }
    }
    public int move(Coordinates goal){

        int up = 0;
        int down = 1;
        int left = 2;
        int right = 3;
        System.err.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//        Up
        if (this.getHead().getY() < goal.getY() &&
                this.getHead().getY()+1<Map.gridHeight &&
                (Map.grid[this.getHead().getX()][this.getHead().getY()+1]==' ' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()+1]=='A' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()+1]=='S')){
            return 2;
//            return 0;
        }
//        Down
        if (this.getHead().getY() > goal.getY() &&
                this.getHead().getY()-1>=0 &&
                (Map.grid[this.getHead().getX()][this.getHead().getY()-1]==' ' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()-1]=='A' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()-1]=='S')){
            return 3;
//            return 1;
        }
//        Left
        if (this.getHead().getX() > goal.getX() &&
                this.getHead().getX()-1>=0 &&
                (Map.grid[this.getHead().getX()-1][this.getHead().getY()]==' ' ||
                        Map.grid[this.getHead().getX()-1][this.getHead().getY()]=='A' ||
                        Map.grid[this.getHead().getX()-1][this.getHead().getY()]=='S')){
            return 1;
//            return 2;
        }
//        Right
        if (this.getHead().getX() <= goal.getX() &&
                this.getHead().getX()+1< Map.gridWidth &&
                (Map.grid[this.getHead().getX()+1][this.getHead().getY()]==' ' ||
                        Map.grid[this.getHead().getX()+1][this.getHead().getY()]=='A' ||
                        Map.grid[this.getHead().getX()+1][this.getHead().getY()]=='S')){
            return 0;
//            return 3;
        }
        System.err.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//        Can't go towards apple
        //        Up
        if (this.getHead().getY()+1<Map.gridHeight &&
                (Map.grid[this.getHead().getX()][this.getHead().getY()+1]==' ' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()+1]=='A' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()+1]=='S')){
            return 2;
//            return 0;
        }
//        Down
        if (this.getHead().getY()-1>=0 &&
                (Map.grid[this.getHead().getX()][this.getHead().getY()-1]==' ' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()-1]=='A' ||
                        Map.grid[this.getHead().getX()][this.getHead().getY()-1]=='S')){
            return 3;
//            return 1;
        }
//        Left
        if (this.getHead().getX()-1>=0 &&
                (Map.grid[this.getHead().getX()-1][this.getHead().getY()]==' ' ||
                        Map.grid[this.getHead().getX()-1][this.getHead().getY()]=='A' ||
                        Map.grid[this.getHead().getX()-1][this.getHead().getY()]=='S')){
            return 1;
//            return 2;
        }
//        Right
        if (this.getHead().getX()+1<Map.gridWidth &&
                (Map.grid[this.getHead().getX()+1][this.getHead().getY()]==' ' ||
                        Map.grid[this.getHead().getX()+1][this.getHead().getY()]=='A' ||
                        Map.grid[this.getHead().getX()+1][this.getHead().getY()]=='S')){
            return 0;
//            return 3;
        }

        return down;
    }

}
