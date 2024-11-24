package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.GameActionResult;
import net.nussi.buckshot.oracle.engine.GameEngine;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.state.ShotgunState;
import net.nussi.buckshot.oracle.engine.type.BulletType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShotOpponentAction  extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) {
        ShotgunState shotgun = state.shotgun;
        int damage = shotgun.damage();
        BulletType bullet = shotgun.eject();

        if (bullet == BulletType.BLANK) {
            GameEngine.endTurn(state);
            return List.of(new GameActionResult(state, null, 1.0, state.current.name+" shot "+state.opponent.name+" with a blank bullet."));
        } else {
            state.opponent.health -= damage;
            GameEngine.endTurn(state);
            return List.of(new GameActionResult(state, null, 1.0, state.current.name+" shot "+state.opponent.name+ ", "+damage+" damage taken."));
        }

    }

    @Override
    public String description() {
        return "The current player shots the opponent with the shotgun.";
    }

}
