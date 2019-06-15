package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main3Activity extends AppCompatActivity {
    ToggleButton alarmToggle;
    private EditText edt_topic;
    private EditText edt_hour;
    private EditText edt_min;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        Intent notifyIntent = new Intent(this, MyReceiver.class);
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        String toastMessage;
                        if(isChecked){
                            /*long repeatInterval = 1*60*1000;
                            long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

//If the Toggle is turned on, set the repeating alarm with a 15 minute interval
                            if (alarmManager != null) {
                                alarmManager.setInexactRepeating
                                        (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                                triggerTime, repeatInterval, notifyPendingIntent);
                            }
                            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                    triggerTime, repeatInterval,
                                    notifyPendingIntent);*/
                            //Set the toast message for the "on" case.
                            toastMessage = "Alarm On!";
                        } else {
                            // Cancel notification if the alarm is turned off.
                            mNotificationManager.cancelAll();

                            if (alarmManager != null) {
                                alarmManager.cancel(notifyPendingIntent);
                            }
                            //Set the toast message for the "off" case.
                            toastMessage = "Alarm Off!";
                        }
                        Toast.makeText(Main3Activity.this, toastMessage,Toast.LENGTH_SHORT)
                                .show();

                    }
                });
        createNotificationChannel();
    }
    public void init(){
        alarmToggle = findViewById(R.id.alarmToggle);
        edt_topic = findViewById(R.id.editText2);
        edt_hour = findViewById(R.id.editText4);
        edt_min =  findViewById(R.id.editText3);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
    public void BackSearch(View view) {
        if(alarmToggle.isChecked()){
            String topic = edt_topic.getText().toString();
            Integer hour = Integer.valueOf(edt_hour.getText().toString());
            Integer min = Integer.valueOf(edt_min.getText().toString());

            Intent notifyIntent = new Intent(this, MyReceiver.class);
            boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                    notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
            alarmToggle.setChecked(alarmUp);
            final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                    (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            long repeatInterval = (hour*60+min)*60*1000;
            long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

//If the Toggle is turned on, set the repeating alarm with a 15 minute interval
            if (alarmManager != null) {
                alarmManager.setInexactRepeating
                        (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                triggerTime, repeatInterval, notifyPendingIntent);
            }
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime, repeatInterval,
                    notifyPendingIntent);
        }
        Intent intent;
        intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
