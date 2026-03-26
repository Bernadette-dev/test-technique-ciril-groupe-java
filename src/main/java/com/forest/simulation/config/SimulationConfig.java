package com.forest.simulation.config;

import java.util.List;

import com.forest.simulation.model.Position;

public class SimulationConfig {

    private int height;
    private int width;
    private double propagationProbability;
    private List<Position> initialFires;

    public SimulationConfig() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getPropagationProbability() {
        return propagationProbability;
    }

    public void setPropagationProbability(double propagationProbability) {
        this.propagationProbability = propagationProbability;
    }

    public List<Position> getInitialFires() {
        return initialFires;
    }

    public void setInitialFires(List<Position> initialFires) {
        this.initialFires = initialFires;
    }
}
