package com.example.sujal.pocketmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sujal on 6/3/2017.
 */
public class InfoAdapter extends BaseAdapter {
    String[] med, desc;
    LayoutInflater inflater;
    Context ctx;
    public InfoAdapter(Context c, String[] m, String[] d) {
        ctx = c;
        med= m;
        desc = d;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return med.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi;
        vi = inflater.inflate(R.layout.infoitem,null);
        TextView titleTeam = (TextView)vi.findViewById(R.id.titleMed);
        TextView titlePoint = (TextView)vi.findViewById(R.id.titleDesc);
        titleTeam.setText(med[position]);
        titlePoint.setText(desc[position]);
        return vi;
    }
}



