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
            System.err.println(initString);

            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            int gridWidth = Integer.parseInt(temp[1]);
            int gridHeight = Integer.parseInt(temp[2]);
            Map map= new Map(gridWidth,gridHeight);
            while (true) {
                System.err.println("--------------------------------------------started while loop--------------------------------");
                int move = -1;
                String line = br.readLine();
                System.err.println(line);
                if (line.contains("Game Over")) {
                    break;
                }

                //initializing apples
                Coordinates superApple = new Coordinates(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]));
                if (superApple.getX()!=-1){
                    Map.grid[superApple.getX()][superApple.getX()]='S';
                }
                line = br.readLine();
                System.err.println(line);
                Coordinates normalApple = new Coordinates(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]));
                Map.grid[normalApple.getX()][normalApple.getX()]='A';


                //declaring snakes
                int mySnakeNum = Integer.parseInt(br.readLine());
                System.err.println(mySnakeNum);
                String[] enemySnakeData = new String[nSnakes-1];
                String mySnakeData="";
                Snake mySnake;
                Snake[] enemySnakes = new Snake[nSnakes-1];
                //initialize snake objects
                for (int i = 0; i < nSnakes; i++) {
                    if (i == mySnakeNum) {
                        mySnakeData = br.readLine();
                        System.err.println(mySnakeData);
                    } else {
                        if (i<mySnakeNum){
                            enemySnakeData[i] = br.readLine();
                            System.err.println(enemySnakeData[i]);
                            enemySnakes[i]= new Snake(i, enemySnakeData[i].split(" "));
                        }else
                            enemySnakeData[i-1] = br.readLine();
                            System.err.println(enemySnakeData[i-1]);
                            enemySnakes[i-1]= new Snake(i, enemySnakeData[i-1].split(" "));
                    }
                }
                mySnake = new Snake(mySnakeNum, mySnakeData.split(" "));

                boolean goingToSuperApple = false;
                if (superApple.getX()!=-1){
                    System.err.println("before bfssssssssssssssssssssssssssssssssssssssssssssssssssss");
                    int[] myBFSSuperApple = BFS(mySnake.getHead(),superApple);
                    System.err.println("after bfssssssssssssssssssssssssssssssssssssssssssssssssssss");
                    //if closest to super apple - go for it
                    if (myBFSSuperApple[1]<BFS(enemySnakes[0].getHead(),superApple)[1] &&
                            myBFSSuperApple[1]<BFS(enemySnakes[1].getHead(),superApple)[1] &&
                            myBFSSuperApple[1]<BFS(enemySnakes[2].getHead(),superApple)[1]){
                        System.err.print("closest to SUPER apple 1111111111111111111111111111111111111111111111");
                        goingToSuperApple = true;
                        move = myBFSSuperApple[0];
                    }
                }
                if (goingToSuperApple == false) {
                    //if closest to normal apple - go for it
                    int[] myBFSNormalApple = BFS(mySnake.getHead(),normalApple);
                    if (myBFSNormalApple[1]<BFS(enemySnakes[0].getHead(),normalApple)[1] &&
                            myBFSNormalApple[1]<BFS(enemySnakes[1].getHead(),normalApple)[1] &&
                            myBFSNormalApple[1]<BFS(enemySnakes[2].getHead(),normalApple)[1]){

                        System.err.print("closest to NORMAL apple 222222222222222222222222222222222222222222");
                        move = myBFSNormalApple[0];
                    } else {
                                if (mySnake.getLength()+5 < mySnake.getMaxLength()){
                                    //kamikaza first place if you aren't close to your max
                                    System.err.print("kamikaza 3333333333333333333333333333333333333");
                                } else {
                                    // if you are close to your max go towards the mid-ring 13-37
                                    System.err.print("mid circle 44444444444444444444444444444444444444444444");

                                }
                    }
                }

                //finished reading, calculate move:
                System.err.println("------------------------------------------------finished turn------------------------------------");
                System.out.println("log calculating...");
                System.out.println(move);
                System.err.println("--------------------------------------------finished while loop----------------------------------");
            }
        } catch (IOException e) {
            System.err.println("went to catch");
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

        int count = 0;
        while (q.isEmpty() == false) {
            if (count == 5){
                System.exit(0);
            }
            curr = q.remove();
            visited.add(curr.getPlace());
            System.err.println(curr.getPlace().getX() + "   " + curr.getPlace().getY() + "   " + goal.getX() + "   " + goal.getY() + "   " + q.size());
            if (curr.getPlace().equals(goal)) {
                break;
            }
//          Up
            Coordinates next = new Coordinates(curr.getPlace().getX(), curr.getPlace().getY() + 1);
            System.err.println("1115   " + next.getX() + "   " + next.getY());
            if (next.getY() < Map.gridHeight && !visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
                System.err.println("111   " + q.peek().getPlace().getX() + "   " + q.peek().getPlace().getY());
                System.err.println("111   " + temp.getPlace().getX() + "   " + temp.getPlace().getY());
            }
//          Down
            next.setY( curr.getPlace().getY() - 1);
            System.err.println("2225   " + next.getX() + "   " + next.getY());
            if (next.getY() >= 0 && !visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
                System.err.println("222   " + q.peek().getPlace().getX() + "   " + q.peek().getPlace().getY());
            }
//          Left
            next.setX(curr.getPlace().getX() - 1);
            next.setY(curr.getPlace().getY());
            System.err.println("3335   " + next.getX() + "   " + next.getY());
            if (next.getX()>=0 && !visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
                System.err.println("333   " + q.peek().getPlace().getX() + "   " + q.peek().getPlace().getY());
            }
//          Right
            next.setX(curr.getPlace().getX() + 1);
            System.err.println("4445   " + next.getX() + "   " + next.getY());
            if (next.getX()< Map.gridWidth && !visited.contains(next) && Map.grid[next.getX()][next.getY()] == '\u0000') {
                BFSCell temp = new BFSCell(next,curr);
                q.add(temp);
                System.err.println("444   " + q.peek().getPlace().getX() + "   " + q.peek().getPlace().getY()+ "   " + q.size());
            }
            count++;
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