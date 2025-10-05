package net.nussi.buckshot.oracle.engine.action;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.state.*;
import net.nussi.buckshot.oracle.engine.state.knowledge.MagazineKnowledge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ShootGameActionTest {

    public GameState originalGameState;

    @BeforeEach
    void setUp() {
        originalGameState = new GameState(
                123456789,
                new ShotgunState(
                        new MagazineState(
                                2,
                                2
                        ),
                        0,
                        false
                ),
                List.of(
                        new PlayerState(
                                "Sans",
                                3,
                                List.of(),
                                List.of(),
                                false
                        ),
                        new PlayerState(
                                "Papyrus",
                                3,
                                List.of(),
                                List.of(),
                                false
                        )
                )
        );
    }

    @Test
    void shoot() {


        GameState expectedGameState = new GameState(
                123456789,
                new ShotgunState(
                        new MagazineState(
                                1,
                                2
                        ),
                        1,
                        false
                ),
                List.of(
                        new PlayerState(
                                "Sans",
                                3,
                                List.of(),
                                List.of(),
                                false
                        ),
                        new PlayerState(
                                "Papyrus",
                                2,
                                List.of(),
                                List.of(),
                                false
                        )
                )
        );

        ShootGameAction gameAction = new ShootGameAction(1);
        GameState appliedGameState = gameAction.shoot(originalGameState, BulletType.LIVE);

        assertEquals(expectedGameState, appliedGameState);
    }

    @Test
    void shootWithKnowledge() {
        GameState modifiedGameState = originalGameState
                .modifyPlayer(0, (player) -> player
                        .addKnowledge(new MagazineKnowledge(1, BulletType.LIVE))
                        .addKnowledge(new MagazineKnowledge(2, BulletType.LIVE))
                );

        ShootGameAction gameAction = new ShootGameAction(1);
        GameStateSuperposition gameStateSuperposition = gameAction.apply(modifiedGameState);

        assertEquals(1, gameStateSuperposition.probabilityMap.size());
    }
}