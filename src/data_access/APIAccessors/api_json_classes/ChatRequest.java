package data_access.APIAccessors.api_json_classes;

public class ChatRequest
{
    private String model;
    private ChatMessage[] messages;

    public void setModel(String model) {
        this.model = model;
    }

    public void setMessages(ChatMessage[] messages) {
        this.messages = messages;
    }
}
