package src.main.java;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BootstrapProgram {
    private String movieFile;
    private String configFile;
    public void setConfigFile(String newConfig){
        configFile = newConfig;
    }
    public boolean loadConfig() throws FileNotFoundException {
        try{
            File configObj = new File(configFile);
            Scanner configReader = new Scanner(configObj);
            while (configReader.hasNextLine()){
                movieFile = configReader.nextLine();
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public void loadMovies(){
        MovieContainer temp = new MovieContainer();
        Path moviePath = Paths.(movieFile);
        temp.collectMovies(movieFile);
    }


}
