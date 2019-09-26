package com.chucknorris.mapa;

import com.chucknorris.commons.Position;

import java.util.ArrayList;

public class Response {
    public Node pos;
    public ArrayList<Position> availablePositions;
    public int movs;

    public Response(Node pos, ArrayList<Position> availablePositions, int movs) {
        super();
        this.pos = pos;
        this.availablePositions = availablePositions;
        this.movs = movs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((availablePositions == null) ? 0 : availablePositions.hashCode());
        result = prime * result + movs;
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
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
        Response other = (Response) obj;
        if (availablePositions == null) {
            if (other.availablePositions != null)
                return false;
        } else if (!availablePositions.equals(other.availablePositions))
            return false;
        if (movs != other.movs)
            return false;
        if (pos == null) {
            return other.pos == null;
        } else return pos.equals(other.pos);
    }


}
