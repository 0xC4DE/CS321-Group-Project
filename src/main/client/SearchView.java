package main.client;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import moviedatabase.moviesearch.Search;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class SearchView extends Frame implements ActionListener {
    private String searchBy;
    private  JRadioButton test = new JRadioButton("Title");
    private JRadioButton director = new JRadioButton("Director Name");
    public void SearchBox() {
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
        // create a panel for the radio buttons
        test.addActionListener(this);
        ButtonGroup bg = new ButtonGroup();
        bg.add(test);
        panel.add(test);
        panel.add(director);
        JTextField textField = new JTextField(20);
        myFrame.add(textField);
        myFrame.add(panel);
        myFrame.setSize(100,10);
        myFrame.setVisible(true);
        textField.addActionListener(e -> {
            List<Movie> foundMovies;
            Search searchFor = new Search();
            String text = textField.getText();
            foundMovies = searchFor.searchByName(text);
            showMovies(foundMovies);

        });
    }
    private void showMovies(List<Movie> moviesToShow){
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        List<Movie> test = MovieContainer.getInstance().getMovieList();

        for (int j = 0;j<5;j++) {

            Movie testMovie = test.get(j);
            buttons.add(j,new JButton(testMovie.getTitle()));
            buttons.get(j).addActionListener(e->
            {
                SingleMovieView hey = new SingleMovieView();
                hey.show(testMovie);
            });
            // adding the buttons so that it can be displayed
            add(buttons.get(j));

        }
        setLayout (new BoxLayout (this, BoxLayout.X_AXIS));
        setSize(400,400);
        setVisible(true);

    }

    public static void main(String[] args) {
        SearchView test = new SearchView();
        test.SearchBox();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(test.isSelected()){
            searchBy = "Title";
        }
        else if(director.isSelected()){
            searchBy = "Director";
        }
    }
}
