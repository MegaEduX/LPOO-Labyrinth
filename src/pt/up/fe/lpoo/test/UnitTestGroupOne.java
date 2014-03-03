/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.test;

import org.junit.*;

import pt.up.fe.lpoo.logic.*;

import pt.up.fe.lpoo.logic.piece.itemizable.*;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class UnitTestGroupOne {
    Board brd;

    @Before public void setUp() {
        try {
            BoardGenerator gen = new BoardGenerator(10, 10, 1);

            brd.setBoardPieces(gen.getDefaultBoard());
            brd.setWidth(10);
            brd.setHeight(10);

            Dragon drag = (Dragon) brd.getPiecesWithType(Board.Type.DRAGON).get(0);

            drag.setBehavior(Dragon.Behavior.STOP);

            assertNotNull("Couldn't generate board", brd);
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

    @After public void tearDown() {

    }
}
