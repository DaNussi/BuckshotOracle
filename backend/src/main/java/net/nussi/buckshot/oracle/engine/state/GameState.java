package net.nussi.buckshot.oracle.engine.state;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.item.base.ItemType;
import net.nussi.buckshot.oracle.engine.state.knowledge.Knowledge;
import net.nussi.buckshot.oracle.engine.utility.Modify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@ToString
@EqualsAndHashCode
public class GameState {
    
    public final long seed;
    public final ShotgunState shotgun;
    public final List<PlayerState> players;

    public GameState(long seed, ShotgunState shotgun, List<PlayerState> players) {
        this.seed = seed;
        this.shotgun = shotgun;
        this.players = Collections.unmodifiableList(players);
    }

    public PlayerState currentPlayer() {
        return players.getFirst();
    }


    public GameState modifyPlayer(int playerIndex, Modify<PlayerState> modification) {
        PlayerState originalPlayerState = players.get(playerIndex);
        PlayerState modifiedPlayerState = modification.modify(originalPlayerState);

        return modifyPlayers((list) -> {
           list.set(playerIndex, modifiedPlayerState);
           return list;
        });
    }

    public GameState modifyPlayers(Modify<List<PlayerState>> modification) {
        List<PlayerState> originalPlayers = new ArrayList<>(players);
        List<PlayerState> modifiedPlayers = modification.modify(originalPlayers);
        return new GameState(seed, shotgun, modifiedPlayers);
    }

    public GameState modifyPlayersIterator(Modify<PlayerState> modification) {
        GameState gameState = new GameState(seed, shotgun, players);
        for (int index = 0; index < players.size(); index++) {
            gameState = gameState.modifyPlayer(index, modification);
        }
        return gameState;
    }

    public GameState modifyShotgun(Modify<ShotgunState> modification) {
        ShotgunState modifiedShotgun = modification.modify(shotgun);
        return new GameState(seed, modifiedShotgun, players);
    }

}
