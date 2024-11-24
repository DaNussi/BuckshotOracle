package net.nussi.buckshot.oracle.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import net.nussi.buckshot.oracle.item.ItemType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class PlayerState implements Serializable {

    public String name;

    public int health;

    @Builder.Default
    public List<ItemType> items = new ArrayList<>();

    @Builder.Default
    public boolean isHandcuffed = false;

    public PlayerState addItem(ItemType item) {
        items.add(item);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(name).append(" ");
        builder.append(health).append("hp");
        if(items.size() > 0) {
            builder.append(" ");
            builder.append("items:");
            for(var item : items) {
                builder.append(" ");
                builder.append(item.toString());
            }
        }

        if(isHandcuffed) {
            builder.append(" ");
            builder.append("handcuffed");
        }

        builder.append("}");


        return builder.toString();
    }
}


