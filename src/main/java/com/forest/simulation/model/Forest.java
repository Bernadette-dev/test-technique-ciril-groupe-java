package com.forest.simulation.model;

public class Forest {

    private final int height; // hauteur
    private final int width; // largeur
    private final CellState[][] grid; // grille

    public Forest(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new CellState[height][width];

        // Par défaut, toutes les cases de la forêt sont des arbres
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = CellState.TREE;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CellState getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, CellState state) {
        grid[row][col] = state;
    }

    // Vérifie si une position est à l'intérieur de la grille
    public boolean isInside(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    // Vérifie s'il reste au moins une case en feu dans la forêt
    public boolean hasFire() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == CellState.FIRE) {
                    return true;
                }
            }
        }
        return false;
    }

    // Crée une copie de la forêt pour calculer l'étape suivante sans modifier l'état actuel
    // IMPORTANT : permet d'éviter les effets de bord en ne modifiant pas la grille en cours
    public Forest copy() {
        Forest copy = new Forest(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                copy.setCell(i, j, this.getCell(i, j));
            }
        }
        return copy;
    }
}
