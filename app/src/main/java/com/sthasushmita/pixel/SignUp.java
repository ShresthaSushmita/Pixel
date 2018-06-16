package com.example.sujal.pocketmed;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by sujal on 6/4/2017.
 */
public class SignUp extends Activity {
    EditText fullname,email,uname;
    EditText idm,pass1,pass2;
    Button b;
    ListView list;
    public static SignUp obj;
    DatabaseAdapter1 dbAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        obj =this;
        fullname=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        uname=(EditText)findViewById(R.id.editText3);
        pass1=(EditText)findViewById(R.id.editText4);
        pass2=(EditText)findViewById(R.id.editText5);
        list=(ListView)findViewById(R.id.listview);
        b=(Button)findViewById(R.id.b1);
        idm=(EditText)findViewById(R.id.ids);
        dbAdapter1 = new DatabaseAdapter1(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full=fullname.getText().toString();
                String em=email.getText().toString();
                String user=uname.getText().toString();
                String pa=pass1.getText().toString();
                String pas=pass2.getText().toString();


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if ((full.matches(""))&&(em.matches(""))&&(user.matches(""))&&(pa.matches(""))&&(pas.matches(""))){
                    fullname.setError("Please enter your fullname");
                    email.setError("Please enter your email");
                    uname.setError("Please enter your username");
                    pass1.setError("Please enter your password");
                    pass2.setError("Please enter your confirm password");
                    return;
                }
                else if(pa.length()<8){
                    pass1.setError("Please enter upto 8 characters");
                    return;
                }
                else if (!pa.equals(pas)){
                    pass2.setError("Password doesnot match");
                    return;
                }

                else if (em.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    saveToDatabasee();
                }
                saveToDatabasee();
                Intent i = new Intent(getApplicationContext(),Login.class);
                i.putExtra("username", user);
                i.putExtra("password",pa);
                startActivity(i);
            }
        });

    }

    public void saveToDatabasee() {
        ContentValues cv = new ContentValues();
        long id = 0;
        if (idm.getText().toString().equals("0")) {
            cv.put(DBConstants.MED_CPASSWORD, pass2.getText().toString());
            cv.put(DBConstants.MED_FULLNAME, fullname.getText().toString());
            cv.put(DBConstants.MED_EMAIL, email.getText().toString());
            cv.put(DBConstants.MED_PASSWORD, pass1.getText().toString());
            cv.put(DBConstants.MED_USERNAME, uname.getText().toString());
            id = dbAdapter1.insertData(cv);
            showToast("Data Inserted");

        }
        if (id != -1) {
            fullname.setText("");
            email.setText("");
            uname.setText("");
            pass1.setText("");
            pass2.setText("");
            idm.setText("0");
            displayListView();
        }
    }

    public void showToast(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void displayListView(){
        Cursor c1 = dbAdapter1.getAllDataa();
        int [] ids = new int[c1.getCount()];
        String [] names= new String[c1.getCount()];
        String [] emails = new String[c1.getCount()];
        String [] u= new String[c1.getCount()];
        String [] ps= new String[c1.getCount()];
        String [] cps= new String[c1.getCount()];

        int i = 0;
        while(c1.moveToNext()){
            ids[i] = c1.getInt(c1.getColumnIndex(DBConstants._ID));
            names[i] = c1.getString(c1.getColumnIndex(DBConstants.MED_FULLNAME));
            emails[i] = c1.getString(c1.getColumnIndex(DBConstants.MED_EMAIL));
            u[i] = c1.getString(c1.getColumnIndex(DBConstants.MED_USERNAME));
            ps[i] = c1.getString(c1.getColumnIndex(DBConstants.MED_PASSWORD));
            cps[i] = c1.getString(c1.getColumnIndex(DBConstants.MED_CPASSWORD));
            i++;
        }
        CustomDatabaseAdapter1 customAdapter = new CustomDatabaseAdapter1(this, R.layout.activity, ids, names,emails,u,ps,cps);
        list.setAdapter(customAdapter);
    }
}


