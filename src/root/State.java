package root;

import java.util.ArrayList;
import java.util.Arrays;

public class State {
    private String[] state = new String[9];
    private static String empty = "_";

    public State() {
        Arrays.fill(state, empty);
    }

    public State(String[] initState) {
        state = initState;
    }

    public void setState(String player, int index) {
        state[index] = player;
    }

    public State setStateCopy(String player, int index) {
        String[] stringArray = this.getState();
        State newState = new State(stringArray.clone());
        newState.setState(player, index);
        return newState;
    }

    public ArrayList<Integer> emtpyFields() {
        ArrayList<Integer> empty = new ArrayList<>();

        for (int i = 0; i < this.state.length; i++) {
            String s = this.state[i];
            if (s.equals(State.empty)) {
                empty.add(i);
            }
        }

        return empty;
    }

    public String[] getState() {
        return state;
    }

    public static boolean isWinning(State stateObj) {
        String[] state = stateObj.getState();
        return (state[0].equals(state[1]) && state[0].equals(state[2]) && !state[0].equals(empty)) ||
                (state[3].equals(state[4]) && state[3].equals(state[5]) && !state[3].equals(empty)) ||
                (state[6].equals(state[7]) && state[6].equals(state[8]) && !state[6].equals(empty)) ||

                (state[0].equals(state[3]) && state[0].equals(state[6]) && !state[0].equals(empty)) ||
                (state[1].equals(state[4]) && state[1].equals(state[7]) && !state[1].equals(empty)) ||
                (state[2].equals(state[5]) && state[2].equals(state[8]) && !state[2].equals(empty)) ||

                (state[0].equals(state[4]) && state[0].equals(state[8]) && !state[0].equals(empty)) ||
                (state[2].equals(state[4]) && state[0].equals(state[6]) && !state[0].equals(empty));
    }

    public boolean isWinning(String player) {
        String[] state = this.state;
        return (state[0].equals(state[1]) && state[0].equals(state[2]) && state[0].equals(player)) ||
                (state[3].equals(state[4]) && state[3].equals(state[5]) && state[3].equals(player)) ||
                (state[6].equals(state[7]) && state[6].equals(state[8]) && state[6].equals(player)) ||

                (state[0].equals(state[3]) && state[0].equals(state[6]) && state[0].equals(player)) ||
                (state[1].equals(state[4]) && state[1].equals(state[7]) && state[1].equals(player)) ||
                (state[2].equals(state[5]) && state[2].equals(state[8]) && state[2].equals(player)) ||

                (state[0].equals(state[4]) && state[0].equals(state[8]) && state[0].equals(player)) ||
                (state[2].equals(state[4]) && state[0].equals(state[6]) && state[0].equals(player));
    }


}
