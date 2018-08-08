package edu.wit.mobileapp.fridge101;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageView iv_bottom;
    ImageView iv_freezer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean alarm =  (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE)== null);

        if(alarm){
            Intent notification = new Intent("ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notification, 0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarmDate = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmDate.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, pendingIntent);
        }

        /**
        iv_bottom = findViewById(R.id.refridgerator_bottom_compartment);
        iv_bottom.setOnClickListener((View.OnClickListener) this);

        iv_freezer = findViewById(R.id.refridgerator_freezer_compartment);
        iv_freezer.setOnClickListener((View.OnClickListener) this);
         */

        ImageView open_fridge = (ImageView) findViewById(R.id.iv_fridge);
        open_fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(MainActivity.this, Refridgerator.class);
                finish();
                startActivity(open);
            }
        });

        /**
        ImageView refridgerator_freezer_compartment = (ImageView) findViewById(R.id.refridgerator_freezer_compartment);
        refridgerator_freezer_compartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refridgerator_freezer_compartment_open = new Intent(MainActivity.this, Refridgerator.class);
                finish();
                startActivity(refridgerator_freezer_compartment_open);
            }
        });

        ImageView refridgerator_bottom_compartment = (ImageView) findViewById(R.id.refridgerator_bottom_compartment);
        refridgerator_bottom_compartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refridgerator_bottom_compartment_open = new Intent(MainActivity.this, Refridgerator.class);
                finish();
                startActivity(refridgerator_bottom_compartment_open);
            }
        });
         */
        Log.v("Testing", "Okay");
    }

    /** public void onClick(View v) {
        if(v == iv_bottom)
        {
            Intent intent = new Intent();
            intent.setClass(this, Refridgerator_Bottom.class);
            finish();
            startActivity(intent);
        }
        else if(v == iv_freezer)
        {
            Intent intent = new Intent();
            intent.setClass(this, Refridgerator_Freezer.class);
            finish();
            startActivity(intent);
        }
    } */
}