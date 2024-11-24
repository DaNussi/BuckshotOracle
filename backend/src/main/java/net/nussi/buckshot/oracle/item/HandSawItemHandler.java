package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.AdrenalineItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.action.HandSawItemAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class HandSawItemHandler extends ItemHandler{
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
