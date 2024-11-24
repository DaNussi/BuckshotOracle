package net.nussi.buckshot.oracle.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import net.nussi.buckshot.oracle.item.BulletType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;

@AllArgsConstructor
@Builder
public class ShotgunState implements Serializable {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Builder.Default
    public List<BulletType> magazine = new ArrayList<>();

    @Builder.Default
    public boolean isSawedOf = false;

    public ShotgunState reload(int live, int blank) {
        for (int i = 0; i < live; i++) {
            magazine.add(BulletType.LIVE);
        }

        for (int i = 0; i < blank; i++) {
            magazine.add(BulletType.BLANK);
        }

        Collections.shuffle(magazine);
        return this;
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

    public int damage() {
        int damage = 0;
        if (magazine.getFirst() == BulletType.LIVE) damage += 1;
        if (isSawedOf) damage *= 2;
        return damage;
    }

    public BulletType eject() {
        return magazine.removeFirst();
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
        return getPattern() + (isSawedOf ? " SO" : "");
    }
}
