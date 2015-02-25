package cu.uci.coj.websocket;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Category implements JacksonEncoder.Encodable {

    private String message;
    private String client;
    private long time;
    private List<String> resources = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private String uuid;

    public Category() {        
        this("", "");
    }

    public Category(String client, String message) {
        this.client = client;
        this.message = message;
        this.time = new Date().getTime();
    }

    public Category(String author, String message, Collection<String> users, Collection<String> rooms) {
        this(author, message);
        this.resources.addAll(users);
        this.categories.addAll(rooms);
    }

    public Category(Collection<String> users, Collection<String> rooms) {
        this.resources.addAll(users);
        this.categories.addAll(rooms);
    }

    public String getMessage() {
        return message;
    }
    
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setUsers(Collection<String> resources) {
        this.resources.addAll(resources);
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
