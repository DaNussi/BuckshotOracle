package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.HandcuffsItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandcuffsItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.HANDCUFFS;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        if (gameState.opponent.isHandcuffed) return List.of();
        return List.of(new HandcuffsItemAction());
    }
}
