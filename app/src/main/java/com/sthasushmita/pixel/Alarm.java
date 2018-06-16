package com.example.sujal.pocketmed;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by sujal on 6/2/2017.
 */
public class Alarm extends AppCompatActivity {

    private static final String a="hello";

    private static final String tag = "DatePicker";
    private static final String TIME_PICKER = "time_picker";



    String[] title1=new String[]{"Calendar","Alarm","Camera"};

    int[] image1=new int[]{R.drawable.calendar,R.drawable.iconalarm,R.drawable.camera};

    ListView l;
    Intent i;
    private DatePickerDialog.OnDateSetListener mdateset;
    Button save;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_clock);
        l = (ListView) findViewById(R.id.grp);

        CustomAdapter1 adapter=new CustomAdapter1(Alarm.this, title1, image1);
        l.setAdapter(adapter);


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int Year = c.get(Calendar.YEAR);
                    int Month = c.get(Calendar.MONTH);
                    int Day = c.get(Calendar.DAY_OF_MONTH);

                   final DatePickerDialog datePickerDialog = new DatePickerDialog(Alarm.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, final int year,
                                              int monthOfYear, int dayOfMonth) {

                            Toast.makeText(getApplicationContext(), "DATE is checked", Toast.LENGTH_LONG).show();




                        }



                   }, Year, Month, Day);

                            datePickerDialog.show();

                }

                if (position == 1) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Alarm.this);

                    // Setting Dialog Title
                    alertDialog.setTitle("ALARM....");

                    // Setting Dialog Message
                    alertDialog.setMessage("Do you want to set alarm");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.notification);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // Write your code here to invoke YES event


                            Intent i = new Intent(Alarm.this, Edit.class);
                            startActivity(i);
                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            Toast.makeText(getApplicationContext(), "You clicked no.Returning back to homepage", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                }
                if (position == 2) {

                    Log.e("Hello", "d");
                    Intent imageIntent = new Intent(Alarm.this, IntentActivity.class);
                    startActivity(imageIntent);
                }


            }
        });
    }
}

 class CustomAdapter1 extends BaseAdapter {
    String[] title;

    int[] img;
    LayoutInflater inflator; //custom bata java file ma lyaune
    public CustomAdapter1(Context c,String[] t,int[] i)
    {
        title=t;
        img=i;
        inflator=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    //counts the string
    public int getCount() {
        return title.length;
    }

    @Override
    //
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) //listview
    {

        View vi=convertView;
        vi=inflator.inflate(R.layout.custom_list1,null); //my_grid
        TextView titleview=(TextView)vi.findViewById(R.id.text1);

        ImageView imageview=(ImageView)vi.findViewById(R.id.image);
        titleview.setText(title[position]);

        imageview.setImageResource(img[position]);
        return vi;
    }

}