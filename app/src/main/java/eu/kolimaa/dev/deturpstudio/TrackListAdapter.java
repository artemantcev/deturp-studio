package eu.kolimaa.dev.deturpstudio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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

        return tracks.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = lInflater.inflate(R.layout.track, parent, false);
        }

        final int trackPosition = position;

        TextView trackNumber = (TextView) view.findViewById(R.id.tracknumber);
        TextView trackName = (TextView) view.findViewById(R.id.trackname);
//        Button removeTrackButton = (Button) view.findViewById(R.id.button_edit);

        trackNumber.setText(Integer.toString(position+1));
        trackName.setText(tracks.get(position).getTrackName());

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
