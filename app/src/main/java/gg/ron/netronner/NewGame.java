package gg.ron.netronner;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;


public class NewGame extends Activity {

    private EditText eastPlayer;
    private EditText southPlayer;
    private EditText westPlayer;
    private EditText northPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        eastPlayer = (EditText) findViewById(R.id.new_player_east);
        southPlayer = (EditText) findViewById(R.id.new_player_south);
        westPlayer = (EditText) findViewById(R.id.new_player_west);
        northPlayer = (EditText) findViewById(R.id.new_player_north);
        final Button createButton = (Button) findViewById(R.id.button_create_new_game);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GamesRepository games = new GamesRepository(NewGame.this);
                final ContentValues values = new ContentValues(6);
                final Date started = new Date();
                values.put(GamesRepository.Fields._ID, started.getTime());
                values.put(GamesRepository.Fields.timestamp, started.getTime());
                values.put(GamesRepository.Fields.east, eastPlayer.getText().toString());
                values.put(GamesRepository.Fields.south, southPlayer.getText().toString());
                values.put(GamesRepository.Fields.west, westPlayer.getText().toString());
                values.put(GamesRepository.Fields.north, northPlayer.getText().toString());
                final long insert = games.getWritableDatabase().insert(GamesRepository.GAMES_TABLE, null, values);
                final int LOADING_FAILURE = -1;
                if (insert != LOADING_FAILURE) {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
        return true;
    }

}
