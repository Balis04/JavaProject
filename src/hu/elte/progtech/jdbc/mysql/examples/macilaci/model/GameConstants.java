package hu.elte.progtech.jdbc.mysql.examples.macilaci.model;

/*
 * állandó adattagok
 * */

public final class GameConstants {
    private GameConstants(){}

    public static final int NORMAL_ROW_COUNT = 10;
    public static final int NORMAL_COL_COUNT = 10;

    public static final int EMPTY = -1;
    public static final int BORDER = -2;

    public static final int cellSize = 50;
    public static final int rows = 10;
    public static final int cols = 10;

    public static final int bearX = 50;
    public static final int bearY = 50;
    public static final int ranger1X = 100;
    public static final int ranger1Y = 200;
    public static final int ranger2X = 500;
    public static final int ranger2Y = 350;

    public static final int bearLives = 3;

    public static final int BLOCK_SIZE = 50;

    public static final int TOP_MARGIN = 50;
    public static final int LEFT_MARGIN = 50;

    public static final int WIDTH = 508;
    public static final int HEIGHT = 508;

    public static final String[][][] predefinedBoards = {
            {
                    {"empty", "empty", "tree", "empty", "basket", "mountain", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "tree", "empty", "empty"},
                    {"mountain", "empty", "empty", "empty", "empty", "basket", "empty", "empty", "empty", "tree"},
                    {"empty", "empty", "tree", "empty", "mountain", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "tree", "empty", "empty", "empty", "mountain"},
                    {"tree", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "mountain", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "basket", "empty", "empty", "empty", "basket", "mountain", "empty", "tree", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "basket", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "empty", "empty", "empty", "mountain", "basket", "empty", "empty", "empty", "tree"},
                    {"empty", "mountain", "empty", "empty", "empty", "empty", "tree", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "mountain", "empty"},
                    {"basket", "empty", "empty", "empty", "empty", "empty", "tree", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "basket", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"tree", "empty", "empty", "tree", "empty", "empty", "empty", "mountain", "empty", "empty"},
                    {"empty", "mountain", "empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty"},
                    {"empty", "basket", "empty", "empty", "mountain", "empty", "tree", "empty", "empty", "empty"},
                    {"mountain", "empty", "empty", "empty", "empty", "empty", "empty", "tree", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "basket"}
            },
            {
                    {"empty", "tree", "empty", "mountain", "empty", "empty", "basket", "empty", "empty", "empty"},
                    {"empty", "empty", "tree", "empty", "empty", "empty", "empty", "tree", "empty", "empty"},
                    {"mountain", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "basket", "empty"},
                    {"empty", "empty", "empty", "basket", "mountain", "empty", "empty", "tree", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "tree", "empty", "mountain", "empty", "empty"},
                    {"tree", "empty", "mountain", "empty", "empty", "empty", "empty", "empty", "basket", "empty"},
                    {"empty", "empty", "empty", "mountain", "empty", "tree", "empty", "empty", "empty", "empty"},
                    {"empty", "tree", "empty", "empty", "basket", "empty", "mountain", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "empty", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "basket", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "mountain", "empty", "empty", "empty"},
                    {"empty", "mountain", "empty", "empty", "empty", "empty", "tree", "empty", "empty", "basket"},
                    {"tree", "empty", "empty", "empty", "empty", "basket", "empty", "empty", "empty", "mountain"},
                    {"empty", "empty", "tree", "mountain", "empty", "empty", "empty", "empty", "empty", "tree"},
                    {"empty", "empty", "empty", "empty", "mountain", "empty", "empty", "empty", "empty", "tree"},
                    {"mountain", "empty", "empty", "empty", "empty", "empty", "basket", "empty", "tree", "empty"},
                    {"empty", "tree", "empty", "empty", "mountain", "empty", "empty", "basket", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "empty", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "tree", "empty", "empty", "basket", "empty", "empty", "empty", "empty", "tree"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "mountain", "empty", "empty", "empty"},
                    {"empty", "empty", "tree", "empty", "empty", "mountain", "empty", "empty", "basket", "empty"},
                    {"tree", "empty", "empty", "empty", "basket", "empty", "mountain", "empty", "empty", "empty"},
                    {"empty", "tree", "empty", "empty", "empty", "tree", "mountain", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "mountain", "tree", "basket", "empty"},
                    {"tree", "mountain", "empty", "empty", "empty", "empty", "empty", "tree", "empty", "empty"},
                    {"empty", "empty", "tree", "mountain", "empty", "empty", "empty", "basket", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "empty", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "mountain", "empty", "empty", "empty", "tree", "basket", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "mountain", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "basket", "empty", "empty"},
                    {"tree", "empty", "empty", "mountain", "empty", "empty", "empty", "tree", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "mountain", "empty", "basket", "empty", "tree", "empty"},
                    {"empty", "tree", "empty", "empty", "mountain", "empty", "empty", "empty", "empty", "tree"},
                    {"tree", "empty", "mountain", "empty", "empty", "basket", "empty", "tree", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "tree", "empty", "empty", "mountain", "empty", "basket"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "empty", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "empty", "tree", "basket", "empty", "mountain", "empty", "empty", "empty", "empty"},
                    {"mountain", "empty", "empty", "empty", "empty", "empty", "tree", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "empty", "empty", "basket", "empty", "mountain", "empty"},
                    {"empty", "tree", "empty", "empty", "empty", "empty", "empty", "mountain", "empty", "empty"},
                    {"empty", "mountain", "empty", "tree", "basket", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "empty", "empty", "tree", "basket", "empty", "empty"},
                    {"tree", "empty", "empty", "empty", "empty", "empty", "empty", "mountain", "empty", "empty"},
                    {"empty", "tree", "empty", "mountain", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "basket", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "basket", "empty", "tree"},
                    {"empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "empty", "empty", "tree", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "tree", "empty", "empty", "mountain", "empty", "empty"},
                    {"empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "mountain", "empty"},
                    {"empty", "basket", "empty", "empty", "tree", "mountain", "empty", "empty", "empty", "empty"},
                    {"empty", "tree", "empty", "empty", "empty", "empty", "mountain", "empty", "basket", "empty"},
                    {"empty", "basket", "empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "basket", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "empty", "empty", "tree", "mountain", "empty", "basket", "empty", "empty", "empty"},
                    {"mountain", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "tree", "empty", "empty", "mountain", "empty", "basket", "empty", "empty"},
                    {"empty", "empty", "empty", "empty", "mountain", "empty", "tree", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "empty","empty", "empty", "empty", "mountain", "empty", "empty"},
                    {"empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty", "basket", "empty"},
                    {"empty", "empty", "tree", "empty", "empty", "empty", "mountain", "empty", "empty", "empty"},
                    {"basket", "empty", "mountain", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "basket", "empty", "empty", "tree", "empty", "empty"}
            },
            {
                    {"empty", "empty", "empty", "mountain", "empty", "empty", "empty", "empty", "basket", "empty"},
                    {"empty", "empty", "tree", "empty", "empty", "empty", "empty", "mountain", "tree", "empty"},
                    {"empty", "mountain", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "tree"},
                    {"empty", "empty", "empty", "basket", "mountain", "tree", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "mountain", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "mountain", "basket", "empty"},
                    {"tree", "empty", "empty", "empty", "empty", "empty", "tree", "empty", "mountain", "empty"},
                    {"empty", "basket", "empty", "tree", "empty", "empty", "empty", "mountain", "empty", "empty"},
                    {"empty", "empty", "empty", "tree", "empty", "empty", "empty", "empty", "empty", "empty"},
                    {"empty", "empty", "mountain", "empty", "basket", "empty", "empty", "tree", "empty", "empty"}
            }

    };


}
