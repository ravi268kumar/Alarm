package com.example.ravi.alarm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    TextView update_text;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        alarmTimePicker = (TimePicker) findViewById(R.id.timepicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        update_text =(TextView) findViewById(R.id.update_text);

        final Calendar  calendar = Calendar.getInstance();


        Button alarm_on =(Button) findViewById(R.id.alarm_on);

        final Intent my_Intent = new Intent(this.context,AlarmReceiver.class);



        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

                int hour = alarmTimePicker.getHour();
                int minute = alarmTimePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (hour > 12){
                    hour_string = String.valueOf(hour - 12);

                }

                if (minute<10){

                    minute_string = "0" + String.valueOf(minute);
                }


                set_alarm_text("Alarm set to : " + hour_string + ":" + minute_string);

                pendingIntent =PendingIntent.getBroadcast(MainActivity.this,0,
                        my_Intent,pendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent
                );

            }
        });

        Button alarm_off =(Button) findViewById(R.id.alarm_off);

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text("Alarm_off");
                alarmManager.cancel(pendingIntent);
            }
        });




    }

    private void set_alarm_text(String output) {

        update_text.setText(output);

    }

//
//    public void OnToggleClicked(View view)
//    {
//        long time;
//        if (((ToggleButton) view).isChecked())
//        {
//            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
//            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
//            Intent intent = new Intent(this, AlarmReceiver.class);
//            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
//            if(System.currentTimeMillis()>time)
//            {
//                if (calendar.AM_PM == 0)
//                    time = time + (1000*60*60*12);
//                else
//                    time = time + (1000*60*60*24);
//            }
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
//        }
//        else
//        {
//            alarmManager.cancel(pendingIntent);
//            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
//        }
//    }
}