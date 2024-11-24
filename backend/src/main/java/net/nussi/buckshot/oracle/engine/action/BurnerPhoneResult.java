package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.type.BulletType;

public class BurnerPhoneResult {
    public int position;
    public BulletType type;

    public BurnerPhoneResult(int position, BulletType type) {
        this.position = position;
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                ", type=" + type +
                '}';
    }
}
