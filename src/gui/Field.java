package gui;

import javax.swing.JButton;

public class Field extends JButton{

    private int index;
    private boolean clicked;

    public Field(int index) {
        this.index = index;
        clicked = false;
    }

    public int getIndex() {
        return index;
    }

    public boolean getClicked() {
        return clicked;
    }

    public void click(char player) {
        clicked = true;
        setText(Character.toString(player));
    }


}
