package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.HandcuffsItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
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
