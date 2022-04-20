package views.client;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Dashboard {

    /**
     *Creates the dashboard
     */
    public Dashboard() {
        JFrame window = new JFrame("Dashboard");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close window on 'X' always

        SearchView searchPanel = new SearchView();
        WishlistView wishlistPanel = new WishlistView();
        UserProfile profilePanel = new UserProfile();

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.setBounds(50,30,600,500);

        // tab text is white when selected on macOS
        tabPanel.add("Search", searchPanel.SearchBox());
        tabPanel.add("Wishlists", wishlistPanel.showWishlists());
        tabPanel.add("Profile", profilePanel.getProfile());
        window.add(tabPanel);
        window.setSize(tabPanel.getWidth()+100,tabPanel.getHeight()+100);
        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                tabPanel.setSize(window.getWidth()-100,window.getHeight()-100);
                tabPanel.repaint();
            }
        });
        window.setLayout(null);
        window.setVisible(true);

    }

}
