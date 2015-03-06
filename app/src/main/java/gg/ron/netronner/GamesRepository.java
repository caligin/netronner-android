package gg.ron.netronner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.emaze.dysfunctional.Strings;

public class GamesRepository extends SQLiteOpenHelper {

    public static final String GAMES_TABLE = "games";

    private static final String CREATE_DDL = Strings.interpose(new String[]{
            "CREATE TABLE",
            GAMES_TABLE,
            "(",
            Fields._ID, "INTEGER,",
            Fields.timestamp, "INTEGER,",
            Fields.east, "TEXT,",
            Fields.south, "TEXT,",
            Fields.west, "TEXT,",
            Fields.north, "TEXT",
            ");"}, " ");
    private static final int DB_VERSION = 1;
    private static final String DATABASE = "netronner";

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

    public Cursor list() {
        return this.getReadableDatabase().rawQuery(String.format("select * from %s order by %s DESC;", GAMES_TABLE, Fields.timestamp), new String[]{});
    }

    public static class Fields {
        public static String _ID = "_id";
        public static String timestamp = "timestamp";
        public static String east = "east";
        public static String south = "south";
        public static String west = "west";
        public static String north = "north";
    }

}
