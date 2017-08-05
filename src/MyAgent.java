/**
 * Created by or on 7/29/17.
 */
import za.ac.wits.snake.DevelopmentAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//                System.err.println("--------------------------------------------started while loop--------------------------------");
                int move = -1;
                String line = br.readLine();
                System.err.println(line);

                long time = System.currentTimeMillis();

                if (line.contains("Game Over")) {
//                    System.err.println("--------------------------------------------Game Over--------------------------------");
                    break;
                }

                //initializing apples
                Coordinates superApple = new Coordinates(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]));
                if (superApple.getX()!=-1){
                    Map.grid[superApple.getX()][superApple.getY()]='S';
                }
                line = br.readLine();
                System.err.println(line);
                Coordinates normalApple = new Coordinates(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]));
                Map.grid[normalApple.getX()][normalApple.getY()]='A';


                //declaring snakes
                int mySnakeNum = Integer.parseInt(br.readLine());
                System.err.println(mySnakeNum);
                String[] enemySnakeData = new String[nSnakes-1];
                String mySnakeData="";
                Snake[] enemySnakes = new Snake[nSnakes-1];
                int nLiveEnemySnakes=0;
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
                            if (enemySnakes[i].getState().equals("dead")){
                                continue;
                            }
                            enemySnakes[i].setSafeZone();
                            nLiveEnemySnakes++;
                        }else {
                            enemySnakeData[i - 1] = br.readLine();
                            System.err.println(enemySnakeData[i-1]);
                            enemySnakes[i - 1] = new Snake(i, enemySnakeData[i - 1].split(" "));
                            if (enemySnakes[i-1].getState().equals("dead")){
                                continue;
                            }
                            enemySnakes[i-1].setSafeZone();
                            nLiveEnemySnakes++;
                        }
                    }
                }
                Snake mySnake = new Snake(mySnakeNum, mySnakeData.split(" "));
                if (mySnake.getState().equals("dead")){
                    continue;
                }

                Snake[] liveEnemySnakes = new Snake[nLiveEnemySnakes];
                for (int i = 0, deadCount=0; i < nSnakes-1; i++) {
                    if (enemySnakes[i].getState().equals("dead")){
                        deadCount++;
                        continue;
                    }
                    liveEnemySnakes[i-deadCount]=enemySnakes[i];
                }
                map.printMap();
                System.err.println("----------------------------------------------------------------------------");

                //finished reading, calculate move:
                //System.out.println("log calculating...");
//                int myDistanceFromSuperApple = 999;
//                if (superApple.getX()!=-1){
//                    myDistanceFromSuperApple = mySnake.getHead().distance(superApple);
//                }
//                int myDistanceFromNormalApple = mySnake.getHead().distance(normalApple);
//                int enemyDistanceFromSuperApple=999;
//                int enemyDistanceFromNormalApple=999;
//                for (int i = 0; i<nLiveEnemySnakes; i++){
//                    if (superApple.getX()!=-1 && liveEnemySnakes[i].getHead().distance(superApple)<enemyDistanceFromSuperApple){
//                        enemyDistanceFromSuperApple=liveEnemySnakes[i].getHead().distance(superApple);
//                    }
//                    if (liveEnemySnakes[i].getHead().distance(normalApple)<enemyDistanceFromNormalApple){
//                        enemyDistanceFromNormalApple=liveEnemySnakes[i].getHead().distance(normalApple);
//                    }
//                }
////                System.err.println(myDistanceFromNormalApple + "   " + enemyDistanceFromNormalApple + "   " + myDistanceFromSuperApple + "   " + enemyDistanceFromSuperApple);
//
//                if (myDistanceFromSuperApple < enemyDistanceFromSuperApple){
//                    move = mySnake.move(superApple);
//                } else if (myDistanceFromNormalApple < enemyDistanceFromNormalApple){
//                    move = mySnake.move(normalApple);
//                } else {
////                    kamikaza
//                    move = mySnake.move(new Coordinates(25,25));
//                }
                move = mySnake.move(new Coordinates(25,25));
//                move = new Random().nextInt(4);
                System.out.println(move);
                map.resetGrid();

                System.err.println(System.currentTimeMillis()-time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


















/*import za.ac.wits.snake.DevelopmentAgent;

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
//                System.err.println("--------------------------------------------started while loop--------------------------------");
                int move = -1;
//                System.err.println("asdsadasdasda");
                String line = br.readLine();
//                System.err.println("qweqwewqeqwewqe");
//                System.err.println(line);
                if (line.contains("Game Over")) {
//                    System.err.println("--------------------------------------------Game Over--------------------------------");
                    break;
                }

                //initializing apples
                Coordinates superApple = new Coordinates(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]));
                if (superApple.getX()!=-1){
                    Map.grid[superApple.getX()][superApple.getX()]='S';
                }
                line = br.readLine();
//                System.err.println(line);
                Coordinates normalApple = new Coordinates(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]));
                Map.grid[normalApple.getX()][normalApple.getX()]='A';


                //declaring snakes
                int mySnakeNum = Integer.parseInt(br.readLine());
//                System.err.println(mySnakeNum);
                String[] enemySnakeData = new String[nSnakes-1];
                String mySnakeData="";
                Snake mySnake;
                Snake[] enemySnakes = new Snake[nSnakes-1];
                //initialize snake objects
                for (int i = 0; i < nSnakes; i++) {
                    if (i == mySnakeNum) {
                        mySnakeData = br.readLine();
//                        System.err.println(mySnakeData);
                    } else {
                        if (i<mySnakeNum){
                            enemySnakeData[i] = br.readLine();
//                            System.err.println(enemySnakeData[i]);
                            enemySnakes[i]= new Snake(i, enemySnakeData[i].split(" "));
                        }else
                            enemySnakeData[i-1] = br.readLine();
//                            System.err.println(enemySnakeData[i-1]);
                            enemySnakes[i-1]= new Snake(i, enemySnakeData[i-1].split(" "));
                    }
                }
                mySnake = new Snake(mySnakeNum, mySnakeData.split(" "));

                boolean goingToSuperApple = false;
                if (superApple.getX()!=-1){
//                    System.err.println("before bfssssssssssssssssssssssssssssssssssssssssssssssssssss");
                    int[] myBFSSuperApple = BFS(mySnake.getHead(),superApple);
//                    System.err.println("after bfssssssssssssssssssssssssssssssssssssssssssssssssssss");
                    //if closest to super apple - go for it
                    if (myBFSSuperApple[1]<BFS(enemySnakes[0].getHead(),superApple)[1] &&
                            myBFSSuperApple[1]<BFS(enemySnakes[1].getHead(),superApple)[1] &&
                            myBFSSuperApple[1]<BFS(enemySnakes[2].getHead(),superApple)[1]){
//                        System.err.print("closest to SUPER apple 1111111111111111111111111111111111111111111111");
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

//                        System.err.print("closest to NORMAL apple 222222222222222222222222222222222222222222");
                        move = myBFSNormalApple[0];
                    } else {
                                if (mySnake.getLength()+5 < mySnake.getMaxLength()){
                                    //kamikaza first place if you aren't close to your max
//                                    System.err.print("kamikaza 3333333333333333333333333333333333333");
                                } else {
                                    // if you are close to your max go towards the mid-ring 13-37
//                                    System.err.print("mid circle 44444444444444444444444444444444444444444444");

                                }
                    }
                }

                //finished reading, calculate move:
//                System.err.println("------------------------------------------------finished turn------------------------------------");
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

        while (q.isEmpty() == false) {
            curr = q.remove();
//            System.err.println(curr.getPlace().getX() + "   " + curr.getPlace().getY() + "   " + goal.getX() + "   " + goal.getY() + "   " + q.size() + "   " + visited.size());
            if (curr.getPlace().equals(goal)) {
                break;
            }
//          Up
            Coordinates next = new Coordinates(curr.getPlace().getX(), curr.getPlace().getY() + 1);
            boolean isVisited = listContains(visited,next);
            if (next.getY() < Map.gridHeight && !isVisited && Map.grid[next.getX()][next.getY()] == ' ') {
                q.add(new BFSCell(next,curr));
                visited.add(new Coordinates(next.getX(),next.getY()));
            }
//          Down
            next.setY( curr.getPlace().getY() - 1);
            isVisited = listContains(visited,next);
            if (next.getY() >= 0 && !isVisited && Map.grid[next.getX()][next.getY()] == ' ') {
                q.add(new BFSCell(next,curr));
                visited.add(new Coordinates(next.getX(),next.getY()));
            }
//          Left
            next.setX(curr.getPlace().getX() - 1);
            next.setY(curr.getPlace().getY());
            isVisited = listContains(visited,next);
            if (next.getX()>=0 && !isVisited && Map.grid[next.getX()][next.getY()] == ' ') {
                q.add(new BFSCell(next,curr));
                visited.add(new Coordinates(next.getX(),next.getY()));
            }
//          Right
            next.setX(curr.getPlace().getX() + 1);
            isVisited = listContains(visited,next);
            if (next.getX()< Map.gridWidth && !isVisited && Map.grid[next.getX()][next.getY()] == ' ') {
                q.add(new BFSCell(next,curr));
                visited.add(new Coordinates(next.getX(),next.getY()));
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

    public static Queue<BFSCell> printQueue(Queue<BFSCell> q){
        System.err.println ("q size is " +q.size());
        Queue<BFSCell> p = new LinkedList<>();
        int num = q.size();
        for(int i=0; i< num; i++){
            BFSCell t = q.remove();
            System.err.println ("q size is " +q.size() + " q place "+ i + " is x: " + t.getPlace().getX() + " y: " + t.getPlace().getY() + " q size is " + q.size());
            p.add(t);
        }
        return p;
    }

    public static void printList(LinkedList<Coordinates> l){
        for (int i=0; i<l.size(); i++){
            System.err.println(l.get(i).getX() + "   " + l.get(i).getY());
        }
    }
    public static boolean listContains(LinkedList<Coordinates> l, Coordinates c){
        for (int i=0; i<l.size(); i++){
            if(l.get(i).equals(c)){
                return true;
            }
        }
        return false;
    }
}
*/