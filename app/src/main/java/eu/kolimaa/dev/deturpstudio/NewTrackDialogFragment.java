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

public class NewTrackDialogFragment extends DialogFragment {

    private String currentTrackName;

    private Uri currentFilePath;

    private int trackPosition;

    private TextView trackNameView;

    private Button saveButton;


    public NewTrackDialogFragment() {

    }

    public static NewTrackDialogFragment newInstance() {
        NewTrackDialogFragment f = new NewTrackDialogFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(getActivity().getString(R.string.action_add));

        return inflater.inflate(R.layout.fragment_new_track_dialog, container, false);
    }

    private Intent getUriPathIntent() {

        Intent intent = new Intent();
        intent.setType("audio/mp3");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        return intent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View cancelButtonView = getView().findViewById(R.id.cancel_button);
        View saveButtonView = getView().findViewById(R.id.save_button);
        View fileButtonView = getView().findViewById(R.id.file_button);

        saveButton = (Button) saveButtonView;

        saveButton.setEnabled(false);

        trackNameView = (TextView) getView().findViewById(R.id.trackname_edit_text);

        fileButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(getUriPathIntent(), 1);
            }
        });

        cancelButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View cancelButton) {
                dismiss();
            }
        });

        saveButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View saveButton) {
                String name = trackNameView.getText().toString();
                ((ITrackOperator)getActivity()).onCreateNewTrack(name, getCurrentFilePath());
                dismiss();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;

    }

    public void setCurrentName(String currentTrackName) {
        this.currentTrackName = currentTrackName;

    }

    public void setCurrentFilePath(Uri currentFilePath) {
        this.currentFilePath = currentFilePath;

    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public String getCurrentName() {
        return currentTrackName;
    }

    public Uri getCurrentFilePath() {
        return currentFilePath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (data != null) {
                Uri uri = data.getData();
                setCurrentFilePath(uri);
                saveButton.setEnabled(true);
            } else {
                saveButton.setEnabled(false);
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        trackNameView = null;
        saveButton = null;

        super.onDestroy();
    }

}
