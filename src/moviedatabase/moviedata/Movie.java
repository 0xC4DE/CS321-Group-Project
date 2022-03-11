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
