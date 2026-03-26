package com.forest.simulation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import com.forest.simulation.model.CellState;
import com.forest.simulation.model.Forest;

class FireSimulationServiceTest {
@Test
    void shouldTurnFireIntoAsh() {
        FireSimulationService service = new FireSimulationService(new Random(1));
        Forest forest = new Forest(3, 3);

        // On met une case en feu
        forest.setCell(1, 1, CellState.FIRE);

        Forest next = service.nextStep(forest, 0.0);

        // Vérifie que la case est devenue cendre
        assertEquals(CellState.ASH, next.getCell(1, 1));
    }

    @Test
    void shouldPropagateFireWhenProbabilityIsOne() {
        FireSimulationService service = new FireSimulationService(new Random(1));
        Forest forest = new Forest(3, 3);

        // feu au centre
        forest.setCell(1, 1, CellState.FIRE);

        Forest next = service.nextStep(forest, 1.0);

        // tous les voisins doivent brûler
        assertEquals(CellState.FIRE, next.getCell(0, 1));
        assertEquals(CellState.FIRE, next.getCell(2, 1));
        assertEquals(CellState.FIRE, next.getCell(1, 0));
        assertEquals(CellState.FIRE, next.getCell(1, 2));
    }
}
