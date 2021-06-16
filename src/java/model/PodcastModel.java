package model;


public class PodcastModel {
 
    private String id;
    private String user_id;
    private String name;
    private String about;
    private String subscribers;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PodcastModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }
    
       
    
}
