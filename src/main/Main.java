package main;

import views.client.Dashboard;

import views.client.LoginView;
import main.reviews.ReviewManager;

import moviedatabase.moviedata.MovieContainer;
import moviedatabase.userdata.UserAccount;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String []args) throws Exception {
        Lock lock = new ReentrantLock();
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);
        

        // creates test login
        UserAccount user = new UserAccount();
        user.createAccount("user", "user", null);
        user.logoutUser();


        Condition finished= lock.newCondition();
        LoginView test = new LoginView();
        //locking execution to start the await process
        //Basically, I place a lock on  this main thread, then await for the login to finish to continue with the program
        lock.lock();



        try {
            test.loginLoop(finished,lock);
            finished.await();
        }
        catch (Exception e){

        }
        finally {
            lock.unlock();

        }


        BootstrapProgram bootstrapFiles = new BootstrapProgram();
        ReviewManager setupReviews = new ReviewManager(bootstrapFiles);
        Dashboard d = new Dashboard();

    }
}
