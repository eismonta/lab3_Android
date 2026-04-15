package com.example.lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        tv = v.findViewById(R.id.textResultFragment);
        Button cancel = v.findViewById(R.id.buttonCancel);

        cancel.setOnClickListener(view -> ((MainActivity)getActivity()).clearAll());
        return v;
    }

    public void updateLabel(String s) {
        if (tv != null) tv.setText(s);
    }
}