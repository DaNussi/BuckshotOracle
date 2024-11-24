package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.GameActionResult;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.BulletType;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MagnifyingGlassItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {

        state.current.items.remove(ItemType.MAGNIFYING_GLASS);
        BulletType bullet = state.shotgun.magazine.getFirst();

        return List.of(
                new GameActionResult(state, bullet, 1.0, state.current.name+" used a magnifying glass and saw a "+bullet+" round.")
        );
    }

    @Override
    public String description() {
        return "Used to look at the first round in the shotgun chamber.";
    }
}
