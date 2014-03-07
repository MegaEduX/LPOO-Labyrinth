/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.test;

import org.junit.*;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.BoardGenerator;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;

import java.util.Vector;

import static org.junit.Assert.*;

public class UnitTestGroupTwoMD {
    private Board brd;

    private void _performMultipleMovement(Board.Direction[] moves, Piece pc) throws Exception {
        for (Board.Direction mv : moves)
            pc.move(mv);
    }

    @Before public void setUp() throws Exception {
        brd = new Board();

        BoardGenerator gen = new BoardGenerator(10, 10, 3);

        Vector<Piece> ooBoard = gen.getDragonTestBoard();

        for (Piece pc : ooBoard)
            pc.setBoard(brd);

        brd.setBoardPieces(ooBoard);
        brd.setWidth(10);
        brd.setHeight(10);

        assertNotNull("Couldn't generate board.", brd);
    }

    @Test public void testDragonNumber() throws Exception {
        Vector<Piece> pcs = brd.getPiecesWithType(Board.Type.DRAGON);

        assertTrue(pcs.size() == 3);
    }

    @Test public void testMoveDragons() throws Exception {
        Vector<Piece> pcs = brd.getPiecesWithType(Board.Type.DRAGON);

        ((Dragon) pcs.get(0)).setBehavior(Dragon.Behavior.NO_SLEEP);
        ((Dragon) pcs.get(1)).setBehavior(Dragon.Behavior.NO_SLEEP);
        ((Dragon) pcs.get(2)).setBehavior(Dragon.Behavior.NO_SLEEP);

        Coordinate dc1 = new Coordinate(pcs.get(0).getCoordinate().x, pcs.get(0).getCoordinate().y);
        Coordinate dc2 = new Coordinate(pcs.get(1).getCoordinate().x, pcs.get(1).getCoordinate().y);
        Coordinate dc3 = new Coordinate(pcs.get(2).getCoordinate().x, pcs.get(2).getCoordinate().y);

        Board.Direction[] mvm = {Board.Direction.DOWN, Board.Direction.DOWN, Board.Direction.DOWN};

        _performMultipleMovement(mvm, pcs.get(0));
        _performMultipleMovement(mvm, pcs.get(1));
        _performMultipleMovement(mvm, pcs.get(2));

        assertEquals(pcs.get(0).getCoordinate().x, dc1.x);
        assertEquals(pcs.get(0).getCoordinate().y - 3, dc1.y);

        assertEquals(pcs.get(1).getCoordinate().x, dc2.x);
        assertEquals(pcs.get(1).getCoordinate().y - 3, dc2.y);

        assertEquals(pcs.get(2).getCoordinate().x, dc3.x);
        assertFalse(pcs.get(2).getCoordinate().y - 3 == dc3.y);
    }

    @After public void tearDown() throws Exception {

    }
}
