package net.nussi.buckshot.oracle.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.item.ShotgunItem;

import java.io.Serializable;

@AllArgsConstructor
@Builder
public class GameState implements Serializable {

    @Builder.Default
    public int step = 0;
    @Builder.Default
    public int turn = 0;

    @Builder.Default
    public boolean ended = false;

    public PlayerState dealer;
    public PlayerState player;
    public ShotgunItem shotgun;

    @Builder.Default
    public boolean isPlayerTurn = true;

    @Override
    public String toString() {
        return "GameState{" +
                "step=" + step +
                ", turn=" + turn +
                ", ended=" + ended +
                ", dealer=" + dealer +
                ", player=" + player +
                ", shotgun=" + shotgun +
                ", isPlayerTurn=" + isPlayerTurn +
                '}';
    }
}
