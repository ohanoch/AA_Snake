/**
 * Created by or on 7/29/17.
 */
import za.ac.wits.snake.DevelopmentAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MyAgent extends DevelopmentAgent {

    public static void main(String args[]) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            int gridWidth = Integer.parseInt(temp[1]);
            int gridHeight = Integer.parseInt(temp[2]);
            Map map= new Map(gridWidth,gridHeight);
            while (true) {
                int move;
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                //initializing apples
                Coordinates apple1 = new Coordinates(Integer.parseInt(line.split(",")[0]),Integer.parseInt(line.split(",")[1]));
                Map.grid[apple1.getX()][apple1.getX()]='A';
                line = br.readLine();
                Coordinates apple2 = new Coordinates(Integer.parseInt(line.split(",")[0]),Integer.parseInt(line.split(",")[1]));
                if (apple2.getX()!=-1){
                    Map.grid[apple1.getX()][apple1.getX()]='I';
                }

                //declaring snakes
                int mySnakeNum = Integer.parseInt(br.readLine());
                String[] enemySnakeData = new String[nSnakes-1];
                String mySnakeData="";
                Snake mySnake;
                Snake[] enemySnakes = new Snake[nSnakes-1];

                //initialize snake objects
                for (int i = 0; i < nSnakes; i++) {
                    if (i == mySnakeNum) {
                        mySnakeData = br.readLine();
                    } else {
                        if (i<mySnakeNum){
                            enemySnakeData[i] = br.readLine();
                            enemySnakes[i]= new Snake(i, enemySnakeData[i].split(" "));
                        }else
                            enemySnakeData[i-1] = br.readLine();
                            enemySnakes[i-1]= new Snake(i, enemySnakeData[i].split(" "));
                    }
                }
                mySnake = new Snake(mySnakeNum, mySnakeData.split(" "));


                int[] myBFSApple2 = BFS(mySnake.getHead(),apple2);
                //if closest to super apple - go for it
                if (myBFSApple2[1]<BFS(enemySnakes[0].getHead(),apple2)[1] &&
                        myBFSApple2[1]<BFS(enemySnakes[1].getHead(),apple2)[1] &&
                        myBFSApple2[1]<BFS(enemySnakes[2].getHead(),apple2)[1]){
                    move = myBFSApple2[0];
                } else {
                    //if closest to normal apple - go for it
                    int[] myBFSApple1 = BFS(mySnake.getHead(),apple2);
                    if (myBFSApple1[1]<BFS(enemySnakes[0].getHead(),apple2)[1] &&
                            myBFSApple1[1]<BFS(enemySnakes[1].getHead(),apple2)[1] &&
                            myBFSApple1[1]<BFS(enemySnakes[2].getHead(),apple2)[1]){
                        move = myBFSApple1[0];
                    } else {
                                if (mySnake.getLength()+5 < mySnake.getMaxLength()){
                                    //kamikaza first place if you aren't close to your max
                                } else {
                                    // if you are close to your max go towards the mid-ring
                                }
                    }
                }

                //finished reading, calculate move:
                System.out.println("log calculating...");
                System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    does BFS and returns int array in the shape of [direction to move, amount of moves to target]
//    directions: 0- up, 1 - down, 2 - left, 3 - right
    public static int[] BFS(Coordinates start, Coordinates goal){
        LinkedList<Coordinates> visited = new LinkedList<>();
        Queue<BFSCell> q = new LinkedList<>();

        visited.add(start);
        BFSCell curr = new BFSCell(start,null);
        q.add(curr);

        while (q.isEmpty() == false) {
            curr = q.remove();
            if (curr.getPlace().equals(goal)) {
                break;
            }
//          Up
            Coordinates next = new Coordinates(curr.getPlace().getX(), curr.getPlace().getY() + 1);
            if (!visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
            }
//          Down
            next = new Coordinates(curr.getPlace().getX(), curr.getPlace().getY() - 1);
            if (!visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
            }
//          Left
            next = new Coordinates(curr.getPlace().getX() - 1, curr.getPlace().getY());
            if (!visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
            }
//          Right
            next = new Coordinates(curr.getPlace().getX() + 1, curr.getPlace().getY());
            if (!visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
            }
        }

        int moveCount=0;
        while (!curr.getParent().getPlace().equals(start)){
            curr=curr.getParent();
            moveCount++;
        }
        moveCount++;

        int[] result = new int[2];
        result[1]=moveCount;
//        Move Right
        if (curr.getPlace().getX()>start.getX()){
            result[0]=3;
            return result;
//        Move Left
        } else if (curr.getPlace().getX()<start.getX()){
            result[0]=2;
            return result;
//        Move Up
        } else if (curr.getPlace().getY()>start.getY()){
            result[0]=0;
            return result;
//        Move Down
        } else {
            result[0]=1;
            return result;
        }
    }
}