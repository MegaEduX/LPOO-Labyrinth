/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo;

import java.util.Random;

public class BoardGenerator {
    Board.Type boardRep[][] = {
            {Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.WALL, Board.Type.LOCKED_WALL},
            {Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL, Board.Type.LOCKED_WALL}
    };

    public Board.Type[][] generateBoard() {
        Coordinate stp = _startingPoint();

        boardRep[stp.y][stp.x] = Board.Type.EXIT;

        //  Missing Lots...

        return boardRep;
    }

    /*  private Coordinate[] _possibleMoves(Coordinate pos) {
        Coordinate crds[9];

        if (boardRep[pos.y][pos.x + 1] == Board.Type.WALL) {
            if (boardRep[pos.y - 1][pos.x] == Board.Type.WALL && boardRep[pos.y + 1][pos.x] == Board.Type.WALL) {

            }
        }
    }   */

    private Coordinate _startingPoint() {
        //  Choosing the Column...

        Random rand = new Random();

        int val = rand.nextInt(36);

        if (val < 10)
            return new Coordinate(val, 0);  //  Top-Most Line
        else if (val < 18)
            return new Coordinate(9, val - 9);  //  Right-Most Column
        else if (val < 27)
            return new Coordinate(val - 18, 9); //  Bottom-Most Line
        else
            return new Coordinate(0, val - 27); //  Left-Most Column
    }
}
