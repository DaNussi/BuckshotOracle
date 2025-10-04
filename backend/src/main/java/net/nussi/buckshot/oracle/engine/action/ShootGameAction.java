package net.nussi.buckshot.oracle.engine.action;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.state.GameStateSuperposition;

import java.util.HashMap;

@Slf4j
public class ShootGameAction extends GameAction {
    public final int playerIndex;

    public ShootGameAction(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    public GameStateSuperposition apply(GameState gameState) throws ImpossibleGameActionException {
        if(playerIndex >= gameState.players.size() || playerIndex <= 0) throw new ImpossibleGameActionException("Player index of bounds", new IndexOutOfBoundsException(playerIndex));

        HashMap<GameState, Double> probabilityMap = new HashMap<>();


        // TODO

        return null;
    }
}
