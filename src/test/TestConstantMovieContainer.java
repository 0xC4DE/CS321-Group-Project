package test;

import moviedatabase.moviedata.MovieContainer;

public class TestConstantMovieContainer extends Tester {
    public Boolean testSuccess;

    public TestConstantMovieContainer(){
        super();
    }

    public void run() {
        System.out.println("Testing whether or not MovieContainer is constant throughout multiple accesses.");
        MovieContainer container = new MovieContainer();
        if (MovieContainer.getInstance().getMovieList() != null) {
            System.out.println("Test Passed.");
            return;
        }
        MovieContainer.setInstance(container);
        if (container.collectMovies(null)) {

            // Debug prints
            //System.out.println(MovieContainer.getInstance().getMovieList());
            //System.out.println(cont.getMovieList());

            // Test if the getInstance is the same as the object we constructed earlier
            if (MovieContainer.getInstance().getMovieList() == container.getMovieList()) {
                System.out.println("Test Passed.");
                testSuccess = true;
            } else {
                System.out.println("Test Failed.");
                testSuccess = false;
            }
            return;
        }
        System.out.println("Was Unable to collect movies (read file).");
        System.out.println("Test Failed.");
        testSuccess = null;
        return;
    }
}
