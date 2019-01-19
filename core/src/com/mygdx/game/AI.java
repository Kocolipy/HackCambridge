package com.mygdx.game;

import io.improbable.keanu.algorithms.variational.optimizer.KeanuOptimizer;
import io.improbable.keanu.algorithms.variational.optimizer.Optimizer;
import io.improbable.keanu.network.BayesianNetwork;
import io.improbable.keanu.vertices.dbl.probabilistic.GaussianVertex;
import io.improbable.keanu.vertices.dbl.probabilistic.UniformVertex;

public class AI {

    public static void main(String[] args) {

        UniformVertex temperature = new UniformVertex(20., 30.);

        GaussianVertex firstThermometer = new GaussianVertex(temperature, 2.5);
        GaussianVertex secondThermometer = new GaussianVertex(temperature, 5.);

        firstThermometer.observe(25.);
        secondThermometer.observe(30.);

        BayesianNetwork bayesNet = new BayesianNetwork(temperature.getConnectedGraph());
        Optimizer optimizer = KeanuOptimizer.of(bayesNet);
        optimizer.maxAPosteriori();

        double calculatedTemperature = temperature.getValue().scalar();

        System.out.println("Calculated Room Temperature: " + calculatedTemperature);
    }
}