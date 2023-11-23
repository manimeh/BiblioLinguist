package entity.quiz;

public record ActiveQuizDisplay(String[] questions, String[][] choices){
    public ActiveQuizDisplay {
        if (questions.length != choices.length)
        {
            throw new IllegalArgumentException("The length of questions and choices does not match");
        }
    }
}
