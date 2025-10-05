package net.nussi.buckshot.oracle.engine;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.ShootGameAction;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.item.base.ItemType;
import net.nussi.buckshot.oracle.engine.state.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class GameEngine {
    public static final GameEngine INSTANCE = new GameEngine();

    public GameState createGame(
            int seed,
            List<String> player_names,
            int baseHealth,
            int liveRounds,
            int blankRounds
    ) {
        Random random = new Random(seed);

        List<PlayerState> players = new ArrayList<>();
        for (String playerName : player_names) {
            players.add(new PlayerState(
                    playerName,
                    baseHealth,
                    List.of(),
                    List.of(),
                    false
            ));
        }


        ShotgunState shotgunState = new ShotgunState(new MagazineState(liveRounds, blankRounds), 0, false);

        return new GameState(
                seed,
                shotgunState,
                players
        );
    }


    public List<GameAction> getActions(GameState gameState) {
        List<GameAction> actions = new ArrayList<>();

        for(int playerIndex = 0; playerIndex < gameState.players.size(); playerIndex++) {
            actions.add(new ShootGameAction(playerIndex));
        }

        // Player uses items
        for(ItemType item : gameState.currentPlayer().items) {
            List<GameAction> itemActions = item.getActions(gameState);
            actions.addAll(itemActions);
        }



        return actions;
    }
}










