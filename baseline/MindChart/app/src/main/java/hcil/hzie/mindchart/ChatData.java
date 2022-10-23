package hcil.hzie.mindchart;

public class ChatData {
    private String timestamp;
    private String message;
    private String name;

    public ChatData(){}
    public ChatData(String timestamp, String message, String name){
        this.timestamp = timestamp;
        this.message = message;
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
