package moviedatabase.moviedata;

/*
Here's the schema.
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "Title": {
      "type": "string"
    },
    "Year": {
      "type": "string"
    },
    "Rated": {
      "type": "string"
    },
    "Released": {
      "type": "string"
    },
    "Runtime": {
      "type": "string"
    },
    "Genre": {
      "type": "string"
    },
    "Director": {
      "type": "string"
    },
    "Writer": {
      "type": "string"
    },
    "Actors": {
      "type": "string"
    },
    "Plot": {
      "type": "string"
    },
    "Language": {
      "type": "string"
    },
    "Country": {
      "type": "string"
    },
    "Awards": {
      "type": "string"
    },
    "Poster": {
      "type": "string"
    },
    "Ratings": {
      "type": "array",
      "items": [
        {
          "type": "object",
          "properties": {
            "Source": {
              "type": "string"
            },
            "Value": {
              "type": "string"
            }
          },
          "required": [
            "Source",
            "Value"
          ]
        }
      ]
    },
    "Metascore": {
      "type": "string"
    },
    "imdbRating": {
      "type": "string"
    },
    "imdbVotes": {
      "type": "string"
    },
    "imdbID": {
      "type": "string"
    },
    "Type": {
      "type": "string"
    },
    "DVD": {
      "type": "string"
    },
    "BoxOffice": {
      "type": "string"
    },
    "Production": {
      "type": "string"
    },
    "Website": {
      "type": "string"
    },
    "Response": {
      "type": "string"
    }
  },
  "required": [
    "Title",
    "Year",
    "Rated",
    "Released",
    "Runtime",
    "Genre",
    "Director",
    "Writer",
    "Actors",
    "Plot",
    "Language",
    "Country",
    "Awards",
    "Poster",
    "Ratings",
    "Metascore",
    "imdbRating",
    "imdbVotes",
    "imdbID",
    "Type",
    "DVD",
    "BoxOffice",
    "Production",
    "Website",
    "Response"
  ]
}
 */
public class Movie {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private Object Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;
    private String Response;
    //private String userReview;


    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    /**
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
     **/

    public Movie() {

    }
}
