package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.state.GameState;

public class GameActionResult {
    public GameState state;
    public Object data;
    public double probability;
    public String message;

    public GameActionResult(GameState state, Object data, double probability) {
        this.state = state;
        this.data = data;
        this.probability = probability;
        this.message = "";
    }

    public GameActionResult(GameState state, Object data, double probability, String message) {
        this.state = state;
        this.data = data;
        this.probability = probability;
        this.message = message;
    }
}
