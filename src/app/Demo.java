package app;

import data_access.APIAccessors.NovaAIMCQuizRetriever;
import data_access.APIAccessors.WorldNewsRetriever;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuiz;
import entity.quiz.QuizDisplay;
import entity.quiz.factory.MCQuizFactory;
import entity.reading.News;
import entity.reading.Reading;
import entity.reading.ReadingDisplay;
import entity.reading.factory.NewsReadingFactory;

import java.util.Optional;

public class Demo
{
    public static void main(String[] args) {
        NewsReadingFactory newsFactory = new NewsReadingFactory(new WorldNewsRetriever());
        Optional<Reading> newsOptional = newsFactory.create(Language.FRENCH, DifficultyLevel.BEGINNER);
        News news = null;

        if (newsOptional.isPresent())
        {
            news = (News) newsOptional.get();

            ReadingDisplay newsDisplay = news.display();
            System.out.println("Title: " + newsDisplay.title() + "\nAuthor: " + newsDisplay.author() + "\n"
                    + "\n" + newsDisplay.text());

            System.out.println("\n");

            MCQuizFactory quizFactory = new MCQuizFactory(new NovaAIMCQuizRetriever());
            MCQuiz quiz = (MCQuiz) quizFactory.create(news, DifficultyLevel.BEGINNER, Language.ENGLISH, 5);
            QuizDisplay quizDisplay = quiz.display();

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
