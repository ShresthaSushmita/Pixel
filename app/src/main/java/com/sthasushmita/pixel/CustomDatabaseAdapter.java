package com.example.sujal.pocketmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sujal on 6/2/2017.
 */
public class CustomDatabaseAdapter  extends BaseAdapter {
    LayoutInflater inflater;
    DatabaseAdapter dbAdapter;
    int [] ids;
    String [] names, rolls;
    int res;
    public CustomDatabaseAdapter(Context c, int datacontent, int[] i, String[] r, String[] n) {
        ids = i;
        res = datacontent;
        names = n;
        rolls = r;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbAdapter = new DatabaseAdapter(c);
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.database_content, null);
        }
        TextView tvName = (TextView)convertView.findViewById(R.id.listType);
        TextView tvRoll = (TextView)convertView.findViewById(R.id.listTime);
        Button edit = (Button)convertView.findViewById(R.id.btnEdit);
        Button delete= (Button)convertView.findViewById(R.id.btnDelete);
        tvRoll.setText(rolls[position]);
        tvName.setText(names[position]);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstFragment.obj.nameT.setText(names[position]);
                FirstFragment.obj.rollT.setText(rolls[position]);
                FirstFragment.obj.idT.setText(ids[position]+"");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deleteRowById(ids[position]+"");
                FirstFragment.obj.displayListView();
            }
        });
        return convertView;
    }
}
