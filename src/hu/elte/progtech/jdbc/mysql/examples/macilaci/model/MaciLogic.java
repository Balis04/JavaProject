package hu.elte.progtech.jdbc.mysql.examples.macilaci.model;

import hu.elte.progtech.jdbc.mysql.examples.macilaci.gui.MaciUIConstants;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.score.ScoreBoard;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.dao.HighScoreDao;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.entity.HighScore;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.List;

public final class MaciLogic {
    private int row;
    private int column;
    private int bearX = GameConstants.bearX;
    private int bearY = GameConstants.bearY;
    private int ranger1X = GameConstants.ranger1X;
    private int ranger1Y = GameConstants.ranger1Y;
    private int ranger2X = GameConstants.ranger2X;
    private int ranger2Y = GameConstants.ranger2Y;
    private List<String> top10;

    private int basketsCollected = 0;
    private int lives = GameConstants.bearLives;

    public int getLives() {
        return lives;
    }

    public int getBasketsCollected() {
        return basketsCollected;
    }

    public int getBearX() {
        return bearX;
    }

    public int getBearY() {
        return bearY;
    }

    public int getRanger1X() {
        return ranger1X;
    }

    public int getRanger1Y() {
        return ranger1Y;
    }

    public int getRanger2X() {
        return ranger2X;
    }

    public int getRanger2Y() {
        return ranger2Y;
    }

    private String[][] grids;

    public String[][] getGrids() {
        return grids;
    };

    private ScoreBoard scoreBoard;

    private HighScoreDao highScoreDao;
    private String userName;

    /*
    * constructor
    * */

    public MaciLogic() {
        this.highScoreDao = new HighScoreDao();
    }

    /*
    * Új játék indítása
    * @param row , column , tábla méreteinek megadása
    * inicializáljuk a táblát, és minden adattagnak megadjuk a kezdő értéket
    * */

    public void newGame(int row, int column){
        this.row = row;
        this.column = column;
        //this.userName = userName;

        grids = new String[row][column];


        initScoreBoard();
        selectRandomBoard();


        bearX = GameConstants.bearX;
        bearY = GameConstants.bearY;
        ranger1X = GameConstants.ranger1X;
        ranger1Y = GameConstants.ranger1Y;
        ranger2X = GameConstants.ranger2X;
        ranger2Y = GameConstants.ranger2Y;

        scoreBoard.reset();
    }

    public void fullnewGame(int row, int column){
        this.row = row;
        this.column = column;
        //this.userName = userName;

        grids = new String[row][column];


        initScoreBoard();
        selectRandomBoard();


        bearX = GameConstants.bearX;
        bearY = GameConstants.bearY;
        ranger1X = GameConstants.ranger1X;
        ranger1Y = GameConstants.ranger1Y;
        ranger2X = GameConstants.ranger2X;
        ranger2Y = GameConstants.ranger2Y;

        basketsCollected = 0;
        lives = GameConstants.bearLives;

        scoreBoard.reset();
    }

    /*
    * Kosárfelszedésnél használjuk
    * ha nagyobb mint 0 a felszedettek száma, akkor beletesszük az adatbázisba
    * */

    public void saveScoreIfGreaterThan0() {
        int scoreValue = basketsCollected;
        if(scoreValue > 0) {
            try {
                HighScore score = new HighScore();
                score.setName(userName);
                score.setScore(scoreValue);
                highScoreDao.add(score);
            } catch (SQLException ex) {
                throw new IllegalStateException("Failed to save high_score to database!", ex);
            }
        }
    }

    /*
    * 10 tábla közül választhatunk, ebből generál egyet random
    * */

    private void selectRandomBoard() {
        Random random = new Random();
        int index = random.nextInt(GameConstants.predefinedBoards.length);

        String[][] selectedBoard = GameConstants.predefinedBoards[index];

        grids = new String[GameConstants.rows][selectedBoard[0].length];
        for (int i = 0; i < selectedBoard.length; i++) {
            System.arraycopy(selectedBoard[i], 0, grids[i], 0, selectedBoard[i].length);
        }
    }

    /*
    * Maci mozgatása
    * @param dx, dy irány amerre mozgatjuk
    * */

    public void moveBear(int dx, int dy) {
        int newX = bearX + dx * GameConstants.BLOCK_SIZE;
        int newY = bearY + dy * GameConstants.BLOCK_SIZE;

        if (isValidMove(newX, newY)) {
            bearX = newX;
            bearY = newY;

            int cellX = (newX / MaciUIConstants.BLOCK_SIZE)-1;
            int cellY = (newY / MaciUIConstants.BLOCK_SIZE)-1;



            if (isBasketCell(cellX, cellY)) {
                basketsCollected++;
                setCellToEmpty(cellX, cellY);

                if(basketsCollected % 5 == 0){
                    newGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT);
                }
            }
        }
    }

    /*
    * Vadőr 1 ellenőrzése a közelben
    * @param bearX , bearY a maci pozíciója
    * */

    private boolean isRangerNearby(int bearX, int bearY) {
        int bearCellX = (bearX / MaciUIConstants.BLOCK_SIZE)-1;
        int bearCellY = (bearY / MaciUIConstants.BLOCK_SIZE)-1;

        int ranger1CellX = (ranger1X / MaciUIConstants.BLOCK_SIZE)-1;
        int ranger1CellY = (ranger1Y / MaciUIConstants.BLOCK_SIZE)-1;
        return Math.abs(bearCellX - ranger1CellX) <= 1 && Math.abs(bearCellY - ranger1CellY) <= 1;
    }

    /*
     * Vadőr 2 ellenőrzése a közelben
     * @param bearX , bearY a maci pozíciója
     * */

    private boolean isRangerNearby2(int bearX, int bearY) {
        int bearCellX = (bearX / MaciUIConstants.BLOCK_SIZE)-1;
        int bearCellY = (bearY / MaciUIConstants.BLOCK_SIZE)-1;

        int ranger1CellX = (ranger2X / MaciUIConstants.BLOCK_SIZE)-1;
        int ranger1CellY = (ranger2Y / MaciUIConstants.BLOCK_SIZE)-1;
        return Math.abs(bearCellX - ranger1CellX) <= 1 && Math.abs(bearCellY - ranger1CellY) <= 1;
    }

    /*
    * Maci életpont vesztése
    * */

    private void loseLife() {
        lives--;
        bearX = GameConstants.bearX;
        bearY = GameConstants.bearY;

    }

    /*
    * Kosár van-e a cellában
    * @param x , y pozíció
    * */

    public boolean isBasketCell(int x, int y) {
        return grids[y][x].equals("basket");
    }

    /*
    * Kosár cella üresre állítása
    * @param x , y pozíció
    * */

    public void setCellToEmpty(int x, int y) {
        grids[y][x] = "empty";
    }

    /*
    * A lépés érvényességének vizsgálata Maci esetén
    * @param x , y pozíció
    * megnézi, hogy a lépés nem lépne e ki a tábláról, illetve a nem lépne-e hegyre vagy fára
    * */

    public boolean isValidMove(int x, int y) {
        if (x < 50 || x >= GameConstants.WIDTH || y < 50 || y >= GameConstants.HEIGHT) {

            return false;
        }

        String cellType = grids[(y/50)-1][(x/50)-1];


        if (cellType.equals("tree") || cellType.equals("mountain")) {

            return false;
        }
        return !(cellType.equals("tree") || cellType.equals("mountain"));
    }

    /*
     * A lépés érvényességének vizsgálata Vadőr esetén
     * @param x , y pozíció
     * megnézi, hogy a lépés nem lépne e ki a tábláról, illetve a nem lépne-e hegyre vagy fára
     * */

    public boolean isValidMove2(int x, int y) {
        if (x < 50 || x >= GameConstants.WIDTH || y < 50 || y >= GameConstants.HEIGHT) {

            return false;
        }

        String cellType = grids[(x/50)-1][(y/50)-1];// Megnézzük a cella típusát


        if (cellType.equals("tree") || cellType.equals("mountain")) {

            return false;
        }
        return !(cellType.equals("tree") || cellType.equals("mountain"));
    }

    /*
    * Vadőr mozgatása vízsintesen és függőlegesen
    * */

    private final Timer rangerMovementTimer = new Timer(1000, new ActionListener() {

        int directionX = 1;
        int directionY = 0;

        int direction2X = 0;
        int direction2Y = 1;

        @Override
        public void actionPerformed(ActionEvent e) {
            int newX = ranger1X + directionX * MaciUIConstants.BLOCK_SIZE;
            int newY = ranger1Y + directionY * MaciUIConstants.BLOCK_SIZE;

            int new2X = ranger2X + direction2X * MaciUIConstants.BLOCK_SIZE;
            int new2Y = ranger2Y + direction2Y * MaciUIConstants.BLOCK_SIZE;
            if (!isValidMove2(newX, newY)) {
                directionX = -directionX;
                newX = ranger1X + directionX * MaciUIConstants.BLOCK_SIZE;
            }

            if (!isValidMove2(new2X, new2Y)) {
                direction2Y = -direction2Y;
                new2Y = ranger2Y + direction2Y * MaciUIConstants.BLOCK_SIZE;
            }
            moveRanger1(directionX, directionY);
            moveRanger2(direction2X, direction2Y);

        }
    });

    /*
    * A vadőrök elindítása
    * */

    public void startRangerMovement() {
        rangerMovementTimer.start();
    }

    /*
    * vadőr 1 mozgatása
    * @param dx, dy az új pozíció
    * */

    public void moveRanger1(int dx, int dy) {
        int newX = ranger1X + dx * GameConstants.BLOCK_SIZE;
        int newY = ranger1Y + dy * GameConstants.BLOCK_SIZE;

        if (isValidMove2(newX, newY)) {

            ranger1X = newX;
            ranger1Y = newY;

            if (isRangerNearby(bearY, bearX)) {
                loseLife();
            }

        }

        if(lives <= 0) {

            String name = JOptionPane.showInputDialog(null, "What is your name? ");
            if (name == null || name.trim().isEmpty()) {
                name = "Anonymous";
            }
            userName = name;
            saveScoreIfGreaterThan0();
            newGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT);
            lives = GameConstants.bearLives;
            basketsCollected = 0;

        }
    }

    /*
     * vadőr 2 mozgatása
     * @param dx, dy az új pozíció
     * */

    public void moveRanger2(int dx, int dy) {
        int newX = ranger2X + dx * GameConstants.BLOCK_SIZE;
        int newY = ranger2Y + dy * GameConstants.BLOCK_SIZE;

        if (isValidMove2(newX, newY)) {

            ranger2X = newX;
            ranger2Y = newY;

            if (isRangerNearby2(bearY, bearX)) {
                loseLife();
            }

            if(lives <= 0) {

                String name = JOptionPane.showInputDialog(null, "What is your name? ");
                if (name == null || name.trim().isEmpty()) {
                    name = "Anonymous";
                }
                saveScoreIfGreaterThan0();
                newGame(GameConstants.NORMAL_ROW_COUNT, GameConstants.NORMAL_COL_COUNT);
                lives = GameConstants.bearLives;
                basketsCollected = 0;

            }
        }
    }

    /*
    * ScoreBoard inicializálása
    * */

    private void initScoreBoard() {
        scoreBoard = new ScoreBoard();
        try {
            int topScore = highScoreDao.getTopScore();
            top10 = highScoreDao.getTop10Scores();
            scoreBoard.setTopScore(topScore);
            scoreBoard.setTop10scores(top10);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}