package main;

import main.client.Dashboard;

import main.client.LoginView;
import main.reviews.ReviewManager;
import moviedatabase.moviedata.Movie;

import moviedatabase.moviedata.MovieContainer;
import moviedatabase.userdata.UserAccount;
import test.TestConstantMovieContainer;
import test.TestConstantUserAccount;
import test.TestGSON;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String []args) throws Exception {
        Lock lock = new ReentrantLock();
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);

        // TODO: Make GSON actually eat the movie data instead of dying.
        //System.out.println(cont.getMovieList().get(0).getTitle());


        //new TestGSON();
        //new TestConstantUserAccount();


        Condition finished= lock.newCondition();
        LoginView test = new LoginView();
        //locking execution to start the await process
        //Basically, I place a lock on  this main thread, then await for the login to finish to continue with the program
        lock.lock();


        UserAccount user = new UserAccount();
        user.createAccount("user", "user", null);
        user.logoutUser();
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
