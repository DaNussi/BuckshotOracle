package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.item.ItemType;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BeerItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {

        state.current.items.remove(ItemType.BEER);

        BulletType bulletType = state.shotgun.eject();

        return List.of(
                new GameActionResult(state, bulletType, 1.0, state.current.name+" ejected a "+bulletType+" round from the shotgun.")
        );
    }

    @Override
    public String description() {
        return "Eject a bullet from the first chamber.";
    }
}
