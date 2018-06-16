package com.example.sujal.pocketmed;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by sujal on 6/2/2017.
 */
public class Edit extends FragmentActivity {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    Boolean s = true;
    private static final int MY_NOTIFICATION_ID = 1;
    NotificationManager notificationManager;
    Notification myNotification;
    TextView txtview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ring);
        txtview=(TextView)findViewById(R.id.textView4);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                Intent myIntent = new Intent(Edit.this, ClockActivity.class);

                pendingIntent = PendingIntent.getBroadcast(Edit.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOUR, timeHour);
                bundle.putInt(MyConstants.MINUTE, timeMinute);



                MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, MyConstants.TIME_PICKER);
                transaction.commit();

            }
        });
        Button stopAlarm = (Button) findViewById(R.id.button2);
        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Edit.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Delete...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want delete this?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.image);


                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        RingtoneManager ringMan = new RingtoneManager(getApplicationContext());
                        ringMan.stopPreviousRingtone();

                        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        MediaPlayer mp= ClockActivity.mp;
                        mp.stop();
                        mp.release();


                    }
                });

                // Showing Alert Message
                alertDialog.show();

            }
        });

    }

    private class MyHandler extends Handler {


            @Override
            public void handleMessage (Message msg){
                Bundle bundle = msg.getData();
                timeHour = bundle.getInt(MyConstants.HOUR);
                timeMinute = bundle.getInt(MyConstants.MINUTE);

                setAlarm();
            }
        }
        private void setAlarm(){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timeHour);
            calendar.set(Calendar.MINUTE, timeMinute);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        private void cancelAlarm() {
            if (alarmManager!= null) {
                alarmManager.cancel(pendingIntent);

            }
        }
    }

