package eu.kolimaa.dev.deturpstudio;


import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EditDialogFragment extends DialogFragment implements View.OnClickListener {

    public EditDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editdialog, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button saveButton = (Button) getView().findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d("FRAGMENT", "test button");
    }

    public void setTitle(String title) {
        //TODO: a dynamic title setter which indicates the state (e.g. new track/edit track)
    }


}
