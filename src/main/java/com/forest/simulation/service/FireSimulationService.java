package com.forest.simulation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.forest.simulation.model.CellState;
import com.forest.simulation.model.Forest;
import com.forest.simulation.model.Position;

public class FireSimulationService {
private final Random random;

    public FireSimulationService() {
        this.random = new Random();
    }

    // Constructeur utilisé notamment pour les tests (permet de contrôler l'aléatoire)
    public FireSimulationService(Random random) {
        this.random = random;
    }

    // Calcule l'état de la forêt à l'étape suivante
    public Forest nextStep(Forest currentForest, double propagationProbability) {
        
        // IMPORTANT : on travaille sur une copie pour éviter de modifier la grille en cours
        Forest nextForest = currentForest.copy();
        
        // Liste des nouvelles cases qui vont prendre feu
        List<Position> newFires = new ArrayList<>();

        // Parcours de toutes les cases de la forêt
        for (int row = 0; row < currentForest.getHeight(); row++) {
            for (int col = 0; col < currentForest.getWidth(); col++) {

                // Si la case est en feu
                if (currentForest.getCell(row, col) == CellState.FIRE) {
                    // Elle devient cendre à l'étape suivante
                    nextForest.setCell(row, col, CellState.ASH);

                    // On regarde ses 4 voisins
                    for (Position neighbor : getNeighbors(row, col)) {
                        int neighborRow = neighbor.getRow();
                        int neighborCol = neighbor.getCol();

                        // Vérifie :
                        // - que le voisin est dans la grille
                        // - que c'est un arbre
                        // - que la propagation réussit (probabilité)
                        if (currentForest.isInside(neighborRow, neighborCol)
                                && currentForest.getCell(neighborRow, neighborCol) == CellState.TREE
                                && random.nextDouble() < propagationProbability) {
                               
                                // On ajoute la case à la liste des nouveaux feux
                                newFires.add(neighbor);
                        }
                    }
                }
            }
        }

        // Application des nouveaux feux après le parcours
        for (Position newFire : newFires) {
            nextForest.setCell(newFire.getRow(), newFire.getCol(), CellState.FIRE);
        }

        return nextForest;
    }

    // Retourne les 4 cases adjacentes (haut, bas, gauche, droite)
    private List<Position> getNeighbors(int row, int col) {
        List<Position> neighbors = new ArrayList<>();
        neighbors.add(new Position(row - 1, col)); // haut
        neighbors.add(new Position(row + 1, col)); // bas
        neighbors.add(new Position(row, col - 1)); // gauche
        neighbors.add(new Position(row, col + 1)); // droite
        return neighbors;
    }
}
