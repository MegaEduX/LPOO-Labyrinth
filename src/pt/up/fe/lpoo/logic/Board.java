/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and Joao Almeida.
 */

package pt.up.fe.lpoo.logic;

import pt.up.fe.lpoo.logic.piece.Piece;
import pt.up.fe.lpoo.logic.piece.Wall;
import pt.up.fe.lpoo.logic.piece.itemizable.Blank;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;
import pt.up.fe.lpoo.logic.piece.itemizable.Eagle;
import pt.up.fe.lpoo.logic.piece.itemizable.Hero;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;

public class Board implements Serializable {
    /**
     * Available Piece types.
     */
    public enum Type {WALL, HERO, SWORD, DRAGON, MIXED_SD, EXIT, BLANK, LOCKED_WALL, EAGLE};

    /**
     * Directions.
     */

    public enum Direction {UP, LEFT, DOWN, RIGHT};

    /**
     * Game States.
     */

    public enum State {RUNNING, LOST, WON};

    /**
     * Outcomes of a dragon fight.
     */

    public enum DragonFightResult {NOT_FOUND, ALREADY_DEFEATED, LOST, WON};

    /**
     * Outcomes of a dragon search.
     */

    public enum DragonSearchResult {NOT_FOUND, FOUND, ALREADY_DEFEATED};

    private State gameState = State.RUNNING;

    private ArrayList<Piece> _boardPieces = new ArrayList<Piece>();

    private int width = 10;
    private int height = 10;
    
    private Eagle _eagle = null;

    /**
     * Associates an eagle with the board.
     *
     * @param eagle The eagle to associate with the board.
     */

    public void setEagle(Eagle eagle) {
        _eagle = eagle;
    }

    /**
     * Getter for the eagle associated with the board.
     *
     * @return The eagle associated with the board.
     */

    public Eagle getEagle() {
        return _eagle;
    }

    /**
     * Gets the piece at a specified coordinate.
     *
     * @param crd The coordinate where the desired piece is at.
     * @return The requested piece.
     *
     * @throws Exception Throws an error if the piece can't be found.
     */

    public Piece getPiece(Coordinate crd) throws Exception {
        for (Piece pc : _boardPieces)
            if (pc.getCoordinate().equals(crd))
                return pc;

        throw new Exception("404: Piece Not Found");
    }

    /**
     * Gets all the pieces with the following type.
     *
     * @param type The piece's type.
     * @return The desired piece.
     *
     * @throws Exception Throws an exception when pieces of an unknown type are requested.
     */

    public ArrayList<Piece> getPiecesWithType(Type type) throws Exception {
        ArrayList<Piece> pcs = new ArrayList<Piece>();

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

    /**
     * Adds a piece to the board.
     *
     * @param piece The piece to add to the board.
     *
     * @throws Exception Throws an exception if a piece already exists at the same coordinate as the piece to add.
     */

    public void addPiece(Piece piece) throws Exception {
        try {
            getPiece(piece.getCoordinate());
        } catch (Exception exc) {
            _boardPieces.add(piece);

            return;
        }

        throw new Exception("Piece already exists at coordinate.");
    }

    /**
     * Removes a piece from the board.
     *
     * @param piece The piece to remove.
     *
     * @throws Exception Throws an exception if the piece can't be removed.
     */

    public void removePiece(Piece piece) throws Exception {
        _boardPieces.remove(piece);
    }

    /**
     * Setter for the width of the board.
     *
     * @param w The new width of the board.
     */

    public void setWidth(int w) {
        width = w;
    }

    /**
     * Setter for the height of the board.
     *
     * @param h The new height of the board.
     */

    public void setHeight(int h) {
        height = h;
    }

    /**
     * Getter for the width of the board.
     *
     * @return The width of the board.
     */

    public int getWidth() {
        return width;
    }

    /**
     * Getter for the height of the board.
     *
     * @return The height of the board.
     */

    public int getHeight() {
        return height;
    }

    /**
     * Getter for the game state.
     * @return Returns the game state.
     */

    public State getGameState() {
        return gameState;
    }

    /**
     * Sets the board pieces.
     * This will replace the board's internal array.
     *
     * @param board The new board pieces.
     */

    public void setBoardPieces(ArrayList<Piece> board) {
        _boardPieces = board;
    }

    /**
     * Gets the board pieces.
     *
     * @return The board's internal array of pieces.
     */

    public ArrayList<Piece> getBoardPieces() {
        return _boardPieces;
    }

    /**
     * Queries for all the possible moves a piece can perform.
     *
     * @param pc The piece to test against.
     * @return An array with the answer to the query.
     *
     * @throws Exception Throws an exception if something went wrong.
     */

    private ArrayList<Direction> _getMovePossibilities(Piece pc) throws Exception {
        ArrayList<Direction> pb = new ArrayList<Direction>();

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

    /**
     * Moves the dragon(s).
     *
     * @throws Exception Throws an exception when a dragon can't be found.
     */

    public void moveDragon() throws Exception {
        ArrayList<Piece> dragons = getPiecesWithType(Type.DRAGON);

        for (Piece drag : dragons) {
            try {
                ArrayList<Direction> dir = _getMovePossibilities(drag);

                Random rand = new Random();

                int randVal = rand.nextInt(dir.size() + 1);

                if (dir.size() == randVal)
                    continue;

                drag.move(dir.get(randVal));
            } catch (Exception exc) {

            }
        }
    }

    /**
     * Checks if a dragon is (dangerously) near the hero.
     *
     * @return true if yes, false if not.
     *
     * @throws Exception Throws an exception if neither a dragon or a hero can be found.
     */

    private Dragon _dragonNearHero() throws Exception {
        ArrayList<Piece> dragons = getPiecesWithType(Type.DRAGON);

        Hero hero = (Hero) getPiecesWithType(Type.HERO).get(0);

        for (Piece d : dragons) {
            Dragon drag = (Dragon) d;

            if (hero.getCoordinate().x == drag.getCoordinate().x)
                if (hero.getCoordinate().y == drag.getCoordinate().y || hero.getCoordinate().y - 1 == drag.getCoordinate().y || hero.getCoordinate().y + 1 == drag.getCoordinate().y)
                    return drag;

            if (hero.getCoordinate().y == drag.getCoordinate().y)
                if (hero.getCoordinate().x == drag.getCoordinate().x || hero.getCoordinate().x - 1 == drag.getCoordinate().x || hero.getCoordinate().x + 1 == drag.getCoordinate().x)
                    return drag;
        }

        return null;
    }

    /**
     * Performs a dragon fight.
     *
     * @return The dragon fight result.
     *
     * @throws Exception Throws an exception if a hero can't be found.
     */

    public DragonFightResult checkDragon() throws Exception {
        Dragon drag;

        try {
            drag = _dragonNearHero();

            if (drag == null)
                return DragonFightResult.NOT_FOUND;
        } catch (Exception exc) {
            return DragonFightResult.ALREADY_DEFEATED;
        }

        Hero hero = (Hero) getPiecesWithType(Type.HERO).get(0);

        if (hero.getHasItem()) {
            Blank bl = new Blank(drag.getCoordinate());

            _boardPieces.add(bl);

            removePiece(drag);

            return DragonFightResult.WON;
        }

        _boardPieces.add(new Blank(hero.getCoordinate()));

        removePiece(hero);

        gameState = State.LOST;

        return DragonFightResult.LOST;
    }

    /**
     * Checks the state of the game.
     *
     * @throws Exception Thrown when something goes wrong.
     */

    public void recheckGameState() throws Exception {
        ArrayList<Piece> blanks = getPiecesWithType(Type.BLANK);

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
