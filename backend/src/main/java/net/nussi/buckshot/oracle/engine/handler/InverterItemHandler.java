package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.InverterItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
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