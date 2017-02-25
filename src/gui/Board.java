package gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import root.State;

public class Board extends JFrame {

    private Field[] fields = new Field[9];
    private FieldListener fl = new FieldListener();

    private boolean turnX = true;
    private int turnCounter = 0;

    private State state = new State();

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

    private void gameOver(boolean tie) {
        if (tie) {
            System.out.println("Tie");
        } else {
            // turnX is already switched for next turn
            System.out.println("Winner: " + (turnX ? "O" : "X"));
        }
        System.exit(0);
    }

    private class FieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Field source = (Field) e.getSource();

            if (!source.getClicked()) {
                source.click(turnX ? 'X' : 'O');

                turnX = !turnX;

                state.setState(source.getText(), source.getIndex());

                if (State.isWinning(state)) {
                    gameOver(false);
                }

                turnCounter++;
                if (turnCounter == 9) {
                    gameOver(true);
                }

            } else {
                System.out.println("Please click on another field");
            }
        }
    }
}
