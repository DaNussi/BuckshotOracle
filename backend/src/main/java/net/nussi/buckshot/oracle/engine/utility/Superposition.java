package net.nussi.buckshot.oracle.engine.utility;

import java.util.HashMap;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class Superposition<T> {

    private final NavigableMap<Double, T> superposition;
    private final double probabilitySum;

    public Superposition(HashMap<T, Double> superposition) {
        this.superposition = new TreeMap<>();

        double probabilitySum = 0;
        for(T key : superposition.keySet()) {
            double probability = superposition.get(key);
            if(probability <= 0) continue;
            probabilitySum += probability;
            this.superposition.put(probabilitySum, key);
        }
        this.probabilitySum = probabilitySum;
    }

    public T collapse(Random random) {
        double value = random.nextDouble() * probabilitySum;
        return superposition.higherEntry(value).getValue();
    }

}
