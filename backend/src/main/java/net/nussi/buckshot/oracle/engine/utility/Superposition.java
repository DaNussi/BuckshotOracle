package net.nussi.buckshot.oracle.engine.utility;

import net.nussi.buckshot.oracle.engine.state.GameState;

import java.util.*;

public class Superposition<T> {

    private final NavigableMap<Double, T> superposition;
    private final double probabilitySum;
    public final Map<T, Double> probabilityMap;

    public Superposition(HashMap<T, Double> probabilityMap) {
        this.superposition = new TreeMap<>();

        double probabilitySum = 0;
        for(T key : probabilityMap.keySet()) {
            double probability = probabilityMap.get(key);
            if(probability <= 0) continue;
            probabilitySum += probability;
            this.superposition.put(probabilitySum, key);
        }
        this.probabilitySum = probabilitySum;

        HashMap<T, Double> normalizedProbabilityMap = new HashMap<>();
        for(T key : probabilityMap.keySet()) {
            normalizedProbabilityMap.put(key, probabilityMap.get(key) / probabilitySum);
        }

        this.probabilityMap = Collections.unmodifiableMap(normalizedProbabilityMap);
    }

    public T collapse(Random random) {
        double value = random.nextDouble() * probabilitySum;
        return superposition.higherEntry(value).getValue();
    }

}
