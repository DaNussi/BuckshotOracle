package net.nussi.buckshot.oracle.engine;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.item.base.ItemType;
import net.nussi.buckshot.oracle.engine.state.BulletType;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.state.PlayerState;
import net.nussi.buckshot.oracle.engine.state.ShotgunState;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class GameEngine implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public GameState createGame(
            int seed,
            List<String> player_names,
            int baseHealth,
            int liveRounds,
            int blankRound
    ) {
        Random random = new Random(seed);

        List<PlayerState> players = new ArrayList<>();
        for (String playerName : player_names) {
            players.add(new PlayerState(
                    playerName,
                    baseHealth,
                    List.of()
            ));
        }

        List<BulletType> magazine = new ArrayList<>();
        for (int i = 0; i < blankRound; i++) magazine.add(BulletType.BLANK);
        for (int i = 0; i < liveRounds; i++) magazine.add(BulletType.LIVE);
        Collections.shuffle(magazine, random);
        ShotgunState shotgunState = new ShotgunState(magazine, false);

        return new GameState(
                seed,
                shotgunState,
                players
        );
    }


    public List<GameAction> getActions(GameState gameState) {
        List<GameAction> actions = new ArrayList<>();

        // Player uses items
        for(ItemType item : gameState.currentPlayer().items) {
            List<GameAction> itemActions = item.getActions(gameState);
            actions.addAll(itemActions);
        }



        return actions;
    }
}










