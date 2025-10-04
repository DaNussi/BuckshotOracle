package net.nussi.buckshot.oracle.engine.item.base;

import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.item.BeerItemHandler;
import net.nussi.buckshot.oracle.engine.item.NotImplementedItemHandler;
import net.nussi.buckshot.oracle.engine.state.GameState;

import java.util.List;

public enum ItemType {
    BEER(new BeerItemHandler()),
    EXPIRED_MEDICINE(null),
    INVERTER(null),
    HAND_SAW(null),
    ADRENALINE(null),
    CIGARETTE_PACK(null),
    MAGNIFYING_GLASS(null),
    BURNER_PHONE(null),
    HANDCUFFS(null);

    private final ItemHandler handler;

    ItemType(ItemHandler handler) {
        if(handler != null)
            this.handler = handler;
        else
            this.handler = new NotImplementedItemHandler();
    }

    public ItemHandler getHandler() {
        return handler;
    }

    public List<GameAction> getActions(GameState gameState) {
        return handler.getActions(gameState);
    }

}
