package com.example.customdialog;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DataEntryDialog extends DialogFragment{
    public static final String PERSON_KEY = "PERSON_KEY";
    private EditText txtFirstName, txtLastName, txtAge;
    private DataEntryListener mListener;

    public static DataEntryDialog newInstance(Person person){
        Bundle args = new Bundle();
        args.putParcelable(PERSON_KEY, person);

        DataEntryDialog fragment = new DataEntryDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DataEntryListener) context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.dialog_data_entry,
                container, false);
        txtFirstName = (EditText) rootview.findViewById(R.id.textFirstName);
        txtLastName = (EditText) rootview.findViewById(R.id.textLastName);
        txtAge = (EditText) rootview.findViewById(R.id.textAge);

        Person person = getArguments().getParcelable(PERSON_KEY);
        txtFirstName.setText(person.getFirstName());
        txtLastName.setText(person.getLastName());
        txtAge.setText(String.valueOf(person.getAge()));

        Button btnOk = (Button) rootview.findViewById(R.id.textBtnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });
        Button btnCancel = (Button) rootview.findViewById(R.id.textBtnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootview;
    }

    private void saveData() {
        Person person = new Person();
        person.setFirstName(txtFirstName.getText().toString());
        person.setLastName(txtLastName.getText().toString());
        person.setAge( Integer.valueOf( txtAge.getText().toString()) );

        mListener.onDataEntryComplete(person);
        dismiss();
    }

    public interface DataEntryListener{
        void onDataEntryComplete(Person person);

    }
}
