package data_access.APIAccessors.api_json_classes;

public class ChatMessage {
    private String role;
    private String content;

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
