package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.item.ShotgunItem;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShotSelfAction extends GameAction{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    double successProbability = 0;

    public ShotSelfAction(GameState state) {
        super(state);

        successProbability = ((double) state.shotgun.countBlankRounds()) / ((double) state.shotgun.countTotalRounds());
    }

    @Override
    public GameState execute() {
        BulletType bullet = state.shotgun.magazine.pop();
        int damage = ShotgunItem.calculateDamage(bullet, state.shotgun.isSawedOf);

        if(state.isPlayerTurn) state.player.health -= damage;
        if(!state.isPlayerTurn) state.dealer.health -= damage;


        if(state.isPlayerTurn && bullet == BulletType.BLANK) logger.info("Player shot himself but the round was a blank.");
        if(state.isPlayerTurn && bullet == BulletType.LIVE) logger.info("Player shot himself for {} damage.", damage);
        if(!state.isPlayerTurn && bullet == BulletType.BLANK) logger.info("Dealer shot himself but the round was a blank.");
        if(!state.isPlayerTurn && bullet == BulletType.LIVE) logger.info("Dealer shot himself for {} damage.", damage);

        state.step += 1;

        if(bullet == BulletType.LIVE) {
            state.isPlayerTurn = !state.isPlayerTurn;
            state.turn += 1;
            state.shotgun.isSawedOf = false;
        }

        return state;
    }

    @Override
    public String description() {
        String probability = String.format("%.0f%%", successProbability * 100d);
        return state.isPlayerTurn ?
                "player shots himself with a " + probability + " success probability." :
                "dealer shots himself with a " + probability + " success probability.";
    }
}
