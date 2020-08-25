package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    Universe universe;
    JPanel field;
    JLabel GenerationLabel;
    JLabel AliveLabel;
    boolean paused;

    public GameOfLife() {
        paused = false;
        universe = new Universe(15);
        this.setName("Game of Life");
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        initComponenets();
        setVisible(true);
    }

    void initComponenets() {
        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(100, 200));
        this.add(info, BorderLayout.WEST);

        JToggleButton PlayToggleButton = new JToggleButton();
        PlayToggleButton.setName("PlayToggleButton");
        PlayToggleButton.setText("⏯");
        PlayToggleButton.setPreferredSize(new Dimension(100, 50));
        info.add(PlayToggleButton);

        PlayToggleButton.addActionListener(e -> {
            paused = !paused;
            if (paused == false) {
                Thread t = new GameOfLifeCycle(this);
                t.start();
            }
        });

        JButton ResetButton = new JButton();
        ResetButton.setName("ResetButton");
        ResetButton.setText("↺");
        ResetButton.setPreferredSize(new Dimension(100, 50));
        info.add(ResetButton);

        ResetButton.addActionListener(e -> {
            universe = new Universe(universe.getBoard().length);
        });

        GenerationLabel = new JLabel();
        GenerationLabel.setName("GenerationLabel");
        info.add(GenerationLabel);


        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        info.add(AliveLabel);

        info.setLayout(new GridLayout(10, 1, 5, 5));

        this.field = new JPanel();
        field.setPreferredSize(new Dimension(700, 700));
        int size = universe.getBoard().length;
        field.setLayout(new GridLayout(size, size));
        updateField();

        GenerationLabel.setText("Generation #1");
        AliveLabel.setText("Alive: " + universe.getAlive());
        this.add(field);
    }

    void updateTextFields() {
        StringBuilder alive = new StringBuilder(AliveLabel.getText());
        alive.replace(alive.indexOf(":") + 2, alive.length(), Integer.toString(universe.getAlive()));
        AliveLabel.setText(alive.toString());

        StringBuilder generation = new StringBuilder(GenerationLabel.getText());
        generation.replace(generation.indexOf("#") + 1, generation.length(), Integer.toString(universe.getGeneration()));
        GenerationLabel.setText(generation.toString());
    }

    void updateField() {
        boolean[][] board = universe.getBoard();
        this.field.removeAll();

        int size = universe.getBoard().length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel rect = new JLabel();
                rect.setBorder(BorderFactory.createLineBorder(Color.black));
                if (board[i][j]) {
                    rect.setOpaque(true);
                    rect.setBackground(Color.black);
                }
                field.add(rect);
            }
        }
    }

    void updateWindow() {
        updateField();
        updateTextFields();
        setVisible(true);
    }


    void play() {
        while (paused == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            universe.nextGeneration();
            updateWindow();
        }
    }
}
