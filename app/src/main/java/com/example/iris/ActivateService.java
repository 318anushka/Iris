package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivateService extends AppCompatActivity {

    private SensorManager sm;
    private float accelerationValue;
    private float accelerationLast;
    private float shake;

    public void startService(){

        Intent intent = new Intent(this , Foreground.class);
        startService(intent);
    }

    public void stopService(){

        Intent intent = new Intent(this , Foreground.class);
        stopService(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_service);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService();;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService();
            }
        });

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL);

        accelerationLast = SensorManager.GRAVITY_EARTH;
        accelerationValue = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelerationLast = accelerationValue;
            accelerationValue = (float)Math.sqrt((double) x*x + y*y + z*z);
            float diff = accelerationValue - accelerationLast;
             shake = shake*0.9f + diff;

             if(shake>12){
                 Toast.makeText(ActivateService.this , "emergency it is" , Toast.LENGTH_LONG).show();
             }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
