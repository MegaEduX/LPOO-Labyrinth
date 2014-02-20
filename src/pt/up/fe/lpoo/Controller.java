/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo;

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

            System.out.println("Please type (U)p/(L)eft/(D)own/(R)ight to play, followed by Return.");

            readLine = br.readLine();

            System.out.println();

            while (!readLine.equalsIgnoreCase("quit")) {
                if (readLine.equalsIgnoreCase("up") || readLine.equalsIgnoreCase("u"))
                    brd.movePieceTo(Board.Type.HERO, Board.Direction.UP);
                else if (readLine.equalsIgnoreCase("down") || readLine.equalsIgnoreCase("d"))
                    brd.movePieceTo(Board.Type.HERO, Board.Direction.DOWN);
                else if (readLine.equalsIgnoreCase("right") || readLine.equalsIgnoreCase("r"))
                    brd.movePieceTo(Board.Type.HERO, Board.Direction.RIGHT);
                else if (readLine.equalsIgnoreCase("left") || readLine.equalsIgnoreCase("l"))
                    brd.movePieceTo(Board.Type.HERO, Board.Direction.LEFT);

                System.out.println();

                brd.printBoard();

                System.out.println();

                if (brd.getGameState() == Board.State.WON) {
                    System.out.println("You won, congratulations!");

                    break;
                } else if (brd.getGameState() == Board.State.LOST) {
                    System.out.println("You lost... :(");

                    break;
                }

                System.out.println("Please type (U)p/(L)eft/(D)own/(R)ight to play, followed by Return.");

                readLine = br.readLine();
            }
        } catch (IOException exception) {
            //  Take care of it.
        }
    }
}
