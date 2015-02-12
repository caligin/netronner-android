package gg.ron.netronner;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class GamesTest extends ActivityInstrumentationTestCase2<GamesActivity> {

    public GamesTest() {
        super(GamesActivity.class);
    }

    public void testMenuNewGameButtonStartsNewGameActivity() throws Exception {
        final Activity activity = getActivity();
        final Instrumentation.ActivityMonitor newGameStarted = getInstrumentation().addMonitor(NewGame.class.getName(), null, false);
        final TextView newGameButton = (TextView) activity.findViewById(R.id.action_new_game);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newGameButton.performClick();
            }
        });
        final NewGame startedActivity = (NewGame) newGameStarted.waitForActivityWithTimeout(5000);
        assertNotNull(startedActivity);
        getInstrumentation().removeMonitor(newGameStarted);
    }
}
