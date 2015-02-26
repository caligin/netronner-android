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
                final Game g = Game.of(
                        new Date(),
                        eastPlayer.getText().toString(),
                        southPlayer.getText().toString(),
                        westPlayer.getText().toString(),
                        northPlayer.getText().toString());
                final GamesRepository games = new GamesRepository(NewGame.this);
                final ContentValues values = new ContentValues(6);
                values.put("id", g.started.getTime());
                values.put("timestamp", g.started.getTime());
                values.put("east", g.eastPlayer);
                values.put("south", g.southPlayer);
                values.put("west", g.westPlayer);
                values.put("north", g.northPlayer);
                games.getWritableDatabase().insert(GamesRepository.GAMES_TABLE, null, values);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
        return true;
    }

}
