package main;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class BootstrapProgram {
    private String movieFile;
    private String configFile;
    private String reviewPath;
    public void BoostrapProgram(){
        try(FileInputStream in = new FileInputStream("/resources/config.properties")) {
            Properties props = new Properties();
            props.load(in);
            props.setProperty("moviePath", "../../data/data.json");
            props.setProperty("reviewPath", "../../data/reviews.json");
        }
        catch (IOException e){
        }
    }

    public void setConfigFile(String newMoviePath, String newReviewPath) throws IOException {
        FileInputStream in = new FileInputStream(configFile);
        Properties props = new Properties();
        props.load(in);
        props.setProperty("moviePath",newMoviePath);
        props.setProperty("reviewPath",newReviewPath);
    }
    public boolean loadConfig()  {
       try( FileInputStream in = new FileInputStream(configFile)) {
           Properties configInfo = new Properties();
           configInfo.load(in);
           movieFile = configInfo.getProperty("moviePath");
           reviewPath = configInfo.getProperty("reviewPath");
           return true;
       }
       catch (IOException e){
           return false;
       }

    }
    public void loadMovies(){
        MovieContainer temp = MovieContainer.getInstance();
        temp.collectMovies(Path.of(movieFile));
    }


}
