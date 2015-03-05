package eu.kolimaa.dev.deturpstudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class TrackListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Track> tracks;
    LayoutInflater lInflater;

    public TrackListAdapter(Context context, ArrayList<Track> tracks) {

        this.context = context;
        this.tracks = tracks;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {
            view = lInflater.inflate(R.layout.track, parent, false);
        }

        //should be implemented after a Track.class

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return tracks.get(position);
    }

}
