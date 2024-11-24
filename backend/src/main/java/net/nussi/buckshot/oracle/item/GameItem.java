package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public abstract class GameItem implements Serializable {

    public String uuid = UUID.randomUUID().toString();
    public abstract List<GameAction> getActions(GameState state);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
