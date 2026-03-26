package com.forest.simulation.model;

import java.util.Objects;

public class Position {

    private int row; // ligne
    private int col; // colonne
    
    public Position() {
    }
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    
}
