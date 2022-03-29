package moviedatabase.userdata;

import java.nio.file.Path;

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
