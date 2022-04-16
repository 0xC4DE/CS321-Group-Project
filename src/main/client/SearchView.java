package main.client;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import moviedatabase.moviesearch.Search;
import javax.swing.*;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class SearchView extends JPanel implements ActionListener {
    private String searchBy;
    private  JRadioButton test = new JRadioButton("Actor");
    private JRadioButton director = new JRadioButton("Director Name");
    private JRadioButton genre = new JRadioButton("Genre");
    private JRadioButton title = new JRadioButton("Title");
    public JPanel SearchBox() {
        /*JFrame myFrame = new JFrame();
        JTextField test = new JTextField(35);
        test.addActionListener(e -> {
            List<Movie> foundMovies;
            Search searchFor = new Search();
            String text = test.getText();
            foundMovies = searchFor.searchByName(text);
            showMovies(foundMovies);

        });
        myFrame.add(test);
        setSize(400,400);
        setVisible(true);*/
        JFrame myFrame = new JFrame();
        myFrame.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        test.setBounds(100,50,100,30);
        director.setBounds(100,50,100,30);
        genre.setBounds(100,50,100,30);
        title.setBounds(100,50,100,30);
        // create a panel for the radio buttons
        test.addActionListener(this);
        director.addActionListener(this);
        genre.addActionListener(this);
        title.addActionListener(this);
        ButtonGroup bg = new ButtonGroup();
        bg.add(test);
        panel.add(test);
        panel.add(director);
        panel.add(genre);
        panel.add(title);
        JTextField textField = new JTextField(20);
        JPanel boxPanel = new JPanel();

        boxPanel.setSize(100,30);
        textField.addActionListener(e -> {
            List<Movie> foundMovies;
            Search searchFor = new Search();
            String text = textField.getText();
            if(searchBy.equals("Actor")) {
                foundMovies = searchFor.searchByActor(text);
            }
            if(searchBy.equals("Director")) {
                foundMovies = searchFor.searchByDirector(text);
            }
            if(searchBy.equals("Genre")) {
                foundMovies = searchFor.searchByGenre(text);
            }
            foundMovies = searchFor.searchByName(text);
            ArrayList<JButton> buttons = new ArrayList<JButton>();

            for (int j = 0;j<foundMovies.size();j++) {

                Movie testMovie = foundMovies.get(j);
                buttons.add(j,new JButton(testMovie.getTitle()));
                buttons.get(j).addActionListener(f->
                {
                    SingleMovieView hey = new SingleMovieView();
                    hey.show(testMovie);
                });
                // adding the buttons so that it can be displayed
                boxPanel.add(buttons.get(j));

            }
            setLayout (new BoxLayout (boxPanel, BoxLayout.X_AXIS));
            setSize(400,400);
            setVisible(true);

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
        if(test.isSelected()){
            searchBy = "Actor";
        }
        else if(director.isSelected()){
            searchBy = "Director";
        }
        else if(genre.isSelected()){
            searchBy = "Genre";
        }
        else{
            searchBy = "Title";
        }
    }
}
