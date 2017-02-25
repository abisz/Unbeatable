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

    private class FieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Field source = (Field) e.getSource();

            if (!source.getClicked()) {
                source.click();
            } else {
                System.out.println("Please click on another field");
            }
        }
    }
}
