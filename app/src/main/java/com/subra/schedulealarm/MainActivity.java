package com.subra.schedulealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = ((TextView) findViewById(R.id.view_text));

        /*
        Here is a list of UI thread for Android:
        1. Activity.runOnUiThread(Runnable)
        2. View.post(Runnable)
        3. View.postDelayed(Runnable, long)
        */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.set(Calendar.HOUR_OF_DAY, 16);
                cal.set(Calendar.MINUTE, 14);
                cal.set(Calendar.SECOND, 30);
                //cal.set(2020, 9, 22, 15, 25, 10);

                startAlarm(cal);
                textView.setText("" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(cal.getTime()));
            }
        },1000);

    }

    private void startAlarm(Calendar cal) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (cal.before(Calendar.getInstance())) {
            //cal.add(Calendar.DATE, 1);
        }
        Objects.requireNonNull(alarmManager).setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    //Help: https://www.c-sharpcorner.com/article/creating-and-scheduling-alarms-in-android/
    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 9); //Month
        calendar.set(Calendar.DAY_OF_MONTH, 22); //Day
        calendar.set(Calendar.HOUR_OF_DAY, 2); //Hour
        calendar.set(Calendar.MINUTE, 9); //Minute
        calendar.set(Calendar.SECOND, 0); //Second
        calendar.set(Calendar.MILLISECOND, 0); //Millisecond
        calendar.set(Calendar.AM_PM, Calendar.PM);

        AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, NotificationPublisher.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // cal.add(Calendar.SECOND, 5);
        //mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        //mAlarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    //Help: https://developer.android.com/training/scheduling/alarms
    /*private void setAlarm() {
        int requestId = 0;
        Intent intent = new Intent(this, SampleBootReceiver.class);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, requestId, intent, PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent); //if intent exists then cancel it
        }

        // Hopefully your alarm will have a lower frequency than this! || every 30 minutes
        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR, AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);

        AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60 * 1000, alarmIntent);


        // Set the alarm to start at 2:30 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 30);
        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
        // setRepeating() lets you specify a precise custom interval--in this case, 20 minutes.
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 20, alarmIntent);


        ComponentName receiver = new ComponentName(this, SampleBootReceiver.class);
        this.getPackageManager().setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

    }*/

}