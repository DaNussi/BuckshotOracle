package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.action.HandcuffsItemAction;
import net.nussi.buckshot.oracle.action.MagnifyingGlassItemAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MagnifyingGlassItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.MAGNIFYING_GLASS;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new MagnifyingGlassItemAction());
    }
}
