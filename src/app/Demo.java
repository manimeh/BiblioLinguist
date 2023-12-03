package app;

import data_access.api_accessors.ChatGPTRetriever;
import data_access.api_accessors.FactoryBuilders.ReadingFactoryProvider;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.ActiveQuizDisplay;
import entity.quiz.MCQuiz;
import entity.quiz.factory.MCQuizFactory;
import entity.reading.Reading;
import entity.reading.ReadingDisplay;
import entity.reading.ReadingType;

import java.util.Optional;

public class Demo
{
    public static void main(String[] args)
    {
        ReadingType readingType = ReadingType.AI_GENERATED_STORY;

        Optional<? extends Reading> readingOptional = new ReadingFactoryProvider().getReadingFactory(readingType).
                create(Language.FRENCH, DifficultyLevel.BEGINNER);
        Reading reading;

        if (readingOptional.isPresent())
        {
            reading = readingOptional.get();

            ReadingDisplay newsDisplay = reading.display();
            System.out.println("Title: " + newsDisplay.title() + "\nAuthor: " + newsDisplay.author() + "\n"
                    + "\n" + newsDisplay.text());

            System.out.println("\n");

            MCQuizFactory quizFactory = new MCQuizFactory(new ChatGPTRetriever());
            MCQuiz quiz = quizFactory.create(reading, DifficultyLevel.BEGINNER, Language.ENGLISH, 5);
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
            System.out.println("Reading of that difficulty was not found!");
        }
    }
}
