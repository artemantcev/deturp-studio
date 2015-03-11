package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;

public class StartActivity extends Activity {

    ArrayList<Track> playListTracks;
    TrackListAdapter trackListAdapter;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        fm = getFragmentManager();

        playListTracks = new ArrayList<>();
        trackListAdapter = new TrackListAdapter(getApplicationContext(), playListTracks);

        final ListView playlistView = (ListView) findViewById(R.id.playlist);
        playlistView.setAdapter(trackListAdapter); //setting the adapter for playlist view

        //TODO: remove stubs block
        Uri pathStub = Uri.parse("");
        Track trackStub = new Track("Death in June - Last Europa Kiss (Live in Tel Aviv)", pathStub, getApplicationContext());
        Track trackStub1 = new Track("Troum - Mad as the Mist and Snow #2", pathStub, getApplicationContext());
        playListTracks.add(trackStub);
        playListTracks.add(trackStub1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_start, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_quitbutton:
                AppHelper.killApplication();
            case R.id.action_add:
                (new EditDialogFragment()).show(fm, "newTrackDialog");

        }

        return super.onOptionsItemSelected(item);
    }
}