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

import com.squareup.otto.Bus;

import java.util.ArrayList;

public class EditDialogFragment extends DialogFragment {

    private boolean isNewTrack;

    private String currentTrackName;

    private Uri currentFilePath;

    private int trackPosition;

    private TextView trackNameView;

    private Button saveButton;


    public EditDialogFragment() {

    }

    public static EditDialogFragment newInstance(boolean isNewTrack) {
        EditDialogFragment f = new EditDialogFragment();
        f.isNewTrack = isNewTrack;

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(getEditDialogTitle(isNewTrack));

        return inflater.inflate(R.layout.fragment_editdialog, container, false);
    }

    private Intent getUriPathIntent() {

        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        return intent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View cancelButtonView = getView().findViewById(R.id.cancel_button);
        View saveButtonView = getView().findViewById(R.id.save_button);
        View fileButtonView = getView().findViewById(R.id.file_button);

        Button fileButton = (Button) fileButtonView; //its label should be changing
        saveButton = (Button) saveButtonView;

        saveButton.setEnabled(false);

        trackNameView = (TextView) getView().findViewById(R.id.trackname_edit_text);

        fileButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(getUriPathIntent(), 1); //FIXME: onActivityResult() doesn't get called
            }
        });

        cancelButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View cancelButton) {
                dismiss();
            }
        });

        if (isNewTrack) {
            saveButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View saveButton) {
                    String name = trackNameView.getText().toString();
                    ((TrackOperator)getActivity()).onCreateNewTrack(name, getCurrentFilePath());
                    dismiss();
                }
            });
        } else {

            fileButtonView.setVisibility(View.INVISIBLE);

            saveButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View saveButton) {
                    String newName = trackNameView.getText().toString();
                    ((TrackOperator)getActivity()).onEditExistingTrack(newName, getTrackPosition());
                    dismiss();
                }
            });
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        if (!isNewTrack) {
            trackNameView.setText(getCurrentName());
        }

    }

    public String getEditDialogTitle(boolean isNewTrack) {

        String title;

        if (isNewTrack) {
            title = getActivity().getString(R.string.action_add);
        } else {
            title = getActivity().getString(R.string.action_edit);
        }

        return title;

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


    public interface TrackOperator {

        public void onCreateNewTrack(String name, Uri path);

        public void onEditExistingTrack(String newName, int position);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //FIXME: should be tested

        Log.d("EditDialogFragment.onActivityResult", "ok");

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

}
