package main.client;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import moviedatabase.moviesearch.Search;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// TODO: add search "button"
// results below bar
// remove previous results
// scroll bar for results
// make results instantly active (mine only show after click)

public class SearchView extends JPanel implements ActionListener {
    private String searchBy = "Title";
    private JRadioButton actor = new JRadioButton("Actor", false);
    private JRadioButton director = new JRadioButton("Director Name", false);
    private JRadioButton genre = new JRadioButton("Genre", false);
    private JRadioButton title = new JRadioButton("Title", true);

    public JPanel SearchBox() {

        JFrame myFrame = new JFrame();
        myFrame.setLayout(new FlowLayout());
        JPanel panel = new JPanel();

        actor.setBounds(100,50,100,30);
        director.setBounds(100,50,100,30);
        genre.setBounds(100,50,100,30);
        title.setBounds(100,50,100,30);

        // create a panel for the radio buttons
        actor.addActionListener(this);
        director.addActionListener(this);
        genre.addActionListener(this);
        title.addActionListener(this);

        ButtonGroup bg = new ButtonGroup();
        bg.add(actor);
        bg.add(director);
        bg.add(genre);
        bg.add(title);

        panel.add(actor);
        panel.add(director);
        panel.add(genre);
        panel.add(title);

        JTextField textField = new JTextField(20);
        JPanel boxPanel = new JPanel();

        boxPanel.setSize(100,30);
        //boxPanel.setLocale();
        //setLayout(boxPanel, BoxLayout.Y_AXIS);
        textField.addActionListener(e -> {
            List<Movie> foundMovies;
            Search searchFor = new Search();
            String text = textField.getText();

            foundMovies = switch (searchBy) {
                case "Actor" -> searchFor.searchByActor(text);
                case "Director" -> searchFor.searchByDirector(text);
                case "Genre" -> searchFor.searchByGenre(text);
                default ->  // search by title
                        searchFor.searchByName(text);
            };

            ArrayList<JButton> buttons = new ArrayList<JButton>();

            for (int j = 0;j<foundMovies.size();j++) {

                Movie testMovie = foundMovies.get(j);
                buttons.add(j,new JButton(testMovie.getTitle()));
                buttons.get(j).addActionListener(f->
                {
                    SingleMovieView aMovie = new SingleMovieView();
                    aMovie.show(testMovie);
                });
                // adding the buttons so that Movies can be displayed
                boxPanel.add(buttons.get(j));

            }
            setLayout (new BoxLayout (boxPanel, BoxLayout.X_AXIS));
            setSize(400,400);
            setVisible(true);
            panel.revalidate();

        });
        boxPanel.add(textField);
        panel.add(boxPanel);
        return panel;
    }
    private void showMovies(List<Movie> moviesToShow){


    }

    public static void main(String[] args) {
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);
        SearchView test = new SearchView();
        test.SearchBox();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(actor.isSelected()){
            searchBy = "Actor";
        }
        else if(director.isSelected()){
            searchBy = "Director";
        }
        else if(genre.isSelected()){
            searchBy = "Genre";
        }
        else {
            searchBy = "Title";
        }
    }
}
