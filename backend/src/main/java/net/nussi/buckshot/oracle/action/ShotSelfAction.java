package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.service.GameEngine;
import net.nussi.buckshot.oracle.state.GameState;
import net.nussi.buckshot.oracle.state.ShotgunState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShotSelfAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) {
        ShotgunState shotgun = state.shotgun;
        int damage = shotgun.damage();
        BulletType bullet = shotgun.eject();

        if (bullet == BulletType.BLANK) {
            return List.of(new GameActionResult(state, null, 1.0, state.current.name+" shot himself with a blank bullet."));
        } else {
            state.current.health -= damage;
            GameEngine.endTurn(state);
            return List.of(new GameActionResult(state, null, 1.0, state.current.name+" shot himself, "+damage+" damage taken."));
        }
    }

    @Override
    public String description() {
        return "The current player shots himself with the shotgun.";
    }

}
