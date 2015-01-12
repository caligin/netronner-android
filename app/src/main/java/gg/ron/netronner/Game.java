package gg.ron.netronner;

import java.util.Date;

public class Game {

    public static enum Type {
        HANCHAN, TONPUUSEN;
    }

    public Type gameType;
    public Date started;
    public String player1;
    public String player2;
    public String player3;
    public String player4;

    public static Game of(Type gameType, Date started, String player1, String player2, String player3, String player4) {
        final Game g = new Game();
        g.gameType = gameType;
        g.started = started;
        g.player1 = player1;
        g.player2 = player2;
        g.player3 = player3;
        g.player4 = player4;
        return g;
    }



}
