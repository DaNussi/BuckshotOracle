package net.nussi.buckshot.oracle.service;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.item.BeerItem;
import net.nussi.buckshot.oracle.item.ShotgunItem;
import net.nussi.buckshot.oracle.state.GameState;
import net.nussi.buckshot.oracle.state.PlayerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InitService implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public GameEngine gameEngine;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("InitService INITIALIZING");

        ShotgunItem shotgun = new ShotgunItem(4,4);

        PlayerState dealerState = PlayerState.builder()
                .health(2)
                .items(List.of(
                        new BeerItem()
                ))
                .build();

        PlayerState playerState = PlayerState.builder()
                .health(2)
                .items(List.of(
                        new BeerItem()
                ))
                .build();

        GameState gameState = GameState.builder()
                .dealer(dealerState)
                .player(playerState)
                .shotgun(shotgun)
                .isPlayerTurn(true)
                .build();

        logger.info("Game: {}", gameState);


        while (!gameState.ended) {
            List<GameAction> actions = gameEngine.getActions(gameState);
            Collections.shuffle(actions);
            GameAction chosenAction = actions.getFirst();

            for (GameAction action : actions) {
                logger.info("|{}{}: {}", action == chosenAction ? '#' : ' ', action.getClass().getSimpleName(),action.description());
            }


            gameState = gameEngine.playAction(chosenAction);
            logger.info("Game: {}", gameState);
        }




    }
}
