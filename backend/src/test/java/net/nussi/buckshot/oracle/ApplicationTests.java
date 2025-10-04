package net.nussi.buckshot.oracle;

import net.nussi.buckshot.oracle.engine.GameEngine;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.state.GameState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private GameEngine gameEngine;

	@Test
	void contextLoads() {
	}

    @Test
    void game() {

        GameState gameState = gameEngine.createGame(1824971298, List.of("Sans", "Papyrus"), 3, 2, 2);

        List<GameAction> actions = gameEngine.getActions(gameState);


    }

}
