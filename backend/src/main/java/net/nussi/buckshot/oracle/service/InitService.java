package net.nussi.buckshot.oracle.service;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.action.GameActionResult;
import net.nussi.buckshot.oracle.item.ItemType;
import net.nussi.buckshot.oracle.state.GameState;
import net.nussi.buckshot.oracle.state.PlayerState;
import net.nussi.buckshot.oracle.state.ShotgunState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InitService implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public GameEngine gameEngine;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("InitService INITIALIZING");

        ShotgunState shotgun = ShotgunState.builder()
                .build()
                .reload(4,4);

        PlayerState dealerState = PlayerState.builder()
                .name("Dealer")
                .health(8)
                .build()
                .addItem(ItemType.BEER)
                .addItem(ItemType.EXPIRED_MEDICINE)
                .addItem(ItemType.INVERTER)
                .addItem(ItemType.HAND_SAW)
                .addItem(ItemType.ADRENALINE)
                .addItem(ItemType.CIGARETTE_PACK)
                .addItem(ItemType.MAGNIFYING_GLASS)
                .addItem(ItemType.BURNER_PHONE)
                .addItem(ItemType.HANDCUFFS);

        PlayerState playerState = PlayerState.builder()
                .name("Player")
                .health(8)
                .build()
                .addItem(ItemType.BEER)
                .addItem(ItemType.EXPIRED_MEDICINE)
                .addItem(ItemType.INVERTER)
                .addItem(ItemType.HAND_SAW)
                .addItem(ItemType.ADRENALINE)
                .addItem(ItemType.CIGARETTE_PACK)
                .addItem(ItemType.MAGNIFYING_GLASS)
                .addItem(ItemType.BURNER_PHONE)
                .addItem(ItemType.HANDCUFFS);

        GameState gameState = GameState.builder()
                .current(playerState)
                .opponent(dealerState)
                .shotgun(shotgun)
                .build();

        logger.info("Game: {}", gameState);

        Random random = new Random(gameState.seed);

        while (!gameState.ended) {
            List<GameAction> actions = gameEngine.getActions(gameState);
            GameAction chosenAction = actions.get(random.nextInt(actions.size()));

            for (GameAction action : actions) {
                logger.info("{} {}: {}", action == chosenAction ? '#' : ' ', action.getClass().getSimpleName(),action.description());
            }

            List<GameActionResult> results = gameEngine.executeAction(gameState, chosenAction);
            GameActionResult chosenResult = results.getFirst();

            for (GameActionResult result : results) {
                logger.info("{} Result: \"{}\" Data: {} Probability: {}", result == chosenResult ? '#' : ' ', result.message, result.data, String.format("%.2f%%",result.probability * 100d));
            }

            gameState = chosenResult.state;
            logger.info("Game: {}", gameState);
        }




    }
}
