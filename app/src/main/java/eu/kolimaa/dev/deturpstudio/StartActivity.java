package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class StartActivity extends Activity implements ITrackOperator,
        AdapterView.OnItemLongClickListener {

    ArrayList<Track> playListTracks;

    TrackListAdapter trackListAdapter;

    NewTrackDialogFragment newTrackDialogFragment;
    RenameTrackDialogFragment renameTrackDialogFragment;

    private ToggleButton playToggleButton;
    private Button stopButton;

    FragmentManager fm = getFragmentManager();

    private Intent getExplicitMusicServiceIntent() {
        Intent service = new Intent(this, MusicService.class);
        return service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        if (playListTracks == null) {
            playListTracks = new ArrayList<>();
        }

        MusicService.getServiceBus().register(this);
        startService(getExplicitMusicServiceIntent());

        trackListAdapter = new TrackListAdapter(getApplicationContext(), playListTracks);

        final ListView playlistView = (ListView) findViewById(R.id.playlist);
        playToggleButton = (ToggleButton) findViewById(R.id.toggle_button_play);
        stopButton = (Button) findViewById(R.id.button_stop);

        enableControls(false);

        playToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MusicService.getControllerInstance().play();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playToggleButton.setChecked(false);
                MusicService.getControllerInstance().stop();
            }
        });

        playlistView.setAdapter(trackListAdapter); //setting the adapter for playlist view
        playlistView.setOnItemLongClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {


        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

        Track currentTrack = playListTracks.get(position);

        renameTrackDialogFragment = RenameTrackDialogFragment.newInstance();

        renameTrackDialogFragment.setTrackPosition(position);
        renameTrackDialogFragment.setCurrentName(currentTrack.getTrackName());

        renameTrackDialogFragment.show(fm, "renameTrackDialog");

        return true;
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
                if (newTrackDialogFragment == null) {
                    newTrackDialogFragment = NewTrackDialogFragment.newInstance();
                }
                newTrackDialogFragment.show(fm, "newTrackDialog");

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateNewTrack(String name, Uri path) {
        Track track = new Track(name, path, getApplicationContext());
        playListTracks.add(track);
        newTrackDialogFragment = null;
        enableControls(true);

        trackListAdapter.notifyDataSetChanged();
    }

    public void enableControls(boolean isEnabled) {
        playToggleButton.setEnabled(isEnabled);
        stopButton.setEnabled(isEnabled);
    }

    @Override
    public void onEditExistingTrack(String newName, int position) {
        Track currentTrack = playListTracks.get(position);

        currentTrack.setTrackName(newName);

        trackListAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onServiceStarted(ServiceEvent event) {
        MusicService.getControllerInstance().setTracks(playListTracks);
    }

    @Subscribe
    public void onTrackRemove(TrackRemoveEvent event) {

        playToggleButton.setChecked(false);

        if (playListTracks.isEmpty()) {
            enableControls(false);
        }
    }

}