/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo;

import java.util.Random;
import java.util.Stack;

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

    public Board.Type[][] generateBoard() throws Exception {
        Coordinate stp;

        try {
            stp = _startingPoint();
        } catch (Exception exc) {
            throw exc;
        }

        boardRep[stp.y][stp.x] = Board.Type.EXIT;

        Coordinate position = new Coordinate(stp.x, stp.y);

        Stack<Coordinate> crdHistory = new Stack<Coordinate>();

        while (true) {
            Coordinate[] moves = _possibleMoves(position);

            Random rand = new Random();

            if (moves.length == 0) {
                if (crdHistory.size() == 0)
                    break;

                position = crdHistory.pop();

                continue;
            }

            int val = rand.nextInt(moves.length);

            try {
                boardRep[moves[val].y][moves[val].x] = Board.Type.BLANK;
            } catch (Exception exc) {
                break;
            }

            position = moves[val];

            crdHistory.push(position);
        }

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (boardRep[i][j] == Board.Type.LOCKED_WALL)
                    boardRep[i][j] = Board.Type.WALL;

        _fillWithCharacters();

        return boardRep;
    }

    private void _fillWithCharacters() {
        Coordinate[] whiteSpaces = _getWhitespaces();

        Random rand = new Random();

        int val = rand.nextInt(whiteSpaces.length);
        int val2 = rand.nextInt(whiteSpaces.length);
        int val3 = rand.nextInt(whiteSpaces.length);

        while (val == val2)
            val2 = rand.nextInt(whiteSpaces.length);

        while (val == val3 || val2 == val3)
            val3 = rand.nextInt(whiteSpaces.length);

        boardRep[whiteSpaces[val].y][whiteSpaces[val].x] = Board.Type.DRAGON;
        boardRep[whiteSpaces[val2].y][whiteSpaces[val2].x] = Board.Type.HERO;
        boardRep[whiteSpaces[val3].y][whiteSpaces[val3].x] = Board.Type.SWORD;
    }

    private Coordinate[] _getWhitespaces() {
        Coordinate coords[] = new Coordinate[100];

        int currIndex = 0;

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (boardRep[i][j] == Board.Type.BLANK)
                    coords[currIndex++] = new Coordinate(j, i);

        Coordinate crdRet[] = new Coordinate[currIndex];

        for (int i = 0; i < currIndex; i++)
            crdRet[i] = coords[i];

        return crdRet;
    }

    private Coordinate[] _possibleMoves(Coordinate pos) {
        Coordinate[] crds = new Coordinate[4];

        int ccIndex = 0;

        //  Move Up

        try {
            if (boardRep[pos.y - 1][pos.x] == Board.Type.WALL)
                crds[ccIndex++] = new Coordinate(pos.x, pos.y - 1);
        } catch (Exception exc) {

        }

        //  Move Down

        try {
            if (boardRep[pos.y + 1][pos.x] == Board.Type.WALL)
                crds[ccIndex++] = new Coordinate(pos.x, pos.y + 1);
        } catch (Exception exc) {

        }

        //  Move Right

        try {
            if (boardRep[pos.y][pos.x + 1] == Board.Type.WALL)
                crds[ccIndex++] = new Coordinate(pos.x + 1, pos.y);
        } catch (Exception exc) {

        }

        //  Move Left

        try {
            if (boardRep[pos.y][pos.x - 1] == Board.Type.WALL)
                crds[ccIndex++] = new Coordinate(pos.x - 1, pos.y);
        } catch (Exception exc) {

        }

        Coordinate[] crdRet = new Coordinate[ccIndex];

        for (int i = 0; i < ccIndex; crdRet[i] = crds[i++]) {

        }

        return crdRet;
    }

    private Coordinate _startingPoint() throws Exception {
        //  Choosing the Column...

        Random rand = new Random();

        int val = rand.nextInt(4);
        int index = rand.nextInt(8) + 1;

        switch (val) {
            case 0:  //   Top-Most Line

                return new Coordinate(val, 0);

            case 1: //  Right-Most Line

                return new Coordinate(9, val);

            case 2:

                return new Coordinate(val, 9);

            case 3:

                return new Coordinate(0, val);
        }

        throw new Exception("Say What?!");
    }
}
