package com.forest.simulation.app;

import java.io.IOException;

import com.forest.simulation.config.ConfigLoader;
import com.forest.simulation.config.SimulationConfig;
import com.forest.simulation.model.CellState;
import com.forest.simulation.model.Forest;
import com.forest.simulation.model.Position;
import com.forest.simulation.service.FireSimulationService;

public class Main {
public static void main(String[] args) {
        try {
            ConfigLoader configLoader = new ConfigLoader();
            SimulationConfig config = configLoader.load("config.json");

            Forest forest = initializeForest(config);
            FireSimulationService simulationService = new FireSimulationService();

            int step = 0;
            System.out.println("État initial :");
            printForest(forest);

            while (forest.hasFire()) {
                forest = simulationService.nextStep(forest, config.getPropagationProbability());
                step++;
                System.out.println("Après l'étape " + step + " :");
                printForest(forest);
            }

            System.out.println("Simulation terminée en " + step + " étape(s).");

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier config.json : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Configuration invalide : " + e.getMessage());
        }
    }

    private static Forest initializeForest(SimulationConfig config) {
        validateConfig(config);

        Forest forest = new Forest(config.getHeight(), config.getWidth());

        for (Position position : config.getInitialFires()) {
            if (!forest.isInside(position.getRow(), position.getCol())) {
                throw new IllegalArgumentException(
                        "Position initiale hors de la grille : (" + position.getRow() + ", " + position.getCol() + ")"
                );
            }
            forest.setCell(position.getRow(), position.getCol(), CellState.FIRE);
        }

        return forest;
    }

    private static void validateConfig(SimulationConfig config) {
        if (config.getHeight() <= 0) {
            throw new IllegalArgumentException("La hauteur doit être strictement positive.");
        }

        if (config.getWidth() <= 0) {
            throw new IllegalArgumentException("La largeur doit être strictement positive.");
        }

        if (config.getPropagationProbability() < 0 || config.getPropagationProbability() > 1) {
            throw new IllegalArgumentException("La probabilité doit être comprise entre 0 et 1.");
        }

        if (config.getInitialFires() == null || config.getInitialFires().isEmpty()) {
            throw new IllegalArgumentException("Il faut au moins une case initialement en feu.");
        }
    }

    private static void printForest(Forest forest) {
        for (int row = 0; row < forest.getHeight(); row++) {
            for (int col = 0; col < forest.getWidth(); col++) {
                CellState state = forest.getCell(row, col);

                switch (state) {
                    case TREE -> System.out.print("T ");
                    case FIRE -> System.out.print("F ");
                    case ASH -> System.out.print("A ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
