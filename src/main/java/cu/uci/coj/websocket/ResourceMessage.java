package cu.uci.coj.websocket;

public class ResourceMessage {

    private String message;
    private String client;

    public ResourceMessage(){
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
