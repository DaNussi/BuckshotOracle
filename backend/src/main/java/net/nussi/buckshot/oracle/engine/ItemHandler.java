package net.nussi.buckshot.oracle.engine;

import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ItemHandler {

    public abstract ItemType getType();

    public abstract List<GameAction> getActions(GameState gameState);
}
