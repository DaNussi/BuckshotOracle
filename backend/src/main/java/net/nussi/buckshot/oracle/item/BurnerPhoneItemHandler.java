package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.BurnerPhoneItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BurnerPhoneItemHandler extends ItemHandler{
    @Override
    public ItemType getType() {
        return ItemType.BURNER_PHONE;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new BurnerPhoneItemAction());
    }
}
