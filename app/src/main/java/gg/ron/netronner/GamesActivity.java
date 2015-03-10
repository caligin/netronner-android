package gg.ron.netronner;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;


public class GamesActivity
        extends Activity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int GAMES_LOADER_ID = 0;
    private SimpleCursorAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        final ListView gamesListView = (ListView) findViewById(R.id.games_list);
        adapter = makeAdapter();
        gamesListView.setAdapter(adapter);
        getLoaderManager().initLoader(GAMES_LOADER_ID, null, this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!getLoaderManager().getLoader(GAMES_LOADER_ID).isStarted()) {
            getLoaderManager().restartLoader(GAMES_LOADER_ID, null, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_game) {
            startActivity(new Intent(this, NewGame.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            @Override
            public Cursor loadInBackground() {
                return new GamesRepository(GamesActivity.this).list();
            }

            @Override
            protected void onStartLoading() {
                // won't load without this. TODO investigate, i copied from kcc without remembering why
                super.onStartLoading();
                if (isStarted()) {
                    forceLoad();
                }
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // do nothing?
    }

    private SimpleCursorAdapter makeAdapter() {
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.game_list_item,
                null,
                new String[]{GamesRepository.Fields.timestamp, GamesRepository.Fields.east, GamesRepository.Fields.south, GamesRepository.Fields.west, GamesRepository.Fields.north},
                new int[]{R.id.game_date_label, R.id.player_1_name, R.id.player_2_name, R.id.player_3_name, R.id.player_4_name},
                0);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == R.id.game_date_label) {
                    final Date date = new Date(Long.parseLong(cursor.getString(columnIndex)));
                    ((TextView) view).setText(DateFormat.getDateTimeInstance().format(date));
                    return true;
                }
                return false;
            }
        });
        return adapter;
    }

}
