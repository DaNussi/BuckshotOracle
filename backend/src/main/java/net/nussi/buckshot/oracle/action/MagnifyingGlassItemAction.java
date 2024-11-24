package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.item.ItemType;
import net.nussi.buckshot.oracle.state.GameState;
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
