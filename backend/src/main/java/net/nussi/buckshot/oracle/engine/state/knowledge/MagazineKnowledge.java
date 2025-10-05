package net.nussi.buckshot.oracle.engine.state.knowledge;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.state.BulletType;

@Slf4j
public class MagazineKnowledge extends Knowledge {
    public final int index;
    public final BulletType type;

    public MagazineKnowledge(int index, BulletType type) {
        this.index = index;
        this.type = type;
    }
}
