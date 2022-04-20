package views.client;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import moviedatabase.moviesearch.Search;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.BoxLayout.Y_AXIS;

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
    JPopupMenu searchPop = new JPopupMenu(); // popup menu for actions
    static JMenu pAdd = new JMenu("Add");
    WishlistView listView = new WishlistView();

    public JPanel SearchBox() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

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

        searchPanel.add(actor);
        searchPanel.add(director);
        searchPanel.add(genre);
        searchPanel.add(title);

        JTextField textField = new JTextField(20);
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, Y_AXIS));
        JScrollPane boxScroll = new JScrollPane(boxPanel);
        boxScroll.setPreferredSize(new Dimension(200, 200));

        boxPanel.setSize(100,50);

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

            searchFor.sortByName(foundMovies);

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
                listView.handleSearchMouse(buttons.get(j), testMovie);
                panel.revalidate();

            }


            setLayout(new BoxLayout (boxPanel, Y_AXIS));
            setSize(600,400);
            setVisible(true);
            panel.revalidate();

        });
        searchPanel.add(textField);

        panel.add(searchPanel);
        panel.add(boxScroll);
        return panel;
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
