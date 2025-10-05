package net.nussi.buckshot.oracle.engine.state;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.nussi.buckshot.oracle.engine.item.base.ItemType;
import net.nussi.buckshot.oracle.engine.state.knowledge.Knowledge;
import net.nussi.buckshot.oracle.engine.utility.Modify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@ToString
@EqualsAndHashCode
public class PlayerState {

    public final String name;
    public final int health;
    public final List<ItemType> items;
    public final List<Knowledge> knowledgeList;
    public final boolean isHandcuffed;

    public PlayerState(String name, int health, List<ItemType> items, List<Knowledge> knowledgeList, boolean isHandcuffed) {
        this.name = name;
        this.health = health;
        this.items = Collections.unmodifiableList(items);
        this.knowledgeList = Collections.unmodifiableList(knowledgeList);
        this.isHandcuffed = isHandcuffed;
    }

    public PlayerState modifyHealth(int amount) {
        return new PlayerState(name, health + amount, items, knowledgeList, isHandcuffed);
    }

    public PlayerState modifyKnowledgeIterator(Modify<Knowledge> modification) {
        PlayerState playerState = new PlayerState(name, health, items, knowledgeList, isHandcuffed);
        for(int i = 0; i < knowledgeList.size(); i++) {
            playerState = playerState.modifyKnowledge(i, modification);
        }
        return playerState;
    }

    public PlayerState modifyKnowledge(int knowledgeIndex, Modify<Knowledge> modification) {
        List<Knowledge> modifiedKnowledgeList = new ArrayList<>(knowledgeList);
        Knowledge modifiedKnowledge = modification.modify(knowledgeList.get(knowledgeIndex));
        if(modifiedKnowledge == null) {
            modifiedKnowledgeList.remove(knowledgeIndex);
        } else {
            modifiedKnowledgeList.set(knowledgeIndex, modification.modify(knowledgeList.get(knowledgeIndex)));
        }
        return new PlayerState(name, health, items, modifiedKnowledgeList, isHandcuffed);
    }

    public PlayerState addKnowledge(Knowledge knowledge) {
        List<Knowledge> modifiedKnowledgeList = new ArrayList<>(this.knowledgeList);
        modifiedKnowledgeList.add(knowledge);
        return new PlayerState(name, health, items, modifiedKnowledgeList, isHandcuffed);
    }

}


