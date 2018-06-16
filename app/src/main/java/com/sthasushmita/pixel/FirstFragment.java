package com.example.sujal.pocketmed;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by sujal on 6/2/2017.
 */
public class FirstFragment extends Fragment {
View myView;


    //EditText type;
    //EditText time;
    //EditText cause;
    EditText nameT, rollT, idT;
    Button send, reset;

    //Button setRecord;
    //Button resetRecord;
    public static FirstFragment obj;
    DatabaseAdapter dbAdapter;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        //type = (EditText) myView.findViewById(R.id.editText);
        //time = (EditText) myView.findViewById(R.id.editText2);
        //cause = (EditText) myView.findViewById(R.id.editText3);
        // setRecord= (Button) myView.findViewById(R.id.button3);
        //setRecord= (Button) myView.findViewById(R.id.button3);
        nameT = (EditText) myView.findViewById(R.id.editText);
        rollT = (EditText) myView.findViewById(R.id.editText2);
        idT = (EditText) myView.findViewById(R.id.ids);
        send = (Button)myView.findViewById(R.id.button3);
        reset = (Button)myView.findViewById(R.id.button4);
        lv = (ListView)myView.findViewById(R.id.list);

        obj = this;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveToDatabase();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });


        dbAdapter = new DatabaseAdapter(getActivity());
        displayListView();
        return myView;
    }

    public void saveToDatabase() {
        ContentValues cv = new ContentValues();
        long id = 0;
        if (idT.getText().toString().equals("0")) {
            cv.put(DBConstant.ST_NAME, nameT.getText().toString());
            cv.put(DBConstant.ST_ROLL, rollT.getText().toString());
            id = dbAdapter.insertData(cv);
            showToast("Data Inserted");
        } else {
            cv.put(DBConstant.ST_NAME, nameT.getText().toString());
            cv.put(DBConstant.ST_ROLL, rollT.getText().toString());
            id = dbAdapter.updateRowById(idT.getText().toString(), cv);
            showToast("Row Updated");
        }
        if (id != -1) {
            nameT.setText("");
            rollT.setText("");
            idT.setText("0");
            displayListView();
        }
    }

    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    public void resetAll() {
        idT.setText("0");
        nameT.setText("");
        rollT.setText("");
    }

    public void displayListView() {
        Cursor c = dbAdapter.getAllData();
        int[] ids = new int[c.getCount()];
        String[] names = new String[c.getCount()];
        String[] rolls = new String[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            ids[i] = c.getInt(c.getColumnIndex(DBConstant._ID));
            names[i] = c.getString(c.getColumnIndex(DBConstant.ST_NAME));
            rolls[i] = c.getString(c.getColumnIndex(DBConstant.ST_ROLL));
            i++;
        }
        CustomDatabaseAdapter custmAdapter = new CustomDatabaseAdapter(getActivity(), R.layout.database_content, ids, rolls, names);
        lv.setAdapter(custmAdapter);
    }
}