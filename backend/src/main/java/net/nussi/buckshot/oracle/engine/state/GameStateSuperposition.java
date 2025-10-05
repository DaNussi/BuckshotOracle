package net.nussi.buckshot.oracle.engine.state;

import net.nussi.buckshot.oracle.engine.utility.Superposition;

import java.util.*;

public class GameStateSuperposition extends Superposition<GameState> {
    public final Map<GameState, String> desctiptionMap;

    public GameStateSuperposition(HashMap<GameState, Double> probabilityMap, HashMap<GameState, String> descriptionMap) {
        super(probabilityMap);
        this.desctiptionMap = Collections.unmodifiableMap(descriptionMap);
    }
}
