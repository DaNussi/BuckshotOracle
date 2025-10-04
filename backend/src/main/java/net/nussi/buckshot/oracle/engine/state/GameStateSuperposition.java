package net.nussi.buckshot.oracle.engine.state;

import net.nussi.buckshot.oracle.engine.utility.Superposition;

import java.util.*;

public class GameStateSuperposition extends Superposition<GameState> {

    public GameStateSuperposition(HashMap<GameState, Double> superposition) {
        super(superposition);
    }
}
