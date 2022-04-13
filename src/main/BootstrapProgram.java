package main;
import moviedatabase.moviedata.MovieContainer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BootstrapProgram {
    private Path moviePath;
    private Path configFile;
    private Path reviewPath;
    public BootstrapProgram(){
        Path test = Paths.get(System.getProperty("user.home"));
            Properties props = new Properties();
            props.setProperty("moviePath", String.valueOf(test));
            props.setProperty("reviewPath", String.valueOf(test));
        try {
            props.store(new FileWriter("config.properties"),"This contains the file locations of our data");
            configFile = Paths.get("config.properties");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConfigFile(String newMoviePath, String newReviewPath) throws IOException {
        FileInputStream in = new FileInputStream(String.valueOf(configFile));
        Properties props = new Properties();
        props.load(in);
        props.setProperty("moviePath",newMoviePath);
        props.setProperty("reviewPath",newReviewPath);
    }
    public boolean loadConfig()  {
       try( FileInputStream in = new FileInputStream(String.valueOf(configFile))) {
           Properties configInfo = new Properties();
           configInfo.load(in);
           moviePath = Path.of(configInfo.getProperty("moviePath"));
           reviewPath = Path.of(configInfo.getProperty("reviewPath"));
           return true;
       }
       catch (IOException e){
           return false;
       }

    }
    public void loadMovies(){
        MovieContainer temp = MovieContainer.getInstance();
        temp.collectMovies(moviePath);
    }
    public static void main(String []args)  {
        BootstrapProgram test = new BootstrapProgram();
        test.loadConfig();
        System.out.println(test.moviePath);
    }

}
