package net.nussi.buckshot.oracle.service;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.item.GameItem;
import net.nussi.buckshot.oracle.item.ShotgunItem;
import net.nussi.buckshot.oracle.state.GameState;
import net.nussi.buckshot.oracle.state.PlayerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameEngine {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<GameAction> getActions(GameState state) {
        List<GameAction> actions = new ArrayList<>();

        if(state.isPlayerTurn) {
            List<GameAction> itemActions = state.player.items.stream()
                    .map(item -> item.getActions(state))
                    .flatMap(Collection::stream)
                    .toList();
            actions.addAll(itemActions);
        } else {
            List<GameAction> itemActions = state.dealer.items.stream()
                    .map(item -> item.getActions(state))
                    .flatMap(Collection::stream)
                    .toList();
            actions.addAll(itemActions);
        }

        List<GameAction> shotgunActions = state.shotgun.getActions(state);
        actions.addAll(shotgunActions);

        return actions;
    }

    public GameState playAction(GameAction action) {
        GameState state = action.execute();

        if(state.dealer.health <= 0) state.ended = true;
        if(state.player.health <= 0) state.ended = true;
        if(state.ended) logger.info("Game ended.");

        if(state.shotgun.countTotalRounds() <= 0) {
            state.shotgun = new ShotgunItem(4,4);
            logger.info("Reloaded shotgun.");
        }

        return state;
    }




}
