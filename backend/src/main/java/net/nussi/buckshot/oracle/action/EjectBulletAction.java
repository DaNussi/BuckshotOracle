package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EjectBulletAction extends GameAction{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public EjectBulletAction(GameState state) {
        super(state);
    }

    @Override
    public GameState execute() {
        BulletType bullet = state.shotgun.magazine.pop();
        logger.info("A {} round was ejected from the shotgun.", bullet.toString());
        return state;
    }

    @Override
    public String description() {
        return "Ejects a bullet out of the shotgun.";
    }

}
