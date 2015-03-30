package eu.kolimaa.dev.deturpstudio;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RenameTrackDialogFragment extends DialogFragment {

    private String currentTrackName;

    private int trackPosition;

    private TextView trackNameView;

    private Button saveButton;


    public RenameTrackDialogFragment() {

    }

    public static RenameTrackDialogFragment newInstance() {
        RenameTrackDialogFragment f = new RenameTrackDialogFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(getActivity().getString(R.string.action_edit));

        return inflater.inflate(R.layout.fragment_rename_track_dialog, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View cancelButtonView = getView().findViewById(R.id.cancel_button);
        View saveButtonView = getView().findViewById(R.id.save_button);

        saveButton = (Button) saveButtonView;

        trackNameView = (TextView) getView().findViewById(R.id.trackname_edit_text);

        cancelButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View cancelButton) {
                dismiss();
            }
        });

        saveButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View saveButton) {
                String newName = trackNameView.getText().toString();
                ((ITrackOperator)getActivity()).onEditExistingTrack(newName, getTrackPosition());
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        trackNameView.setText(getCurrentName());

    }

    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;

    }

    public void setCurrentName(String currentTrackName) {
        this.currentTrackName = currentTrackName;

    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public String getCurrentName() {
        return currentTrackName;
    }

}