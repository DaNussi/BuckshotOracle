package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.BeerItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerItemHandler extends ItemHandler{
    @Override
    public ItemType getType() {
        return ItemType.BEER;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new BeerItemAction());
    }
}
