package net.nussi.buckshot.oracle.engine.item.base;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.state.GameState;

import java.util.List;

@Slf4j
public abstract class ItemHandler {

    public abstract List<GameAction> getActions(GameState gameState);


}
