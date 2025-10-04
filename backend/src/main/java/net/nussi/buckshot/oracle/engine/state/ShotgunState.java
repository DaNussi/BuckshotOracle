package net.nussi.buckshot.oracle.engine.state;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
@ToString
public class ShotgunState {

    public final List<BulletType> magazine;
    public final boolean isSawedOf;

    public ShotgunState(List<BulletType> magazine, boolean isSawedOf) {
        this.magazine = Collections.unmodifiableList(magazine);
        this.isSawedOf = isSawedOf;
    }
}
