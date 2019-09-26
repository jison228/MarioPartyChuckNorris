package com.chucknorris.commons;

public class Position {
    private int posX;
    private int posY;

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + posX;
        result = prime * result + posY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (posX != other.posX)
            return false;
        return posY == other.posY;
    }

    public void setPos(Position pos) {
        this.posX = pos.posX;
        this.posY = pos.posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }
}
