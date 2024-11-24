package net.nussi.buckshot.oracle.service;

import net.nussi.buckshot.oracle.action.*;
import net.nussi.buckshot.oracle.item.*;
import net.nussi.buckshot.oracle.state.GameState;
import net.nussi.buckshot.oracle.state.PlayerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class GameEngine implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BeerItemHandler beerItemHandler;

    @Autowired
    private InverterItemHandler inverterItemHandler;

    @Autowired
    private ExpiredMedicineItemHandler expiredMedicineItemHandler;

    @Autowired
    private AdrenalineItemHandler adrenalineItemHandler;

    @Autowired
    private HandSawItemHandler handSawItemHandler;

    @Autowired
    private CigarettePackItemHandler cigarettePackItemHandler;

    @Autowired
    private HandcuffsItemHandler handcuffsItemHandler;

    @Autowired
    private MagnifyingGlassItemHandler magnifyingGlassItemHandler;

    @Autowired
    private BurnerPhoneItemHandler burnerPhoneItemHandler;

    private List<ItemHandler> itemHandlers = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        itemHandlers.add(beerItemHandler);
        itemHandlers.add(inverterItemHandler);
        itemHandlers.add(expiredMedicineItemHandler);
        itemHandlers.add(adrenalineItemHandler);
        itemHandlers.add(handSawItemHandler);
        itemHandlers.add(cigarettePackItemHandler);
        itemHandlers.add(handcuffsItemHandler);
        itemHandlers.add(magnifyingGlassItemHandler);
        itemHandlers.add(burnerPhoneItemHandler);
    }

    public List<GameAction> getActions(GameState state) {
        List<GameAction> allActions = new ArrayList<>();

        for (var item : new HashSet<>(state.current.items)) {
            Optional<ItemHandler> optionalItemHandler = itemHandlers.stream().filter(e -> e.getType() == item).findFirst();
            if(optionalItemHandler.isEmpty()) {
                logger.error("Missing item handler for type {}.", item);
                continue;
            }
            ItemHandler itemHandler = optionalItemHandler.get();
            List<GameAction> itemActions = itemHandler.getActions(state);
            allActions.addAll(itemActions);
        }

        allActions.add(new ShotSelfAction());
        allActions.add(new ShotOpponentAction());

        return allActions;
    }

    public List<GameActionResult> executeAction(GameState state, GameAction action) throws Exception {

        List<GameActionResult> results = action.execute(state);

        results.forEach(result -> {
            if(result.state.current.health <= 0) result.state.ended = true;
            if(result.state.opponent.health <= 0) result.state.ended = true;

            if(result.state.shotgun.magazine.size() <= 0) result.state.shotgun.reload(4,4);
        });

        return results;
    }

    public static void endTurn(GameState state) {
        state.turn += 1;
        PlayerState temp = state.opponent;
        state.opponent = state.current;
        state.current = temp;
        state.shotgun.isSawedOf=false;

        if(state.current.isHandcuffed) {
            state.current.isHandcuffed = false;
            endTurn(state);
        }
    }


}
