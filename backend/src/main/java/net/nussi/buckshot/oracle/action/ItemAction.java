package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.GameItem;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemAction extends GameAction{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public GameItem item;
    public GameAction action;

    public ItemAction(GameState state, GameItem item, GameAction action) {
        super(state);
        this.item = item;
        this.action = action;
    }

    @Override
    public GameState execute() {
        if(state.isPlayerTurn) {
            state.player.items = state.player.items.stream().filter(item -> item.uuid != this.item.uuid).toList();
            logger.info("Removed {} from player.", item);
        } else {
            state.dealer.items = state.dealer.items.stream().filter(item -> item.uuid != this.item.uuid).toList();
            logger.info("Removed {} from dealer.", item);
        }

        this.action.state = state;

        return this.action.execute();
    }

    @Override
    public String description() {
        return action.description();
    }
}
