package net.nussi.buckshot.oracle.engine.state;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.item.base.ItemType;

import java.util.Collections;
import java.util.List;

@Slf4j
@ToString
public class PlayerState {

    public final String name;
    public final int health;
    public final List<ItemType> items;
    public final boolean isHandcuffed = false;

    public PlayerState(String name, int health, List<ItemType> items) {
        this.name = name;
        this.health = health;
        this.items = Collections.unmodifiableList(items);
    }
}


