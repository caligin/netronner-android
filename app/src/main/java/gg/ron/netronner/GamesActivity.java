package gg.ron.netronner;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GamesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        final ListView gamesListView = (ListView) findViewById(R.id.games_list);
        final GamesArrayAdapter adapter = new GamesArrayAdapter(this);
        gamesListView.setAdapter(adapter);
        adapter.add(Game.of(Game.Type.HANCHAN, new Date(), "foo", "bar", "sig", "mik"));
        adapter.add(Game.of(Game.Type.HANCHAN, new Date(), "ang", "bid", "pel", "ala"));
        adapter.add(Game.of(Game.Type.TONPUUSEN, new Date(), "inu", "emm", "mai", "dan"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        public void add(Game game){
            this.games.add(game);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Game item = getItem(position);
            final View itemView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.game_list_item, parent, false);
            ((ImageView)itemView.findViewById(R.id.south_round_icon)).setVisibility(item.gameType == Game.Type.HANCHAN ? View.VISIBLE : View.INVISIBLE);
            ((TextView)itemView.findViewById(R.id.game_type_label)).setText(item.gameType == Game.Type.HANCHAN ? "Hanchan" : "Tonpuusen");
            ((TextView)itemView.findViewById(R.id.game_date_label)).setText(item.started.toString());
            ((TextView)itemView.findViewById(R.id.player_1_name)).setText(item.player1);
            ((TextView)itemView.findViewById(R.id.player_2_name)).setText(item.player2);
            ((TextView)itemView.findViewById(R.id.player_3_name)).setText(item.player3);
            ((TextView)itemView.findViewById(R.id.player_4_name)).setText(item.player4);
            return itemView;
        }


    }
}
