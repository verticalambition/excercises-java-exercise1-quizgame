import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class PlayGame implements Runnable {
    private BlockingQueue<Integer> score;
    private Iterator<QuestionAnswer> problems;
    private List<Integer> stopSign;

    public PlayGame(BlockingQueue<Integer> score, Iterator<QuestionAnswer> problems, List<Integer> stopSign) {
        this.score = score;
        this.problems = problems;
        this.stopSign = stopSign;
    }

    public void run() {
        int correctCount = 0;
        Scanner userInput = new Scanner(System.in);
        while (stopSign.isEmpty()) {
            if (problems.hasNext()) {
                QuestionAnswer problem = problems.next();
                System.out.printf("What is %s\n", problem.getQuestion());
                int response = userInput.nextInt();
                if (response == problem.getAnswer()) {
                    correctCount++;
                } else {
                    System.out.printf("Incorrect, answer was %d\n", problem.getAnswer());
                }
            } else {
                break;
            }
        }
        score.add(correctCount);
    }
}

