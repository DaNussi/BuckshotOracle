package net.nussi.buckshot.oracle.service;

import net.nussi.buckshot.oracle.action.GameAction;
import net.nussi.buckshot.oracle.state.GameState;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OracleService {

    @Autowired
    public GameEngine gameEngine;

    public GameAction getBestAction(GameState gameState) {

        SimpleDirectedGraph<GameState, GameAction> graph = new SimpleDirectedGraph<>(GameAction.class);

        graph.addVertex(gameState);

        // TODO
        return gameEngine.getActions(gameState).getFirst();
    }

}
