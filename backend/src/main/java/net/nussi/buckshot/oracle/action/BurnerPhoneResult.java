package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;

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
