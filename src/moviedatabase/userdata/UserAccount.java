package moviedatabase.userdata;

import moviedatabase.moviedata.Movie;

import java.nio.file.Path;
import java.util.List;

public class UserAccount {

    private static UserAccount instance;
    private User user;

    /*
    Default user account is null
     */
    private UserAccount() {
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
        this.user = User.createUser(username, password, userFile);
    }

    public void login(String username, String password, Path userFile){
        // ensure filepath exists
        this.user = user.getUser(username, password, null);
    }

    public void loginAsGuest(){
        user = new User();
    }

}
