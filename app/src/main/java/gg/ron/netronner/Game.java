package gg.ron.netronner;

import java.util.Date;

public class Game {

    public Date started;
    public String eastPlayer;
    public String southPlayer;
    public String westPlayer;
    public String northPlayer;

    public static Game of(Date started, String eastPlayer, String southPlayer, String westPlayer, String northPlayer) {
        final Game g = new Game();
        g.started = started;
        g.eastPlayer = eastPlayer;
        g.southPlayer = southPlayer;
        g.westPlayer = westPlayer;
        g.northPlayer = northPlayer;
        return g;
    }


}
