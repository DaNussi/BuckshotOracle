package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.AdrenalineItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AdrenalineItemHandler extends ItemHandler{
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