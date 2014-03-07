/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.test;

import org.junit.*;

import pt.up.fe.lpoo.logic.*;
import pt.up.fe.lpoo.logic.piece.*;
import pt.up.fe.lpoo.logic.piece.itemizable.*;

import java.util.Vector;

import static org.junit.Assert.*;

public class UnitTestGroupOne {
    private Board brd;

    private void _performMultipleMovement(Board.Direction[] moves, Piece pc) throws Exception {
        for (Board.Direction mv : moves)
            pc.move(mv);
    }

    @Before public void setUp() {
        try {
            brd = new Board();

            BoardGenerator gen = new BoardGenerator(10, 10, 1);

            Vector<Piece> ooBoard = gen.getDefaultBoard();

            for (Piece pc : ooBoard)
                pc.setBoard(brd);

            brd.setBoardPieces(ooBoard);
            brd.setWidth(10);
            brd.setHeight(10);

            Dragon drag = (Dragon) brd.getPiecesWithType(Board.Type.DRAGON).get(0);

            drag.setBehavior(Dragon.Behavior.STOP);

            assertNotNull("Couldn't generate board.", brd);
        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test public void testHeroMove() {
        try {
            Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

            Coordinate oldCoord = new Coordinate(hero.getCoordinate().x, hero.getCoordinate().y);

            hero.move(Board.Direction.DOWN);

            Coordinate newCoord = hero.getCoordinate();

            if (oldCoord.x != newCoord.x || oldCoord.y != newCoord.y - 1)
                fail("Move failed.");
        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test public void testHeroImmobile() {
        try {
            Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

            Coordinate oldCoord = new Coordinate(hero.getCoordinate().x, hero.getCoordinate().y);

            hero.move(Board.Direction.LEFT);

            Coordinate newCoord = hero.getCoordinate();

            if (oldCoord.x != newCoord.x || oldCoord.y != newCoord.y)
                fail("Move occurred. But, to where? :o");
        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test public void testHeroGetSword() {
        try {
            Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

            Board.Direction[] moveSequence = {Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT,
                    Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN,
                    Board.Direction.LEFT, Board.Direction.LEFT, Board.Direction.LEFT,
                    Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN};

            _performMultipleMovement(moveSequence, hero);

            assertTrue(hero.getHasItem());
        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test public void testLoseGame() {
        try {
            Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

            hero.move(Board.Direction.DOWN);

            brd.moveDragon();
            brd.checkDragon();
            brd.recheckGameState();

            assertTrue(brd.getGameState() == Board.State.LOST);
        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test(expected = Exception.class) public void testSlayDragon() throws Exception {
        try {
            testHeroGetSword();

            Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

            Board.Direction[] moveSequence = {Board.Direction.UP, Board.Direction.UP, Board.Direction.UP,
                    Board.Direction.UP, Board.Direction.UP, Board.Direction.UP};

            _performMultipleMovement(moveSequence, hero);

            brd.checkDragon();
            brd.recheckGameState();

        } catch (Exception exc) {
            fail();
        }

        Dragon drag = (Dragon) brd.getPiecesWithType(Board.Type.DRAGON).get(0);
    }

    @Test public void testWinGame() throws Exception {
        Boolean wentWell = false;

        try {
            testSlayDragon();
        } catch (Exception exc) {
            wentWell = true;
        } finally {
            assertTrue(wentWell);
        }

        Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

        Board.Direction[] moveSequence = {Board.Direction.UP, Board.Direction.UP, Board.Direction.UP,
                Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT,
                Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT,
                Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN,
                Board.Direction.RIGHT};

        _performMultipleMovement(moveSequence, hero);

        brd.recheckGameState();

        assertTrue(brd.getGameState() == Board.State.WON);
    }

    @Test public void testGetExitWithoutSword() throws Exception {
        Hero hero = (Hero) brd.getPiecesWithType(Board.Type.HERO).get(0);

        Board.Direction[] moveSequence = {Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT,
                Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT, Board.Direction.RIGHT,
                Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN,
                Board.Direction.RIGHT};

        _performMultipleMovement(moveSequence, hero);

        brd.recheckGameState();

        assertTrue(brd.getGameState() == Board.State.RUNNING);
    }

    @After public void tearDown() {
        //  Nothing so far, AFAIK at least.
    }
}
