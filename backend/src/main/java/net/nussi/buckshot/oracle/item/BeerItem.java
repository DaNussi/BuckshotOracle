package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.EjectBulletAction;
import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.action.ItemAction;
import net.nussi.buckshot.oracle.state.GameState;

import java.util.List;

public class BeerItem extends GameItem {
    @Override
    public List<GameAction> getActions(GameState state) {
        return List.of(new ItemAction(state, this, new EjectBulletAction(state)));
    }
}
