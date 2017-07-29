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
    private static int allMaxLength;
    private static int bestSnake;

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
            setBody(Arrays.copyOfRange(snakeData, 3, snakeData.length));
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
    public void setBody (String[] body){
        for (int i=0; i<body.length; i++){
            Coordinates part = new Coordinates(Integer.parseInt(body[i].split(",")[0]),Integer.parseInt(body[i].split(",")[1]));
            (this.body).addLast(part);
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



}
