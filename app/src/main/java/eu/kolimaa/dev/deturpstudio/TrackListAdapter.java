package eu.kolimaa.dev.deturpstudio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TrackListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Track> tracks;
    private LayoutInflater lInflater;

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

        TextView trackNumberView = (TextView) view.findViewById(R.id.tracknumber);
        TextView trackNameView = (TextView) view.findViewById(R.id.trackname);
        TextView trackLengthView = (TextView) view.findViewById(R.id.tracklength);
        Button removeTrackButton = (Button) view.findViewById(R.id.button_edit);

        removeTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MusicService.getControllerInstance().onRemove(trackPosition);
                tracks.remove(trackPosition);
                if (tracks.isEmpty()) {
                    ((StartActivity) context).enableControls(false);
                }
                TrackListAdapter.this.notifyDataSetChanged();
            }
        });

        trackNumberView.setText(Integer.toString(position+1));
        trackNameView.setText(tracks.get(position).getTrackName());

        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

        trackLengthView.setText(formatter.format(new Date(tracks.get(position)
                .getTrackLengthInMsec())));

        notifyDataSetChanged();

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
