package app;

import data_access.APIAccessors.NovaAIRetriever;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuiz;
import entity.quiz.ActiveQuizDisplay;
import entity.quiz.factory.MCQuizFactory;
import entity.reading.Reading;
import entity.reading.ReadingDisplay;
import entity.reading.ReadingType;

import java.util.Optional;

public class Demo
{
    public static void main(String[] args) {
        Optional<? extends Reading> newsOptional = ReadingType.NEWS.getDefaultReadingFactory().create(Language.FRENCH, DifficultyLevel.INTERMEDIATE);
        Reading news;

        if (newsOptional.isPresent())
        {
            news = newsOptional.get();

            ReadingDisplay newsDisplay = news.display();
            System.out.println("Title: " + newsDisplay.title() + "\nAuthor: " + newsDisplay.author() + "\n"
                    + "\n" + newsDisplay.text());

            System.out.println("\n");

            MCQuizFactory quizFactory = new MCQuizFactory(new NovaAIRetriever());
            MCQuiz quiz = quizFactory.create(news, DifficultyLevel.BEGINNER, Language.ENGLISH, 5);
            ActiveQuizDisplay quizDisplay = quiz.activeDisplay();

            for(int i = 0; i < quizDisplay.questions().length; i++)
            {
                System.out.println("Question " + (i+1) + ": " + quizDisplay.questions()[i]);

                for(String choice: quizDisplay.choices()[i])
                {
                    System.out.println(choice);
                }
            }
        }
        else
        {
            System.out.println("News of that difficult was not found!");
        }
    }
}
