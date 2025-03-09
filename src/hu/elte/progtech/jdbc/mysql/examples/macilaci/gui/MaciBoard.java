package hu.elte.progtech.jdbc.mysql.examples.macilaci.gui;

import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.GameConstants;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.MaciLogic;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.score.ScoreBoard;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.dao.HighScoreDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MaciBoard extends JPanel {
    private final MaciLogic maciLogic;
    private Timer timer;
    JButton newGameButton = new JButton("New Game");
    /*
    * constructor
    * létrehozza a játékot, a new game buttont,
    * */
    public MaciBoard(MaciLogic maciLogic) {
        this.maciLogic = maciLogic;
        setPreferredSize(new Dimension(MaciUIConstants.GAME_WIDTH, MaciUIConstants.GAME_HEIGHT));
        setBackground(MaciUIConstants.BACKGROUND_COLOR);
        setFocusable(true);



        startNewGame();


        newGameButton.setBounds(10, 10, 150, 30); // Beállítod a gomb helyét és méretét
        add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startfullNewGame();
                //addKeyListener(maciKeyListener); // Meghívja a metódust, amely az új játékot indítja
                setFocusable(true); // Beállítjuk, hogy a panel képes legyen fókuszálni
                requestFocusInWindow();
            }
        });


        addKeyListener(maciKeyListener);
    }
    /*
    * új játék indítása
    * */

    private void startfullNewGame() {
        stop();

        maciLogic.fullnewGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT);

        repaint();

    }

    private void startNewGame() {
        stop();

        maciLogic.newGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT);

        repaint();

    }

    /*
    * timer leállítása
    * */

    private void stop() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    /*
    * a maci és a 2 vadőr létrehozása
    * */

    private void drawBearAndTwo(Graphics2D graphics2D) {
        int bearX = maciLogic.getBearX();
        int bearY = maciLogic.getBearY();
        graphics2D.setColor(MaciUIConstants.BEAR_COLOR);
        graphics2D.fillOval(bearX, bearY, MaciUIConstants.BLOCK_SIZE, MaciUIConstants.BLOCK_SIZE);
        graphics2D.fillOval(bearX + 5, bearY - 10, 10, 10);
        graphics2D.fillOval(bearX + MaciUIConstants.BLOCK_SIZE - 15, bearY - 10, 10, 10);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillOval(bearX + 10, bearY + 10, 5, 5);
        graphics2D.fillOval(bearX + MaciUIConstants.BLOCK_SIZE - 15, bearY + 10, 5, 5);
        graphics2D.fillOval(bearX + MaciUIConstants.BLOCK_SIZE / 2 - 5, bearY + MaciUIConstants.BLOCK_SIZE / 2, 10, 10); // Orr



        Color rangerColor = MaciUIConstants.RANGER_COLOR;

        drawSquare(graphics2D, rangerColor, (maciLogic.getRanger1X()/50)-1, (maciLogic.getRanger1Y()/50)-1);
        drawSquare(graphics2D, rangerColor, (maciLogic.getRanger2X()/50)-1, (maciLogic.getRanger2Y()/50)-1);
    }

    /*
    * tábla kirajzolása
    * @param Graphics2D graphics2D grafikus elemek megjelenítésére
    * */

    private void drawUI(Graphics2D graphics2D) {
        graphics2D.setColor(MaciUIConstants.GRID_COLOR);
        graphics2D.fill(MaciUIConstants.GRID_RECTANGLE);

        String[][] grids = maciLogic.getGrids();
        int rows = maciLogic.getRow();
        int cols = maciLogic.getColumn();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String cellValue = grids[r][c];
                if (cellValue != null && !cellValue.isEmpty()) {
                    Color colorIndex = getColorIndex(cellValue);
                    drawSquare(graphics2D, colorIndex, r, c);
                }
            }
        }

        graphics2D.setStroke(MaciUIConstants.LARGE_STROKE);
        graphics2D.setColor(MaciUIConstants.GRID_BORDER_COLOR);

        graphics2D.draw(MaciUIConstants.GRID_RECTANGLE);
        graphics2D.draw(MaciUIConstants.PREVIEW_RECTANGLE);

        graphics2D.setFont(new Font("Arial", Font.BOLD, 16));
        graphics2D.drawString("Toplista", MaciUIConstants.PREVIEW_RECTANGLE.x + 90, MaciUIConstants.PREVIEW_RECTANGLE.y + 20);



        maciLogic.startRangerMovement();


        drawScoreBoard(graphics2D);
        repaint();
        revalidate();
    }
    /*
    * alakzatok kirajzolása
    * @param Graphics2D graphics2D grafikus elemek megjelenítésére, colorindex színek , row, col , pozíció
    */
    private void drawSquare(Graphics2D g, Color colorIndex, int row, int col) {
        g.setColor(colorIndex);
        int x = MaciUIConstants.LEFT_MARGIN + col * MaciUIConstants.BLOCK_SIZE;
        int y = MaciUIConstants.TOP_MARGIN + row * MaciUIConstants.BLOCK_SIZE;

        g.fillRect(x, y, MaciUIConstants.BLOCK_SIZE, MaciUIConstants.BLOCK_SIZE);

        g.setStroke(MaciUIConstants.SMALL_STROKE);
        g.setColor(MaciUIConstants.SQUARE_BORDER);
        g.drawRect(x, y, MaciUIConstants.BLOCK_SIZE, MaciUIConstants.BLOCK_SIZE);
    }

    /*
    * színek meghatározása mező alapjan
    * @param cellValue itt adjuk át milyen típusa van a mezőnek
    * */

    public static Color getColorIndex(String cellValue) {
        switch (cellValue) {
            case "empty": return MaciUIConstants.EMPTY_COLOR; // Barna
            case "tree": return MaciUIConstants.TREE_COLOR;
            case "mountain": return MaciUIConstants.MOUNTAIN_COLOR;
            case "basket": return MaciUIConstants.BASKET_COLOR;
            default: return MaciUIConstants.BACKGROUND_COLOR; // Alapértelmezett vagy átlátszó
        }
    }

    /*
    * oldalsó tábla elkészítése
    * @param Graphics2D graphics2D grafikus elemek megjelenítésére
    * */

    private void drawScoreBoard(Graphics2D graphics2D) {
        ScoreBoard scoreBoard = maciLogic.getScoreBoard();
        int x = MaciUIConstants.SCORE_POS_X;
        int y = MaciUIConstants.SCORE_POS_Y;
        graphics2D.setColor(MaciUIConstants.TEXT_COLOR);
        graphics2D.setFont(MaciUIConstants.SMALL_FONT);
        graphics2D.drawString(String.format("High score %11d", scoreBoard.getTopScore()), x, y);
        graphics2D.drawString(String.format("Maci életei: %9d", maciLogic.getLives()), x, y + 30);
        graphics2D.drawString(String.format("Felszedett kosarak: %2d", maciLogic.getBasketsCollected()), x, y + 60);

        graphics2D.setFont(new Font("Arial", Font.BOLD, 16));
        graphics2D.drawString("Toplista", MaciUIConstants.PREVIEW_RECTANGLE.x + 90, MaciUIConstants.PREVIEW_RECTANGLE.y + 20);



        graphics2D.setFont(new Font("Arial", Font.PLAIN, 16));
        HighScoreDao highScoreDao = new HighScoreDao();


        List<String> top10 = scoreBoard.getTop10Scores();
        int startX = MaciUIConstants.PREVIEW_RECTANGLE.x + 10;
        int startY = MaciUIConstants.PREVIEW_RECTANGLE.y + 50;


        for (int i = 0; i < top10.size(); i++) {
            String line = (i + 1) + ". " + top10.get(i);
            graphics2D.drawString(line, startX, startY + (i * 35));
        }



    }

    /*
    * itt hívjuk meg a deawUI-t és a drawBearAndTwo-t is
    * @param Graphics2D graphics2D grafikus elemek megjelenítésére
    * */

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        drawUI(graphics2D);
        drawBearAndTwo((graphics2D));

        ScoreBoard scoreBoard = maciLogic.getScoreBoard();

        Toolkit.getDefaultToolkit().sync();
    }

    /*
    * maci mozgatása WASD-vel
    * */

    private final KeyListener maciKeyListener = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_W -> maciLogic.moveBear(0, -1);
                case KeyEvent.VK_S -> maciLogic.moveBear(0, 1);
                case KeyEvent.VK_A -> maciLogic.moveBear(-1, 0);
                case KeyEvent.VK_D -> maciLogic.moveBear(1, 0);
            }


    int cellX = maciLogic.getBearX() / MaciUIConstants.BLOCK_SIZE;
    int cellY = maciLogic.getBearY() / MaciUIConstants.BLOCK_SIZE;

            repaint();

}};




}