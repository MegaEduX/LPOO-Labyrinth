/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic;

import pt.up.fe.lpoo.logic.piece.itemizable.Blank;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;
import pt.up.fe.lpoo.logic.piece.itemizable.Hero;

import pt.up.fe.lpoo.logic.piece.*;

import java.util.Random;
import java.util.Vector;

public class Board {
    public enum Type {WALL, HERO, SWORD, DRAGON, MIXED_SD, EXIT, BLANK, LOCKED_WALL};
    public enum Direction {UP, LEFT, DOWN, RIGHT};
    public enum State {RUNNING, LOST, WON};

    private enum DragonFightResult {NOT_FOUND, ALREADY_DEFEATED, LOST, WON};
    private enum DragonSearchResult {NOT_FOUND, FOUND, ALREADY_DEFEATED};

    private State gameState = State.RUNNING;

    //  private Hero hero = new Hero();

    private Type boardRep[][] = {
            {Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL},
            {Type.WALL, Type.HERO, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.DRAGON, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL, Type.BLANK, Type.EXIT},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.SWORD, Type.WALL, Type.WALL, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL},
            {Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL}
    };

    private Vector<Piece> _boardPieces = new Vector<Piece>();

    private int width = 10;
    private int height = 10;

    public Coordinate getLocation(Type piece) throws Exception {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (boardRep[i][j] == piece)
                    return new Coordinate(j, i);

        throw new Exception("Piece Not Found.");
    }

    public Piece getPiece(Coordinate crd) throws Exception {
        for (Piece pc : _boardPieces)
            if (pc.getCoordinate().equals(crd))
                return pc;

        throw new Exception("404: Piece Not Found");
    }

    public Vector<Piece> getPiecesWithType(Type type) throws Exception {
        Vector<Piece> pcs = new Vector<Piece>();

        for (Piece pc : _boardPieces) {
            switch (type) {
                case WALL:

                    if (pc instanceof Wall)
                        pcs.add(pc);

                    break;

                case HERO:

                    if (pc instanceof Hero)
                        pcs.add(pc);

                    break;

                case SWORD:

                    if (pc instanceof Blank && ((Blank) pc).getHasItem())
                        pcs.add(pc);

                    break;

                case DRAGON:

                    if (pc instanceof Dragon)
                        pcs.add(pc);

                    break;

                case EXIT:

                    if (pc instanceof Blank && ((Blank) pc).getIsExit())
                        pcs.add(pc);

                    break;

                case BLANK:

                    if (pc instanceof Blank)
                        pcs.add(pc);

                    break;

                default:

                    throw new Exception("Undefined or Private type requested.");
            }
        }

        return pcs;
    }

    public void removePiece(Piece piece) throws Exception {
        _boardPieces.remove(piece);
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public State getGameState() {
        return gameState;
    }

    public void setBoardRepresentation(Type newRep[][]) {
        boardRep = newRep;
    }

    public void setBoardPieces(Vector<Piece> board) {
        _boardPieces = board;
    }

    public Vector<Piece> getBoardPieces() {
        return _boardPieces;
    }

    private Direction[] _getMovePossibilities(Type piece) throws Exception {
        Coordinate pieceLoc;

        Direction[] pb = new Direction[4];

        try {
            pieceLoc = getLocation(piece);
        } catch (Exception exc) {
            throw exc;
        }

        /*
         *  As far as I noticed, .length returns the length of the array, not the number of objects that currently
         *  reside in it (which was what I needed), so I had to do a small workaround involving an extra int and then
         *  creating another array with the correct length. Ugh!
         */

        int currIndex = 0;

        if (boardRep[pieceLoc.y][pieceLoc.x - 1] == Type.BLANK || boardRep[pieceLoc.y][pieceLoc.x - 1] == Type.SWORD)
            pb[currIndex++] = Direction.LEFT;

        if (boardRep[pieceLoc.y][pieceLoc.x + 1] == Type.BLANK || boardRep[pieceLoc.y][pieceLoc.x + 1] == Type.SWORD)
            pb[currIndex++] = Direction.RIGHT;

        if (boardRep[pieceLoc.y - 1][pieceLoc.x] == Type.BLANK || boardRep[pieceLoc.y - 1][pieceLoc.x] == Type.SWORD)
            pb[currIndex++] = Direction.UP;

        if (boardRep[pieceLoc.y + 1][pieceLoc.x] == Type.BLANK || boardRep[pieceLoc.y + 1][pieceLoc.x] == Type.SWORD)
            pb[currIndex++] = Direction.DOWN;

        Direction[] pbRet = new Direction[currIndex];

        for (int i = 0; i < currIndex; i++)
            pbRet[i] = pb[i];

        return pbRet;
    }

    private Vector<Direction> _getMovePossibilities(Piece pc) throws Exception {
        Vector<Direction> pb = new Vector<Direction>();

        try {
            getPiece(new Coordinate(pc.getCoordinate().x - 1, pc.getCoordinate().y));

            pb.add(Direction.LEFT);
        } catch (Exception e) {

        }

        try {
            getPiece(new Coordinate(pc.getCoordinate().x + 1, pc.getCoordinate().y));

            pb.add(Direction.RIGHT);
        } catch (Exception e) {

        }

        try {
            getPiece(new Coordinate(pc.getCoordinate().x, pc.getCoordinate().y - 1));

            pb.add(Direction.UP);
        } catch (Exception e) {

        }

        try {
            getPiece(new Coordinate(pc.getCoordinate().x, pc.getCoordinate().y + 1));

            pb.add(Direction.DOWN);
        } catch (Exception e) {

        }

        return pb;
    }

    public void moveDragon() throws Exception {
        Vector<Piece> dragons = getPiecesWithType(Type.DRAGON);

        for (Piece drag : dragons) {
            try {
                Vector<Direction> dir = _getMovePossibilities(drag);

                Random rand = new Random();

                int randVal = rand.nextInt(dir.size() + 1);

                if (dir.size() == randVal)
                    continue;

                drag.move(dir.get(randVal));
            } catch (Exception exc) {

            }
        }
    }

    private DragonSearchResult _isNearDragon() throws Exception {
        Dragon drag;

        try {
            drag = (Dragon) getPiecesWithType(Type.DRAGON).get(0);
        } catch (Exception exc) {
            return DragonSearchResult.ALREADY_DEFEATED;
        }

        Hero hero = (Hero) getPiecesWithType(Type.HERO).get(0);

        if (hero.getCoordinate().x == drag.getCoordinate().x)
            if (hero.getCoordinate().y == drag.getCoordinate().y || hero.getCoordinate().y - 1 == drag.getCoordinate().y || hero.getCoordinate().y + 1 == drag.getCoordinate().y)
                return DragonSearchResult.FOUND;

        if (hero.getCoordinate().y == drag.getCoordinate().y)
            if (hero.getCoordinate().x == drag.getCoordinate().x || hero.getCoordinate().x - 1 == drag.getCoordinate().x || hero.getCoordinate().x + 1 == drag.getCoordinate().x)
                return DragonSearchResult.FOUND;

        return DragonSearchResult.NOT_FOUND;
    }

    public DragonFightResult checkDragon() throws Exception {
        try {
            DragonSearchResult res = _isNearDragon();

            if (res == DragonSearchResult.ALREADY_DEFEATED)
                return DragonFightResult.ALREADY_DEFEATED;

            if (res == DragonSearchResult.NOT_FOUND)
                return DragonFightResult.NOT_FOUND;

        } catch (Exception exc) {
            throw exc;
        }

        Hero hero = (Hero) getPiecesWithType(Type.HERO).get(0);

        if (hero.getHasItem()) {
            Dragon drag = (Dragon) getPiecesWithType(Type.DRAGON).get(0);

            Blank bl = new Blank(drag.getCoordinate());

            _boardPieces.add(bl);

            removePiece(drag);

            return DragonFightResult.WON;
        }

        removePiece(hero);

        gameState = State.LOST;

        return DragonFightResult.LOST;
    }

    public void recheckGameState() throws Exception {
        Vector<Piece> blanks = getPiecesWithType(Type.BLANK);

        if (getPiecesWithType(Type.HERO).size() == 0) {
            gameState = State.LOST;

            return;
        }

        for (Piece bl : blanks)
            if (((Blank) bl).getIsExit()) {
                gameState = State.RUNNING;

                return;
            }

        gameState = State.WON;
    }
}
