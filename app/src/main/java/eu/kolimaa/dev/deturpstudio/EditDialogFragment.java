package eu.kolimaa.dev.deturpstudio;


import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EditDialogFragment extends DialogFragment {

    private boolean isNewTrack;

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

//        Button saveButton = (Button) getView().findViewById(R.id.save_button);
//        saveButton.setOnClickListener(this);

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
