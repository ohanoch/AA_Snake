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
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }
                String apple1 = line;
                String apple2 = br.readLine();
                int mySnakeNum = Integer.parseInt(br.readLine());
                String[] enemySnakeData = new String[nSnakes-1];
                String mySnakeData;
                Snake mySnake;
                Snake[] enemySnakes = new Snake[nSnakes-1];
                for (int i = 0; i < nSnakes; i++) {
                    if (i == mySnakeNum) {
                        mySnakeData = br.readLine();
                        mySnake = new Snake(mySnakeNum, mySnakeData.split(" "));
                    } else {
                        if (i<mySnakeNum){
                            enemySnakeData[i] = br.readLine();
                            enemySnakes[i]= new Snake(i, enemySnakeData[i].split(" "));
                        }else
                            enemySnakeData[i-1] = br.readLine();
                            enemySnakes[i-1]= new Snake(i, enemySnakeData[i].split(" "));
                    }
                    //do stuff with snakes
                }
                //finished reading, calculate move:
                System.out.println("log calculating...");
                int move = new Random().nextInt(4);
                System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}