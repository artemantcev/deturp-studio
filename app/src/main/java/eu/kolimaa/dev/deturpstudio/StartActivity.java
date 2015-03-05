package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final ListView mPlaylistView = (ListView) findViewById(R.id.playlist);
        ArrayList<Track> PlaylistTracks = new ArrayList<>();


        Track test = new Track();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_start, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_quitbutton:
                AppHelper.killApplication();
//            case R.id.action_add:

        }

        return super.onOptionsItemSelected(item);
    }
}