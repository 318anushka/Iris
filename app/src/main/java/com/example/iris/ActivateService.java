package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.iris.ShowNumberActivity.numbers;

public class ActivateService extends AppCompatActivity {

    private SensorManager sm;
    private float accelerationValue;
    private float accelerationLast;
    private float shake;

    LocationManager locationManager;
    LocationListener locationListener;

    double lattitude;
    double longitude;

    //starting foreground service and sensor detector
    public void startService() {

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        accelerationLast = SensorManager.GRAVITY_EARTH;
        accelerationValue = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

        Intent intent = new Intent(this, Foreground.class);
        startService(intent);
    }

    //stopping foreground service and detector
    public void stopService() {

        //onStop();

        Intent intent = new Intent(this, Foreground.class);
        stopService(intent);
    }

   /* @Override
    protected void onStop() {
        sm.unregisterListener(sensorListener);
        super.onStop();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_service);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService();
            }
        });

        //declaring location manager and listener
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("location","location has been chnaged");
                //Toast.makeText(ActivateService.this,"location has been changed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(ActivateService.this , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null){
                updateLocationInfo(lastKnownLocation);
            }

        }
    }

    //updating location when changed
    private void updateLocationInfo(Location lastKnownLocation) {

        lattitude = lastKnownLocation.getLatitude();
        longitude = lastKnownLocation.getLongitude();

        Toast.makeText(this , lattitude+" "+longitude,Toast.LENGTH_LONG).show();


    }

    //on shaking device
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

                 Intent intent = new Intent(Intent.ACTION_SENDTO);
                 // This ensures only SMS apps respond
                 String sendTo = numbers.substring(0,numbers.length());
                 intent.setData(Uri.parse("smsto:" + sendTo));
                 intent.putExtra("sms_body", "My Location is -  Lattitude:" + " " + lattitude + "\n" + "Longitude:" + " " + longitude);
                 intent.putExtra("exit_on_sent", true);
                 Toast.makeText(ActivateService.this , sendTo , Toast.LENGTH_LONG).show();
                 if (intent.resolveActivity(getPackageManager()) != null) {

                     startActivityForResult(intent, 1);

                 } else {
                     // Log.i("Error" , "failed");
                     Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                 }
             }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
