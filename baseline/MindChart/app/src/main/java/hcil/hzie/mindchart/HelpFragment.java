package hcil.hzie.mindchart;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class HelpFragment extends DialogFragment {
    MaterialButton cancelBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        cancelBtn = view.findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                HelpFragment.this.dismiss();
            }
        });
        return view;
    }
}