package com.example.iot_mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView brightnessTextView;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brightnessTextView = findViewById(R.id.brightnessTextView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //printing all available sensors on device
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(int i = 0 ; i < deviceSensors.size();i++){
            System.out.println(deviceSensors.get(i));
        }

        if (sensorManager == null) {
            brightnessTextView.setText("No sensor manager found");
        }
        else if (lightSensor == null){
            brightnessTextView.setText("No sensor found");
        }
        else {
            sensorManager.registerListener(this, lightSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        System.out.println("heyy1");
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            float lightSensorValue = event.values[0];
            String brightnessValue = Float.toString(lightSensorValue);

            brightnessTextView.setText(brightnessValue);

            System.out.println("Light sensor value : "+ brightnessValue);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}