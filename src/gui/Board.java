package gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import root.State;
import root.Minimax;

public class Board extends JFrame {

    private Field[] fields = new Field[9];
    private FieldListener fl = new FieldListener();

    private boolean turnX = true;
    private int turnCounter = 0;

    private boolean aiMode;
    private Minimax ai;

    private State state = new State();

    public Board(boolean aiMode) {
        super();
        initComponents();
        this.aiMode = aiMode;

        if (aiMode) {
            ai = new Minimax("O", "X");
        }
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

    public void fieldClicked(int index) {
        Field field = fields[index];
        if (!field.getClicked()) {
            field.click(turnX ? 'X' : 'O');

            turnX = !turnX;

            state.setState(field.getText(), field.getIndex());

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

    public ArrayList<Integer> emptyFields() {
        ArrayList<Integer> empty = new ArrayList<Integer>();

        for (Field field : fields) {
            if (!field.getClicked()) {
                empty.add(field.getIndex());
            }
        }

        return empty;
    }

    private class FieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Field source = (Field) e.getSource();
            fieldClicked(source.getIndex());

            if (aiMode) {
                int nextMove = ai.nextMove(state, "O");
                fieldClicked(nextMove);
            }
        }
    }
}
