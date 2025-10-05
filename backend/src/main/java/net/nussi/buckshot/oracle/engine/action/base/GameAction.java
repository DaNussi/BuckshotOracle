package net.nussi.buckshot.oracle.engine.action.base;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.ImpossibleGameActionException;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.state.GameStateSuperposition;

@Slf4j
public abstract class GameAction {

    public abstract GameStateSuperposition apply(GameState gameState) throws ImpossibleGameActionException;
    public abstract String describe(GameState gameState) throws ImpossibleGameActionException;

}
