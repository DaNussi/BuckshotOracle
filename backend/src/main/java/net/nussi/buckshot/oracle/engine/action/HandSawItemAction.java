package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.GameActionResult;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HandSawItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {

        state.current.items.remove(ItemType.HAND_SAW);

        state.shotgun.isSawedOf = true;

        return List.of(
                new GameActionResult(state, null, 1.0, state.current.name+" sawed of the shotgun.")
        );
    }

    @Override
    public String description() {
        return "Saw of the end of the shotgun for double damage.";
    }
}
