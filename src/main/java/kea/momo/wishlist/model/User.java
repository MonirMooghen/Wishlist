package kea.momo.wishlist.model;

public class User {

    private String userName;
    private String userFamilyName;
    private String userEmail;
    private String password;

    public User(){

    }

    public User(String username, String password){
        this.userName = username;
        this.password = password;
    }

}
