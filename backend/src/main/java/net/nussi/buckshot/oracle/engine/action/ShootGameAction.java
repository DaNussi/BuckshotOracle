package net.nussi.buckshot.oracle.engine.action;

import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.action.base.GameAction;
import net.nussi.buckshot.oracle.engine.state.*;
import net.nussi.buckshot.oracle.engine.state.knowledge.Knowledge;
import net.nussi.buckshot.oracle.engine.state.knowledge.MagazineKnowledge;

import java.util.HashMap;

@Slf4j
public class ShootGameAction extends GameAction {
    public final int targetPlayerIndex;

    public ShootGameAction(int targetPlayerIndex) {
        this.targetPlayerIndex = targetPlayerIndex;
    }

    @Override
    public GameStateSuperposition apply(GameState gameState) throws ImpossibleGameActionException {
        if (targetPlayerIndex >= gameState.players.size() || targetPlayerIndex < 0)
            throw new ImpossibleGameActionException("Player index of bounds", new IndexOutOfBoundsException(targetPlayerIndex));
        PlayerState targetPlayer = gameState.players.get(targetPlayerIndex);



        int potentialLiveRounds = gameState.shotgun.magazine.lives;
        int potentialBlankRounds = gameState.shotgun.magazine.blanks;

        int nextShotIndex = gameState.shotgun.shots;

        for (PlayerState playerState : gameState.players) {
            for (Knowledge knowledge : playerState.knowledgeList) {
                if (knowledge instanceof MagazineKnowledge k) {

                    if (k.index == nextShotIndex) {
                        potentialLiveRounds = k.type == BulletType.LIVE ? 1 : 0;
                        potentialBlankRounds = k.type == BulletType.BLANK ? 1 : 0;
                    } else if (k.index > nextShotIndex) {
                        if (k.type == BulletType.LIVE) {
                            if (potentialLiveRounds == 0)
                                throw new ImpossibleGameActionException("Contradictory knowledge about magazine");
                            potentialLiveRounds -= 1;
                        }
                        else {
                            if (potentialBlankRounds == 0)
                                throw new ImpossibleGameActionException("Contradictory knowledge about magazine");
                            potentialBlankRounds -= 1;
                        }
                    } else {
                        // Ignore old knowledge
                    }
                }
            }
        }

        HashMap<GameState, Double> probabilityMap = new HashMap<>();
        HashMap<GameState, String> descriptionMap = new HashMap<>();

        if (potentialLiveRounds > 0) {
            GameState liveWasShot = shoot(gameState, BulletType.LIVE);
            probabilityMap.put(liveWasShot, potentialLiveRounds * 1.0);
            descriptionMap.put(liveWasShot, "Player \""+ gameState.players.getFirst().name + "\" shoots \""+gameState.players.get(targetPlayerIndex).name+"\" with " + BulletType.LIVE + " bullet");
        }
        if (potentialBlankRounds > 0) {
            GameState blankWasShot = shoot(gameState, BulletType.BLANK);
            probabilityMap.put(blankWasShot, potentialBlankRounds * 1.0);
            descriptionMap.put(blankWasShot, "Player \""+ gameState.players.getFirst().name + "\" shoots \""+gameState.players.get(targetPlayerIndex).name+"\" with " + BulletType.BLANK + " bullet");
        }

        return new GameStateSuperposition(probabilityMap, descriptionMap);
    }

    @Override
    public String describe(GameState gameState) throws ImpossibleGameActionException {
        return "Player \""+ gameState.players.getFirst().name + "\" shoots \""+gameState.players.get(targetPlayerIndex).name+"\"";
    }

    public GameState shoot(GameState gameState, BulletType bulletType) {
        return gameState
                .modifyPlayer(targetPlayerIndex, (player) -> player
                        .modifyHealth(bulletType == BulletType.LIVE ? -1 : 0))
                .modifyShotgun((shotgun) -> shotgun
                        .modifyMagazine((magazine) -> new MagazineState(
                                magazine.lives - (bulletType == BulletType.LIVE ? 1 : 0),
                                magazine.blanks - (bulletType == BulletType.BLANK ? 1 : 0)
                        ))
                        .modifyShots((shots) -> shots + 1)
                );
    }

    @Override
    public String toString() {
        return "ShootGameAction{" +
                "targetPlayerIndex=" + targetPlayerIndex +
                '}';
    }
}
