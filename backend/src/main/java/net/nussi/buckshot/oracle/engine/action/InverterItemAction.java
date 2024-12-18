package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.GameActionResult;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.BulletType;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InverterItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {

        state.current.items.remove(ItemType.INVERTER);

        BulletType bulletType = state.shotgun.magazine.getFirst();
        if(bulletType == BulletType.BLANK)
            state.shotgun.magazine.set(0,BulletType.LIVE);
        else
            state.shotgun.magazine.set(0,BulletType.BLANK);

        return List.of(
                new GameActionResult(state, null, 1.0, state.current.name+" inverted a "+bulletType+" round from the shotgun.")
        );
    }

    @Override
    public String description() {
        return "Inverts the first bullet in the shotgun magazine.";
    }
}
