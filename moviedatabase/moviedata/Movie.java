package moviedatabase.moviedata;
/*{"Title":"Spiderman","Year":"1990","Rated":"N/A","Released":"N/A","Runtime":"5 min",
"Genre":"Short","Director":"Christian Davi","Writer":"N/A","Actors":"N/A","Plot":"N/A",
"Language":"German","Country":"Switzerland","Awards":"N/A","Poster":"N/A","Ratings":
[{"Source":"Internet Movie Database","Value":"5.6/10"}],"Metascore":"N/A"
,"imdbRating":"5.6","imdbVotes":"96","imdbID":"tt0100669",
"Type":"movie","DVD":"N/A","BoxOffice":"N/A","Production":"N/A","Website":"N/A","Response":"True"},
 */
public class Movie {
    private String title;
    private String year;
    private String movieRating;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plotSum;
    private String language;
    private String country;
    private String awards;
    private String poster;


    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }
}
