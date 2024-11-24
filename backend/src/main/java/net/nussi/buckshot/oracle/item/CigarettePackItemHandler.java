package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.BeerItemAction;
import net.nussi.buckshot.oracle.action.CigarettePackItemAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CigarettePackItemHandler extends ItemHandler{
    @Override
    public ItemType getType() {
        return ItemType.CIGARETTE_PACK;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new CigarettePackItemAction());
    }
}
