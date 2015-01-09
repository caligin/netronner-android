package gg.ron.netronner;

import java.util.Date;

public class Game {

    public static enum Type {
        HANCHAN, TONPUUSEN;
    }

    public String player1;
    public String player2;
    public String player3;
    public String player4;
    public Type gameType;
    public Date started;

}
