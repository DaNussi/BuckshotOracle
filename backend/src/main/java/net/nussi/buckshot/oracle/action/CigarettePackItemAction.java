package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.item.ItemType;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CigarettePackItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {

        state.current.items.remove(ItemType.CIGARETTE_PACK);
        state.current.health += 1;

        return List.of(
                new GameActionResult(state, null, 1.0, state.current.name+" used a cigarette pack and gained 1hp.")
        );
    }

    @Override
    public String description() {
        return "Inverts the first bullet in the shotgun magazine.";
    }
}
