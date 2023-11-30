package data_access.api_accessors;

import data_access.api_accessors.api_json_classes.AIStoryResponse;
import data_access.api_accessors.api_json_classes.ChatMessage;
import data_access.api_accessors.api_json_classes.ChatRequest;
import data_access.api_accessors.api_json_classes.ChatResponse;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuiz;
import entity.reading.AIGeneratedStory;
import entity.reading.Reading;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatGPTRetrieverInterface implements MCQuizRetrieverInterface, AIGeneratedStoryRetrieverInterface
{
    private static final String OPEN_AI_API_TOKEN = System.getenv("OPEN_AI_API_TOKEN");

    @Override
    public MCQuiz getQuizFromAPI(Reading reading, DifficultyLevel difficulty, Language language, Integer numOfQuestions)
    {
        String prompt = String.format("Give me %s multiple choice questions on '%s'. The question should be in %s." +
                "The question should be at %s level of difficulty. Return your answer entirely in the form of a JSON" +
                "object. The JSON object should have a key named 'questions' which is an array of questions. Each " +
                "quiz question should include the choices, the answer, and a brief explanation of why the answer" +
                "is correct. Don't include anything other than the JSON. The JSON properties of each question should be" +
                " 'query' (which is the question), 'choices, 'answer', and 'explanation'. The choices should not have " +
                "any ordinal value like A, B, C, D or a number like 1, 2, 3, 4. The answer should be the 0-indexed " +
                "number of the correct choice", numOfQuestions.toString(), reading.display().text(),
                difficulty.getName(), language.getName());

        ChatResponse chatAnswer = getAIAnswer(prompt);
        return gson.fromJson(chatAnswer.getChoices()[0].getMessage().getContent(), MCQuiz.class);
    }

    @Override
    public AIGeneratedStory retrieveReadingFromAPI(Language language)
    {
        return retrieveReadingFromAPI(language, DifficultyLevel.INTERMEDIATE);
    }

    @Override
    public AIGeneratedStory retrieveReadingFromAPI(Language language, DifficultyLevel difficulty)
    {
        return retrieveListOfStoriesFromAPI(language, difficulty, 1)[0];
    }

    @Override
    public AIGeneratedStory[] retrieveListOfStoriesFromAPI(Language language, DifficultyLevel difficulty, Integer numOfStories)
    {
        AIGeneratedStory.setAiBot("Chat_GPT 3.5");
        String prompt = String.format("Give me %s short stories written in %s. The stories should be at %s reading level. " +
                        "The word count of the stories should be roughly around %s words. The plot of the stories should " +
                        "be entertaining. Return your answer entirely in the form of a JSON object. The JSON object should " +
                        "have a key named 'stories' which is an array of stories. Each story should include a text and a " +
                        "title. Don't include anything other than the JSON. The JSON properties of each story should be " +
                        "'text' and 'title'", numOfStories.toString(), language.getName(), difficulty.getName(),
                Reading.IDEAL_WORD_COUNT);

        ChatResponse chatAnswer = getAIAnswer(prompt);
        return gson.fromJson(chatAnswer.getChoices()[0].getMessage().getContent(), AIStoryResponse.class).getStories();
    }

    private ChatResponse getAIAnswer(String prompt)
    {
        ChatRequest chatRequest = new ChatRequest();
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setRole("user");
        chatMessage.setContent(prompt);

        chatRequest.setModel("gpt-3.5-turbo");
        chatRequest.setMessages(new ChatMessage[]{chatMessage});

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", OPEN_AI_API_TOKEN))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(chatRequest)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;

        try
        {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), ChatResponse.class);
        }
        catch (IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
