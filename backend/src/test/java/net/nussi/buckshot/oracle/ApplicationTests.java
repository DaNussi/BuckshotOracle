package net.nussi.buckshot.oracle;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.GameEngine;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.state.BulletType;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.state.GameStateSuperposition;
import net.nussi.buckshot.oracle.engine.state.knowledge.MagazineKnowledge;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@Slf4j
class ApplicationTests {
    public GameEngine gameEngine;

    @BeforeEach
    void setup() {
        gameEngine = GameEngine.INSTANCE;
    }


	@Test
	void contextLoads() {
	}

    @Test
    void game() {

        GameState gameState = gameEngine.createGame(1824971298, List.of("Sans", "Papyrus"), 3, 2, 2)
                        .modifyPlayer(0, (player) -> player
                                .addKnowledge(new MagazineKnowledge(1, BulletType.LIVE))
                        );
        log.info("Game State: {}", gameState);

        List<GameAction> actions = gameEngine.getActions(gameState);
        log.info("Actions: {}", actions.size());

        for (int i = 0; i < actions.size(); i++) {
            GameAction action = actions.get(i);
            log.info("Action[{}]: {}", i, action);
            GameStateSuperposition gameStateSuperposition = action.apply(gameState);
            for(GameState probableGameState : gameStateSuperposition.probabilityMap.keySet()) {
                double probability = gameStateSuperposition.probabilityMap.get(probableGameState);
                String description = gameStateSuperposition.desctiptionMap.get(probableGameState);

                log.debug("Probability[{}%]: {}", String.format("%.02f",probability), description);
            }
        }

    }

}
