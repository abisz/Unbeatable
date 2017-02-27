package root;

import root.State;

import java.util.ArrayList;
import java.util.HashMap;

public class Minimax {

    private String player;
    private String opponent;

    public Minimax(String player, String opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public MoveEntry<Integer, Integer> nextMove(State state, String activePlayer) {
        ArrayList<Integer> emptyFields = state.emtpyFields();

        if (state.isWinning(this.player)) {
            return new MoveEntry<>(-1, 10);
        } else if (State.isWinning(state)) {
            return new MoveEntry<>(-1, -10);
        } else if (emptyFields.size() == 0) {
            return new MoveEntry<>(-1, 0);
        }

        HashMap<Integer, Integer> moves = new HashMap<>();

        for (Integer emptyField : emptyFields) {
            State newState = state.setStateCopy(activePlayer, emptyField);
            String nextPlayer = activePlayer.equals(this.player) ? this.opponent : this.player;
            moves.put(emptyField, nextMove(newState, nextPlayer).getValue());
        }

        // To make compiler happy, because otherwise it might have not been initialized...
        Integer bestMove = -1;
        Integer bestScore = activePlayer.equals(this.player) ? -100 : 100;

        for (HashMap.Entry<Integer, Integer> entry : moves.entrySet()) {
            Integer index = entry.getKey();
            Integer score = entry.getValue();

            if ((activePlayer.equals(this.player) && score > bestScore) ||
                    (activePlayer.equals(this.opponent) && score < bestScore)) {
                bestScore = score;
                bestMove = index;
            }
        }

        return new MoveEntry<>(bestMove, bestScore);
    }

}
