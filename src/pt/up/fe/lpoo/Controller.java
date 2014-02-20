package pt.up.fe.lpoo;

/**
 * Created by MegaEduX on 20/02/14.
 */

import pt.up.fe.lpoo.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public static void main(String args[]) {
        Board brd = new Board();

        brd.printBoard();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readLine;

        try {
            System.out.println();

            readLine = br.readLine();

            System.out.println();

            while (!readLine.equalsIgnoreCase("quit")) {
                if (readLine.equalsIgnoreCase("up"))
                    brd.moveHeroTo(Board.Direction.UP);
                else if (readLine.equalsIgnoreCase("down"))
                    brd.moveHeroTo(Board.Direction.DOWN);
                else if (readLine.equalsIgnoreCase("right"))
                    brd.moveHeroTo(Board.Direction.RIGHT);
                else if (readLine.equalsIgnoreCase("left"))
                    brd.moveHeroTo(Board.Direction.LEFT);

                System.out.println();

                brd.printBoard();

                System.out.println();

                if (brd.getGameState() == Board.State.WON) {
                    System.out.println("You won, congratulations!");

                    break;
                }

                readLine = br.readLine();
            }
        } catch (IOException exception) {
            //  Take care of it.
        }
    }
}
