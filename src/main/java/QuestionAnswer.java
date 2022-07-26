import com.opencsv.bean.CsvBindByPosition;

public class QuestionAnswer {

    @CsvBindByPosition(position = 0)
    private String question;
    @CsvBindByPosition(position = 1)
    private int answer;

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }
}
