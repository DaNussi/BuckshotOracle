package net.nussi.buckshot.oracle.action;

import net.nussi.buckshot.oracle.item.BulletType;
import net.nussi.buckshot.oracle.item.ItemType;
import net.nussi.buckshot.oracle.state.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BurnerPhoneItemAction extends GameAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameActionResult> innerExecute(GameState state) throws Exception {
        state.current.items.remove(ItemType.BURNER_PHONE);

        List<GameActionResult> results = new ArrayList<>();

        int positions = state.shotgun.magazine.size();
        for (int position = 0; position < positions; position++) {
            GameState state1 = state.clone();

            BulletType bullet = state.shotgun.magazine.get(position);

            results.add(new GameActionResult(
                    state1,
                    new BurnerPhoneResult(position, bullet),
                    1.0 / positions,
                    state.current.name + " used a burner phone and saw a "+bullet+" round in the "+position+" position."
            ));
        }

        return results;
    }

    @Override
    public String description() {
        return "Allows the user to see a random bullet in the chamber.";
    }
}
