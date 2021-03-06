package com.example.writeamobileapplicationthatcreatesalarmclock;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    TimePicker alarmTime;
    TextClock currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.textClock);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentTime.getText().toString().equals(AlarmTime())) {
                    r.play();
                } else {
                    r.stop();
                }
            }
        }, 0, 1000);
    }
    public String AlarmTime() {
        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();
        String stringAlarmMinutes = "";
        //make sure value of minute in 2 digit's length
        if (alarmMinutes < 10) {
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        } else {
            stringAlarmMinutes = alarmMinutes.toString();
        }
        // convert time into AM/PM instead of 24 Hrs format
        String stringAlarmTime = "";
        if (alarmHours >= 12) {
            if (alarmHours != 12) {
                alarmHours = alarmHours - 12;
            }
            stringAlarmTime =
                    alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" PM");
        } else {
            if (alarmHours == 0) {
                alarmHours = 12;
            }
            stringAlarmTime =
                    alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" AM");
        }
        return stringAlarmTime;
    }
}