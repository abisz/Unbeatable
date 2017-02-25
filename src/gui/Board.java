package gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import gui.Field;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame {

    private Field[] fields = new Field[9];
    private FieldListener fl = new FieldListener();

    private boolean turnX = true;

    public Board() {
        super();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(i);

            fields[i].addActionListener(fl);

            this.add(fields[i]);
        }

        this.setMinimumSize(new Dimension(500, 500));
        pack();
        setVisible(true);
    }

    public void evaluateState(String[] state) {
        if (
            // Rows
            (state[0].equals(state[1]) && state[0].equals(state[2]) && !state[0].equals("_")) ||
            (state[3].equals(state[4]) && state[3].equals(state[5]) && !state[3].equals("_")) ||
            (state[6].equals(state[7]) && state[6].equals(state[8]) && !state[6].equals("_")) ||

            // Columns
            (state[0].equals(state[3]) && state[0].equals(state[6]) && !state[0].equals("_")) ||
            (state[1].equals(state[4]) && state[1].equals(state[7]) && !state[1].equals("_")) ||
            (state[2].equals(state[5]) && state[2].equals(state[8]) && !state[2].equals("_")) ||

            // Diagonals
            (state[0].equals(state[4]) && state[0].equals(state[8]) && !state[0].equals("_")) ||
            (state[2].equals(state[4]) && state[0].equals(state[6]) && !state[0].equals("_"))

        ) {
            System.out.println("Game Over");
        }
    }

    private class FieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Field source = (Field) e.getSource();

            if (!source.getClicked()) {
                source.click(turnX ? 'X' : 'O');
                turnX = !turnX;

                String[] state = new String[9];

                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].getText().isEmpty()) {
                        state[i] = "_";
                    } else {
                        state[i] = fields[i].getText();
                    }
                }

                evaluateState(state);

            } else {
                System.out.println("Please click on another field");
            }
        }
    }
}
