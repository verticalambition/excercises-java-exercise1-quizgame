import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class QuizGame {
    public static void main(String[] args) {
        int questionCount;
        List <QuestionAnswer> problems;
        try {
            CsvToBeanBuilder reader = new CsvToBeanBuilder(new FileReader("D:\\Development\\Practice\\Java\\exercises\\exercise1quizgame\\problems.csv"));
            problems = reader.withType(QuestionAnswer.class).build().parse();
            questionCount = problems.size();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        final BlockingQueue<Integer> theScore = new ArrayBlockingQueue<Integer>(1);
        final ArrayList<Integer> stopSign = new ArrayList();
        final PlayGame theGame = new PlayGame(theScore, problems.iterator(), stopSign);
        System.out.println("Are you ready to beeeeegggiiiinnnn!?");
        Scanner userInput = new Scanner(System.in);
        String readyOrNot = userInput.nextLine();
        Thread timeThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(30000);
                    System.out.println("Time's UP!");
                    theGame.interrupt();
                } catch (InterruptedException e) {
                }
            }
        };

        if(readyOrNot.toUpperCase().startsWith(("Y"))) {

            timeThread.start();

            theGame.start();
        }
        try {
            int actualScore = theScore.poll(50000000, TimeUnit.MILLISECONDS);
            System.out.printf("You answered %d out of %d questions correctly", actualScore, questionCount);
            timeThread.interrupt();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
