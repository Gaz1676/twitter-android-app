package app.tweeting.models;

public class Token {
    public boolean success;
    public String token;
    public User user;

    public Token(boolean success, String token)
    {
        this.success = success;
        this.token = token;
    }
}
