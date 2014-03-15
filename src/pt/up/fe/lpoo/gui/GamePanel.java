/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.gui;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;
import pt.up.fe.lpoo.logic.piece.Wall;
import pt.up.fe.lpoo.logic.piece.itemizable.Blank;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;
import pt.up.fe.lpoo.logic.piece.itemizable.Eagle;
import pt.up.fe.lpoo.logic.piece.itemizable.Hero;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {
    //  public Coordinate _board = new Coordinate(5, 5);
    public Coordinate _window = new Coordinate(500, 500);

    private Board _board;

    public GamePanel(/*Coordinate boardSize, */Board board, Coordinate windowSize) {
        //  _board = boardSize;

        _board = board;

        _window = windowSize;

        addKeyListener(this);

        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int sepX = _window.x / _board.getWidth();
        int sepY = _window.y / _board.getHeight();

        for (int i = 1; i <= _board.getWidth(); i++) {
            g.drawLine(sepX * i, 0, sepX * i, _window.y);
            g.drawLine(0, sepY * i, _window.x, sepY * i);
        }

        File img = new File("HootHoot.png");

        try {
            BufferedImage bimg = ImageIO.read(img);

            g.drawImage(bimg, 0, 0, sepX, sepY, null);
        } catch (Exception e) {

        }

        for (int i = 0; i < _board.getHeight(); i++) {
            for (int j = 0; j < _board.getWidth(); j++) {
                Piece pc;

                try {
                    pc = _board.getPiece(new Coordinate(j, i));
                } catch (Exception exc) {
                    System.out.print(" ");

                    continue;
                }

                if (pc instanceof Wall) {
                    File wood = new File("wooden_bg.jpg");

                    try {
                        BufferedImage woodbg = ImageIO.read(wood);

                        g.drawImage(woodbg, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                    } catch (Exception e) {

                    }
                } else {
                    File gr = new File("grass.png");

                    try {
                        BufferedImage gri = ImageIO.read(gr);

                        g.drawImage(gri, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                    } catch (Exception e) {

                    }

                    if (pc instanceof Hero) {
                        File hero = new File("teemo.png");

                        try {
                            BufferedImage heroi = ImageIO.read(hero);

                            g.drawImage(heroi, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else if (pc instanceof Blank && ((Blank) pc).getHasItem()) {
                        File sw = new File("sword.png");

                        try {
                            BufferedImage esp = ImageIO.read(sw);

                            g.drawImage(esp, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else if (pc instanceof Eagle)
                        System.out.print(((Eagle) pc).isFlying() ? "X" : "E");
                    else if (pc instanceof Dragon) {
                        File dragon = new File(((Dragon) pc).getIsSleeping() ? "gyarados_zzz.png" : "gyarados.png");

                        try {
                            BufferedImage drg = ImageIO.read(dragon);

                            g.drawImage(drg, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else if (pc instanceof Blank && ((Blank) pc).getIsExit()) {
                        File exit = new File("door.png");

                        try {
                            BufferedImage exi = ImageIO.read(exit);

                            g.drawImage(exi, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else {

                    }
                }
            }
        }

    }

    //
    //  Key Listener Implementation
    //

    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    public void keyPressed(KeyEvent e) {
        String keyText = java.awt.event.KeyEvent.getKeyText(e.getKeyCode());

        try {
            Hero hero = (Hero) _board.getPiecesWithType(Board.Type.HERO).get(0);
            if (keyText.equalsIgnoreCase("up")) {
                hero.move(Board.Direction.UP);
            } else if (keyText.equalsIgnoreCase("left")) {
                hero.move(Board.Direction.LEFT);
            } else if (keyText.equalsIgnoreCase("down")) {
                hero.move(Board.Direction.DOWN);
            } else if (keyText.equalsIgnoreCase("right")) {
                hero.move(Board.Direction.RIGHT);
            }
        } catch (Exception exc) {

        }

        try {
            _board.moveDragon();
            _board.checkDragon();
            _board.recheckGameState();
        } catch (Exception exc) {

        }

        repaint();
        revalidate();
    }

    public void keyReleased(KeyEvent e) {

    }
}
