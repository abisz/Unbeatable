package root;

import root.State;

import java.util.ArrayList;

public class Minimax {

    private String player;
    private String opponent;

    public Minimax(String player, String opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public int nextMove(State state, String activePlayer) {
        ArrayList<Integer> emptyFields = state.emtpyFields();

        if (state.isWinning(player)) {
            return 10;
        } else if (State.isWinning(state)) {
            // check if ai is winning has already failed, therefore this case is human winning
            return -10;
        } else if (emptyFields.size() == 0) {
            return 0;
        }

        for (int i = 0; i < emptyFields.size(); i++) {
            State newState = state.setStateCopy(activePlayer, emptyFields.get(1));


        }

        return 0;
    }

}
