package test;

import moviedatabase.userdata.User;
import moviedatabase.userdata.UserAccount;

import java.nio.file.Paths;

public class TestConstantUserAccount extends Tester {
    public Boolean testSuccess;

    public TestConstantUserAccount() throws Exception {
        super();
    }

    public void run() throws Exception {

        // this makes the global user a guest.
        UserAccount user = new UserAccount();
        user.loginAsGuest();

        if (UserAccount.getInstance().getUserName() != "guest"){
            throw new Exception("Guest account does not initialize properly");
        };

        // Now that we know users are constant we can create one
        user.createAccount("testuser", "test", Paths.get(".testdata"));
        if (UserAccount.getInstance().getUserName() != "testuser"){
            throw new RuntimeException("User creation did not work properly.");
        }
        System.out.println("testuser created Successfully.");
        // Now we go back to guest and login as our new user
        user = new UserAccount();

        if (!user.login("user does not exist", "badpassword", Paths.get(".testdata"))){
            System.out.println("User Did not Exist");
        } else {
            throw new RuntimeException("User existed but shouldn't");
        }

        user = new UserAccount();
        user.login("testuser", "test", Paths.get(".testdata"));

        if (UserAccount.getInstance().getUserName() == "testuser"){
            System.out.println("Test user successfully logged in with persistence!");
        }

        Boolean deleted = user.delUser(Paths.get(".testdata"));
        if (deleted) {
            System.out.println("Deleted User successfully");
        }


    }
}
