package app.tweeting.models;

import android.util.Log;

import java.util.ArrayList;

import static app.tweeting.helpers.LogHelpers.info;

// Uses the serializer to load and save the timeline
public class UserStore {

    public ArrayList<User> users;
    private UserSerializer userSerializer;


    // loads constructor with creation of userStore
    public UserStore(UserSerializer userSerializer) {
        this.userSerializer = userSerializer;
        try {
            users = userSerializer.loadUsers();
        } catch (Exception e) {
            info(this, "Error loading users: " + e.getMessage());
            users = new ArrayList<>();
        }
    }


    // method to add a user to the list
    public void addUser(User user) {
        users.add(user);
        this.saveUsers();
    }


    // method to get a user by its id
    public User getUser(Long id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);

        for (User user : users) {
            if (id.equals(user.id)) {
                return user;
            }
        }
        return null;
    }


    // method to save all the users to disk
    public boolean saveUsers() {
        try {
            userSerializer.saveUsers(users);
            info(this, "Users saved to file");
            return true;
        } catch (Exception e) {
            info(this, "Error saving users: " + e.getMessage());
            return false;
        }
    }
}