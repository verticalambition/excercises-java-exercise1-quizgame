import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

        int correctAnswers = BeginQuiz(problems);
        System.out.printf("You answered %d out of %d questions correctly", correctAnswers, questionCount);
    }

    public static int BeginQuiz(List<QuestionAnswer> problems) {
        int correctCount = 0;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Are you ready to beeeeegggiiiinnnn!?");
        String readyOrNot = userInput.nextLine();
        if(readyOrNot.toUpperCase().startsWith(("Y"))) {
            for (QuestionAnswer problem : problems) {
                System.out.printf("What is %s\n", problem.getQuestion());
                int response = userInput.nextInt();
                if (response == problem.getAnswer()) {
                    correctCount++;
                } else {
                    System.out.printf("Incorrect, answer was %d\n", problem.getAnswer());
                }
            }
            return correctCount;
        }
        return 0;
    }
}
