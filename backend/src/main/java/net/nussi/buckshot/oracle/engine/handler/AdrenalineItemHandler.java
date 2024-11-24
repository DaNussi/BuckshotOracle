package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.AdrenalineItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AdrenalineItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.ADRENALINE;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        List<GameAction> actions = new ArrayList<>();

        for(var itemToSteal : new HashSet<>(gameState.opponent.items))
            actions.add(new AdrenalineItemAction(itemToSteal));

        return actions;
    }
}
