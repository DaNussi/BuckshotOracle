package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.ExpiredMedicineItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ExpiredMedicineItemHandler extends ItemHandler{
    @Override
    public ItemType getType() {
        return ItemType.EXPIRED_MEDICINE;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new ExpiredMedicineItemAction());
    }
}
