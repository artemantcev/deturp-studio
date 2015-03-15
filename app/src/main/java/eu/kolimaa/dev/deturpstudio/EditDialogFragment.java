package eu.kolimaa.dev.deturpstudio;



import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditDialogFragment extends DialogFragment {

    private boolean isNewTrack;
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

        final View saveButtonView = (View) getView().findViewById(R.id.save_button);

        if (isNewTrack) {
            saveButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View saveButton) {}
            });
        } else {
            saveButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View saveButton) {}
            });
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


}
