package models;

public class UserModel {
    private int userId;
    private String username;
    private String userToken;

    public UserModel(int userId, String username, String userToken) {
        this.userId = userId;
        this.username = username;
        this.userToken = userToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
