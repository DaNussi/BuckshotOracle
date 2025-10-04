package net.nussi.buckshot.oracle.engine.item;

import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.item.base.ItemHandler;
import net.nussi.buckshot.oracle.engine.state.GameState;

import java.util.List;

public class BeerItemHandler extends ItemHandler {
    @Override
    public List<GameAction> getActions(GameState gameState) {
        throw new RuntimeException("TODO: Implement me");
    }
}
