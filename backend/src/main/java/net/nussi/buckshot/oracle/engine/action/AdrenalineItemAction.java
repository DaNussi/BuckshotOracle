package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.GameActionResult;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdrenalineItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ItemType itemToSteal;

    public AdrenalineItemAction(ItemType itemToSteal) {
        this.itemToSteal = itemToSteal;
    }

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {
        if(!state.opponent.items.contains(itemToSteal)) throw new IllegalArgumentException("Opponent dose't have the item!");

        state.current.items.remove(ItemType.ADRENALINE);
        state.opponent.items.remove(itemToSteal);
        state.current.items.add(itemToSteal);

        return List.of(
                new GameActionResult(state, null, 1.0, state.current.name+" stole "+itemToSteal+" from "+state.opponent.name+".")
        );
    }

    @Override
    public String description() {
        return "Allows the user to steal "+itemToSteal+" from the opponent.";
    }
}
