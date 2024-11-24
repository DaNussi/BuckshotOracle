package net.nussi.buckshot.oracle.state;

import lombok.*;
import net.nussi.buckshot.oracle.item.GameItem;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
public class PlayerState implements Serializable {


    public int health;

    @Builder.Default
    public List<GameItem> items = List.of();

    @Builder.Default
    public boolean isHandcuffed = false;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(health).append("hp");
        if(items.size() > 0) {
            builder.append(" ");
            builder.append("items:");
            for(var item : items) {
                builder.append(" ");
                builder.append(item.getClass().getSimpleName());
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


