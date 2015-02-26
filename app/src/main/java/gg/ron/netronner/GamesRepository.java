package gg.ron.netronner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.emaze.dysfunctional.Strings;

public class GamesRepository extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DATABASE = "netronner";
    public static final String GAMES_TABLE = "games";
    private static final String CREATE_DDL = Strings.interpose(new String[]{
            "CREATE TABLE",
            GAMES_TABLE,
            "(",
            " id INTEGER,",
            " timestamp INTEGER,", //same as id for now
            " east TEXT,",
            " south TEXT,",
            " west TEXT,",
            " north TEXT,",
            ");"}, " ");

    public GamesRepository(Context context) {
        super(context, DATABASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1) {
            db.delete(GAMES_TABLE, null, null); // delete everything on draft versions updates
        }
    }
}
