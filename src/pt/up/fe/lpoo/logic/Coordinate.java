/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and Joao Almeida.
 */

package pt.up.fe.lpoo.logic;

import java.io.Serializable;

public class Coordinate implements Serializable {
    public int x;
    public int y;

    public Coordinate(int xCrd, int yCrd) {
        x = xCrd;
        y = yCrd;
    }

    public boolean equals(Coordinate o) {
        return (x == o.x && y == o.y);
    }
}
