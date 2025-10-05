package net.nussi.buckshot.oracle.engine.state;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@EqualsAndHashCode
public class MagazineState {
    public final int lives;
    public final int blanks;

    public MagazineState(int lives, int blanks) {
        this.lives = lives;
        this.blanks = blanks;
    }
}
