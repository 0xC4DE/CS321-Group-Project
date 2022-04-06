package test;

import moviedatabase.userdata.User;
import moviedatabase.userdata.UserAccount;

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
        user.createAccount("testuser", "test", null);
        if (UserAccount.getInstance().getUserName() != "testuser"){
            throw new Exception("User creation did not work properly.");
        }
        System.out.println("testuser created Successfully.");
        // Now we go back to guest and login as our new user
        user = new UserAccount();

        try {
            user.login("user does not exist", "badpassword", null);
        } catch (Exception e) {
            if (e.getMessage() == "Wrong Password."){
                System.out.println("Failure to login with bad user successful.");
            }
        }

        user = new UserAccount();
        user.login("testuser", "test", null);

        if (UserAccount.getInstance().getUserName() == "testuser"){
            System.out.println("Test user successfully logged in with persistence!");
        }
        System.out.println();

    }
}
