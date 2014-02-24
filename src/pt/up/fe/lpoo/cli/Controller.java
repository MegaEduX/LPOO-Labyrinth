/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.cli;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.BoardGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public static void main(String args[]) {
        Board brd = new Board();

        int width = 10, height = 10;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readLine;

        Boolean skipBoardGeneration = false;

        try {
            System.out.print("Press D for default or any other string to create a new board: ");

            readLine = br.readLine();

            if (readLine.equalsIgnoreCase("d")) {
                skipBoardGeneration = true;
            } else {
                System.out.print("Board Width? (Must be bigger than 3): ");

                readLine = br.readLine();

                if (Integer.parseInt(readLine) > 3)
                    width = Integer.parseInt(readLine);
                else {
                    while (true) {
                        System.out.print("Invalid Width. Please try again. (Must be bigger than 3): ");
                        readLine = br.readLine();

                        if (Integer.parseInt(readLine) > 3) {
                            width = Integer.parseInt(readLine);

                            break;
                        }
                    }
                }

                System.out.println();

                System.out.print("Board Height? (Must be bigger or equal than 3): ");

                readLine = br.readLine();

                if (Integer.parseInt(readLine) > 3)
                    height = Integer.parseInt(readLine);
                else {
                    while (true) {
                        System.out.print("Invalid Height. Please try again. (Must be bigger than 3): ");
                        readLine = br.readLine();

                        if (Integer.parseInt(readLine) > 3) {
                            height = Integer.parseInt(readLine);

                            break;
                        }
                    }
                }

                System.out.println();

                System.out.println("Board Size is " + width + "w * " + height + "h.");
            }

            System.out.println();
        } catch (IOException exception) {
            width = 10;
            height = 10;

            System.out.println("An error has occurred while attempting to ask for board size values. Continuing with default values (10w * 10h)...");
        }

        if (!skipBoardGeneration) {
            Boolean boardGenSuccess = false;

            for (int i = 0; i < 10; i++) {
                try {
                    BoardGenerator brdGen = new BoardGenerator(width, height);

                    Board.Type[][] gdBoard = brdGen.generateBoard();

                    brd.setBoardRepresentation(gdBoard);

                    brd.setWidth(width);
                    brd.setHeight(height);

                    boardGenSuccess = true;

                    break;
                } catch (Exception exc) {

                }
            }

            if (!boardGenSuccess)
                System.out.println("Unable to generate a new board. Proceeding with default board...");
        }

        brd.printBoard();

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