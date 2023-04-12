package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FM1 extends Fragment {

    Button button;
    EditText editText;
    private FragmentManager fragmentManager;
    private FM2 fragment2;
    private FM3 fragment3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fm1, container, false);

        Button btnSendToFM3 = view.findViewById(R.id.btnSendToFragment3);
        EditText editText = view.findViewById(R.id.edtContent);
        Button btnGoToTabActivity = view.findViewById(R.id.btnGoToTabActivity);


        fragmentManager = requireActivity().getSupportFragmentManager();

        Button button_fm2 = view.findViewById(R.id.btnGoToFragment2);
        button_fm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment2 = new FM2();
                fragmentManager.beginTransaction().replace(R.id.FM2_layout, fragment2).commit();
            }
        });

        Button button_fm3 = view.findViewById(R.id.btnGoToFragment3);
        button_fm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment3 = new FM3();
                fragmentManager.beginTransaction().replace(R.id.FM2_layout, fragment3).commit();
            }
        });

        btnSendToFM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                FM3 fm3 = (FM3) fragmentManager.findFragmentById(R.id.FM2_layout);
                fm3.textView.setText(text);

            }
        });

        btnGoToTabActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Tab.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
