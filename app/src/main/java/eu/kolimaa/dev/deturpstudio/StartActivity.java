package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.net.Uri;
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

        final ListView playlistView = (ListView) findViewById(R.id.playlist);
        ArrayList<Track> playlistTracks = new ArrayList<>();


        Uri demo = Uri.parse("https://archive.org/download/testmp3testfile/mpthreetest.mp3");
        Track test = new Track("1", demo, getApplicationContext());
        playlistTracks.add(test);

        TrackListAdapter testAdapter = new TrackListAdapter(getApplicationContext(), playlistTracks);

//        ViewGroup

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