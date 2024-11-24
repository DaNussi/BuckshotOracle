package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.ExpiredMedicineItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpiredMedicineItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.EXPIRED_MEDICINE;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new ExpiredMedicineItemAction());
    }
}
