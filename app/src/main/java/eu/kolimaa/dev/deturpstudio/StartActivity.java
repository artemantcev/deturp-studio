package eu.kolimaa.dev.deturpstudio;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class StartActivity extends ActionBarActivity implements PlaylistFragment.OnToolbarListener {

    private PlaylistFragment cToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        PlaylistFragment cToolbar = new PlaylistFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.layout.activity_start, cToolbar);
        fragmentTransaction.commit();

    }

    @Override
    public void onToolbarInteraction(Uri uri) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_start, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_quitbutton) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}