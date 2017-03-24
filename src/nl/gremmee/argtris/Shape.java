package nl.gremmee.argtris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Shape {
    enum Tetrominos {
        NoShape(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }), //
        ZShape(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }), //
        SShape(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }), //
        LineShape(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }), //
        TShape(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }), //
        SquareShape(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }), //
        LShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }), //
        MirroredLShape(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } //
        );

        public int[][] coords;

        private Tetrominos(int[][] aCoords) {
            this.coords = aCoords;
        }
    }

    private int[][] coords;

    private Tetrominos pieceShape;

    public Shape() {
        coords = new int[4][2];
        setShape(Tetrominos.NoShape);
    }

    public static void drawSquare(Graphics g, int x, int y, Board aBoard, Tetrominos shape) {
        Color color = aBoard.COLORS[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, aBoard.squareWidth() - 2, aBoard.squareHeight() - 2);
        Color brighter = color.brighter();
        g.setColor(brighter);
        g.drawLine(x, y + aBoard.squareHeight() - 1, x, y);
        g.drawLine(x, y, x + aBoard.squareWidth() - 1, y);
        g.setColor(brighter.brighter());
        g.drawLine(x + 2, y + 2, x + (aBoard.squareWidth() / 8), y + 2);
        g.drawLine(x + 2, y + 2, x + 2, y + (aBoard.squareHeight() / 8));
        Color darker = color.darker();
        g.setColor(darker);
        g.drawLine(x + 1, y + aBoard.squareHeight() - 1, x + aBoard.squareWidth() - 1, y + aBoard.squareHeight() - 1);
        g.drawLine(x + aBoard.squareWidth() - 1, y + aBoard.squareHeight() - 1, x + aBoard.squareWidth() - 1, y + 1);
        g.setColor(darker.darker());
        g.drawLine(x + aBoard.squareWidth() - (aBoard.squareWidth() / 8), y + aBoard.squareHeight() - 2,
                x + aBoard.squareWidth() - 2, y + aBoard.squareHeight() - 2);
        g.drawLine(x + aBoard.squareWidth() - 2, y + aBoard.squareHeight() - (aBoard.squareHeight() / 8),
                x + aBoard.squareWidth() - 2, y + aBoard.squareHeight() - 2);
    }

    public Tetrominos getShape() {
        return pieceShape;
    }

    public int minX() {
        int m = coords[0][0];

        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    public int minY() {
        int m = coords[0][1];

        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

    public Shape rotateLeft() {
        if (pieceShape == Tetrominos.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }

    public Shape rotateRight() {
        if (pieceShape == Tetrominos.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }

    public void setRandomShape() {
        Random r = new Random();

        int x = 0;
        do {
            x = Math.abs((r.nextInt() % 7) + 1);
        } while (x <= 0);
        Tetrominos[] values = Tetrominos.values();
        setShape(values[x]);
    }

    public void setShape(Tetrominos aShape) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                coords[i][j] = aShape.coords[i][j];
            }
        }

        pieceShape = aShape;
    }

    public int x(int aIndex) {
        return coords[aIndex][0];
    }

    public int y(int aIndex) {
        return coords[aIndex][1];
    }

    private void setX(int aIndex, int aX) {
        coords[aIndex][0] = aX;
    }

    private void setY(int aIndex, int aY) {
        coords[aIndex][1] = aY;
    }
}
