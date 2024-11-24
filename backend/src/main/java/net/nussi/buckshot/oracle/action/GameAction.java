package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public List<GameActionResult> execute(GameState state) throws Exception {
        GameState innerState = state.clone();
        List<GameActionResult> results = innerExecute(innerState);
        for(var result : results) result.state.step += 1;
        return results;
    }
    public abstract List<GameActionResult> innerExecute(GameState state) throws Exception;
    public abstract String description();
}
