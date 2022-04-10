package moviedatabase.userdata;

import moviedatabase.moviedata.Movie;

import java.nio.file.Path;
import java.util.List;

public class UserAccount {

    private static UserAccount instance = new UserAccount();
    private User user;

    /*
    Default user account is null
     */
    public UserAccount() {
        user = null;
        instance = this;
    }

    public static UserAccount getInstance(){
        return instance;
    }

    public Boolean changeName(String newName){
        user.setUsername(newName);
        return true;
    }

    public List<Movie> getWishlist(){return user.wishlistsToStore;}
    public void SetList(List<Movie> listToSet){
        user.wishlistsToStore = listToSet;
    }


    public void createAccount(String username, String password, Path userFile){
        // I dont like this pattern, which indicates createUser should be a util, instead of being in user
        // TODO: Make createUser a util instead of a user
        User user = new User();
        this.user = user.createUser(username, password, userFile);
    }

    public void login(String username, String password, Path userFile){
        User user = new User();
        this.user = user.getUser(username, password, null);
    }

    /*
    This constructs a guest with username guest and no password
     */
    public void loginAsGuest(){
        this.user = new User();
    }

    public String getUserName(){
        return instance.user.getUsername();
    }
}
