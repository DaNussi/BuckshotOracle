package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ItemHandler {

    public abstract ItemType getType();

    public abstract List<GameAction> getActions(GameState gameState);
}
