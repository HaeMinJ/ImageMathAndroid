package com.haemin.imagemathstudent.View.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.haemin.imagemathstudent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QAFragment extends Fragment {


    public QAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qa, container, false);
    }

}
