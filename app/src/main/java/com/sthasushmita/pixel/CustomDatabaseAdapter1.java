package com.example.sujal.pocketmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sujal on 6/4/2017.
 */
public class CustomDatabaseAdapter1 extends BaseAdapter {
    LayoutInflater inflater;
    DatabaseAdapter1 dbAdapter;
    int[] ids;
    String [] fullname,email,username,password,cpassword;
    int res;
    public CustomDatabaseAdapter1(Context c, int datacontent, int[] i, String[] fn, String[] e, String[] un, String[] p, String[] cp) {
        ids = i;
        res = datacontent;
        fullname= fn;
        email = e;
        username= un;
        password= p;
        cpassword= cp;
        inflater= (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbAdapter= new DatabaseAdapter1(c);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity, null);
        }
        TextView tvName = (TextView)convertView.findViewById(R.id.listName);
        TextView tvEmail = (TextView)convertView.findViewById(R.id.listEmail);
        TextView tvUsername = (TextView)convertView.findViewById(R.id.listUsername);
        TextView tvPassword = (TextView)convertView.findViewById(R.id.listPassword);
        TextView tvCpassword = (TextView)convertView.findViewById(R.id.listCPassword);

        tvUsername.setText(username[position]);
        tvName.setText(fullname[position]);
        tvEmail.setText(email[position]);
        tvPassword.setText(password[position]);
        tvCpassword.setText(cpassword[position]);

        return convertView;
    }
}

