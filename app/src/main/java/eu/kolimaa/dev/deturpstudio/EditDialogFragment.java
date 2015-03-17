package eu.kolimaa.dev.deturpstudio;



import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditDialogFragment extends DialogFragment {

    private boolean isNewTrack;

    private String currentTrackName;

    private Uri currentFilePath;

    private int trackPosition;

    private TextView trackNameView;

    private Button trackFileUploadView;


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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View cancelButtonView = getView().findViewById(R.id.cancel_button);
        View saveButtonView = getView().findViewById(R.id.save_button);
        trackNameView = (TextView) getView().findViewById(R.id.trackname_edit_text);

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
                    Uri path = Uri.parse("");
                    ((TrackOperator)getActivity()).onCreateNewTrack(name, path);
                    dismiss();
                }
            });
        } else {
            saveButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View saveButton) {
                    String newName = trackNameView.getText().toString();
                    Uri newPath = Uri.parse("");
                    ((TrackOperator)getActivity()).onEditExistingTrack(newName, newPath, getTrackPosition());
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

    public EditDialogFragment setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;

        return this;
    }

    public EditDialogFragment setCurrentName(String currentTrackName) {
        this.currentTrackName = currentTrackName;

        return this;
    }

    public EditDialogFragment setCurrentFilePath(Uri currentFilePath) {
        this.currentFilePath = currentFilePath;

        return this;
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

        public void onEditExistingTrack(String newName, Uri newPath, int position);

    }


}
