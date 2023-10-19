package entity.quiz;

public record QuizDisplay (String[] questions, String[][] choices){
    public QuizDisplay {
        if (questions.length != choices.length)
        {
            throw new IllegalArgumentException("The number of questions and choices does not match");
        }
    }
}
