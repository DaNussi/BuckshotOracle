package net.nussi.buckshot.oracle.item;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.action.ShotOpponentAction;
import net.nussi.buckshot.oracle.action.ShotSelfAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;

public class ShotgunItem extends GameItem implements Serializable {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Stack<BulletType> magazine = new Stack<>();
    public boolean isSawedOf = false;

    public ShotgunItem(int live, int blank) {
        for (int i = 0; i < live; i++) {
            magazine.add(BulletType.LIVE);
        }

        for (int i = 0; i < blank; i++) {
            magazine.add(BulletType.BLANK);
        }

        Collections.shuffle(magazine);

        String pattern = getPattern();

        logger.info("Created SHOTGUN with {} live and {} blank rounds, with following pattern: {}", live, blank, pattern);
    }

    @Override
    public List<GameAction> getActions(GameState state) {
        return List.of(
                new ShotOpponentAction(state),
                new ShotSelfAction(state)
        );
    }


    public long countLiveRounds() {
        return magazine.stream()
                .filter(round -> round == BulletType.LIVE)
                .count();
    }

    public long countBlankRounds() {
        return magazine.stream()
                .filter(round -> round == BulletType.BLANK)
                .count();
    }

    public long countTotalRounds() {
        return magazine.size();
    }

    public static int calculateDamage(BulletType bullet, boolean isSawedOf) {
        int damage = 0;
        if (bullet == BulletType.LIVE) damage += 1;
        if (isSawedOf) damage *= 2;
        return damage;
    }

    private String getPattern() {
        return magazine.stream()
                .map(bullet -> bullet == BulletType.LIVE ? 'L' : 'B')
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        if(countTotalRounds() > 0) {
            builder.append(getPattern());
        }
        if(isSawedOf) {
            builder.append(" ");
            builder.append("SO");
        }
        builder.append("}");

        return builder.toString();
    }
}
