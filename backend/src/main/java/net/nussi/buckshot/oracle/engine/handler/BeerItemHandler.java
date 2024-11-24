package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.BeerItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.BEER;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new BeerItemAction());
    }
}
