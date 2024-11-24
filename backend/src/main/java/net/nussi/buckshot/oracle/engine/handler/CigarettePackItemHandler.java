package net.nussi.buckshot.oracle.engine.handler;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.ItemHandler;
import net.nussi.buckshot.oracle.engine.action.CigarettePackItemAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CigarettePackItemHandler extends ItemHandler {
    @Override
    public ItemType getType() {
        return ItemType.CIGARETTE_PACK;
    }

    @Override
    public List<GameAction> getActions(GameState gameState) {
        return List.of(new CigarettePackItemAction());
    }
}
