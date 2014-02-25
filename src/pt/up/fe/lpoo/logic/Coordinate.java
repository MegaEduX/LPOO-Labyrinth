/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic;

public class Coordinate {
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
