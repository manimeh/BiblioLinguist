package data_access.APIAccessors.api_json_classes;

public class ChatResponse {
    private String id;
    private AnswerChoice[] choices;

    public AnswerChoice[] getChoices() {
        return choices;
    }
}
