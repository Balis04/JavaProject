package hu.elte.progtech.jdbc.mysql.examples.macilaci.gui;

import java.awt.*;

/*
* állandó adattagok
* */

public final class MaciUIConstants  {

    private MaciUIConstants(){}

    public static final int GAME_WIDTH = 900;
    public static final int GAME_HEIGHT = 700;

    public static final Color BACKGROUND_COLOR = new Color(0xDDEEFF);

    public static final int SCORE_POS_X = 600;
    public static final int SCORE_POS_Y = 70;

    public static final Color RANGER_COLOR = new Color(255, 0, 0); // Vadőr: piros
    public static final Color BEAR_COLOR = new Color(139, 69, 19); // Medve: barna
    public static final Color MOUNTAIN_COLOR = new Color(128, 128, 128); // Hegy: szürke
    public static final Color TREE_COLOR = new Color(0, 128, 0); // Fa: zöld
    public static final Color EMPTY_COLOR = new Color(0, 0, 255); // Üres: kék
    public static final Color BASKET_COLOR = new Color(255, 255, 0); // Kosár: sárga



    public static final int BLOCK_SIZE = 50;

    public static final Color TEXT_COLOR = Color.BLACK;
    public static final Font MAIN_FONT = new Font("Monospaced", Font.BOLD, 48);
    public static final Font SMALL_FONT = MAIN_FONT.deriveFont(Font.BOLD, 18);

    public static final Stroke LARGE_STROKE = new BasicStroke(5);
    public static final Stroke SMALL_STROKE = new BasicStroke(2);

    public static final Color SQUARE_BORDER = Color.WHITE;
    public static final Color GRID_COLOR = new Color(0xBECFEA);
    public static final Color GRID_BORDER_COLOR = new Color(0x7788AA);

    public static final Rectangle GRID_RECTANGLE = new Rectangle(46, 47, 508, 508);
    public static final Rectangle PREVIEW_RECTANGLE = new Rectangle(600, 150, 250, 400);

    public static final int TOP_MARGIN = 50;
    public static final int LEFT_MARGIN = 50;


    public static final Color[] COLORS = {
            RANGER_COLOR,
            BEAR_COLOR,
            MOUNTAIN_COLOR,
            TREE_COLOR,
            EMPTY_COLOR,
            BASKET_COLOR
    };
}