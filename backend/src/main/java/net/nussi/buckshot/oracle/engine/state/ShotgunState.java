package net.nussi.buckshot.oracle.engine.state;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.state.knowledge.MagazineKnowledge;
import net.nussi.buckshot.oracle.engine.utility.Modify;

import java.util.Collections;
import java.util.List;

@Slf4j
@ToString
@EqualsAndHashCode
public class ShotgunState {

    public final MagazineState magazine;
    public final int shots;
    public final boolean isSawedOf;

    public ShotgunState(MagazineState magazine, int shots, boolean isSawedOf) {
        this.magazine = magazine;
        this.shots = shots;
        this.isSawedOf = isSawedOf;
    }

    public ShotgunState modifyMagazine(Modify<MagazineState> modification) {
        return new ShotgunState(modification.modify(magazine), shots, isSawedOf);
    }

    public ShotgunState modifyShots(Modify<Integer> modification) {
        return new ShotgunState(magazine, modification.modify(shots), isSawedOf);
    }
}


