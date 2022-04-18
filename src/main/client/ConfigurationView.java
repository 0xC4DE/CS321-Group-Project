package main.client;

import main.BootstrapProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigurationView extends JFrame {

    private JTextField reviewFile;
    private JButton reviewButton;

    private JTextField movieFile;
    private JButton movieButton;

    public ConfigurationView() {
        BootstrapProgram p = new BootstrapProgram();
        p.loadConfig();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PopupFactory pop = PopupFactory.getSharedInstance();
        JFrame popupFrame = new JFrame("Popup");
        JPanel popupPanel = new JPanel();
        JLabel l = new JLabel("Invalid Path!");
        popupPanel.add(l);
        Popup popup = pop.getPopup(popupFrame, popupPanel, 100, 100);


        l.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e){
                if (popup != null) {
                    popup.hide();
                }
            }
        });

        setTitle("Config");
        setSize(550,120);
        setResizable(false);
        this.reviewFile = new JTextField(p.getProperty("reviewPath"),30);
        this.reviewButton = new JButton("Submit");

        reviewButton.addActionListener(e -> {
            try {
                if (!Files.exists(Paths.get(reviewFile.getText()))){
                    popup.show();
                    l.requestFocus();
                }
                else {
                    p.setProperty("reviewPath", reviewFile.getText());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        System.out.println(p.getProperty("moviePath"));
        this.movieFile = new JTextField(p.getProperty("moviePath"),30);
        this.movieButton = new JButton("Submit");

        movieButton.addActionListener(e -> {
            try {
                if (!Files.exists(Paths.get(movieFile.getText()))){

                };
                p.setProperty("moviePath", movieFile.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setLayout(new FlowLayout());

        add(new Label("Review File:"));
        add(reviewFile);
        add(reviewButton);
        add(new Label("Movie File:"));
        add(movieFile);
        add(movieButton);
    }

    public static void main(String[] args) {
        ConfigurationView confv = new ConfigurationView();
        confv.setVisible(true);
    }
}


