package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.action.InverterItemAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InverterItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.INVERTER;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new InverterItemAction());
    }
}
