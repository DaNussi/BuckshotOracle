package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.HandSawItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandSawItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.HAND_SAW;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        List<GameAction> actions = new ArrayList<>();
        if(!gameState.shotgun.isSawedOf) actions.add(new HandSawItemAction());
        return actions;
    }
}
