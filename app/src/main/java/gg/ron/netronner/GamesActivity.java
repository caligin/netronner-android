package gg.ron.netronner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GamesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        final ListView gamesListView = (ListView) findViewById(R.id.games_list);
        final GamesArrayAdapter adapter = new GamesArrayAdapter(this);
        gamesListView.setAdapter(adapter);
        adapter.add(Game.of(new Date(), "foo", "bar", "sig", "mik"));
        adapter.add(Game.of(new Date(), "ang", "bid", "pel", "ala"));
        adapter.add(Game.of(new Date(), "inu", "emm", "mai", "dan"));
        adapter.add(Game.of(new Date(), "foo", "bar", "sig", "mik"));
        adapter.add(Game.of(new Date(), "ang", "bid", "pel", "ala"));
        adapter.add(Game.of(new Date(), "inu", "emm", "mai", "dan"));
        adapter.add(Game.of(new Date(), "foo", "bar", "sig", "mik"));
        adapter.add(Game.of(new Date(), "ang", "bid", "pel", "ala"));
        adapter.add(Game.of(new Date(), "inu", "emm", "mai", "dan"));
        adapter.add(Game.of(new Date(), "foo", "bar", "sig", "mik"));
        adapter.add(Game.of(new Date(), "ang", "bid", "pel", "ala"));
        adapter.add(Game.of(new Date(), "inu", "emm", "mai", "dan"));
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

    public static class GamesArrayAdapter extends BaseAdapter {

        private final Context context;
        private final List<Game> games;

        public GamesArrayAdapter(Context context) {
            this.context = context;
            this.games = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return games.size();
        }

        @Override
        public Game getItem(int position) {
            return games.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void add(Game game) {
            this.games.add(game);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Game item = getItem(position);
            final View itemView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.game_list_item, parent, false);
            ((TextView) itemView.findViewById(R.id.game_date_label)).setText(item.started.toString());
            ((TextView) itemView.findViewById(R.id.player_1_name)).setText(item.player1);
            ((TextView) itemView.findViewById(R.id.player_2_name)).setText(item.player2);
            ((TextView) itemView.findViewById(R.id.player_3_name)).setText(item.player3);
            ((TextView) itemView.findViewById(R.id.player_4_name)).setText(item.player4);
            return itemView;
        }


    }
}
