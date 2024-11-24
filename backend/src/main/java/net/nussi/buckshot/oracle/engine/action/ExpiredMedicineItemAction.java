package net.nussi.buckshot.oracle.engine.action;

import net.nussi.buckshot.oracle.engine.GameAction;
import net.nussi.buckshot.oracle.engine.GameActionResult;
import net.nussi.buckshot.oracle.engine.state.GameState;
import net.nussi.buckshot.oracle.engine.type.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExpiredMedicineItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {

        state.current.items.remove(ItemType.EXPIRED_MEDICINE);

        GameState stateSuccess = state.clone();
        GameState stateFail = state.clone();

        stateSuccess.current.health += 2;
        stateFail.current.health -= 1;

        return List.of(
                new GameActionResult(stateSuccess, true, 0.5, state.current.name + " took expired medicine and gained 2hp."),
                new GameActionResult(stateFail, false, 0.5, state.current.name + " took expired medicine and lost 1hp.")
        );
    }

    @Override
    public String description() {
        return "Eject a bullet from the first chamber.";
    }
}
