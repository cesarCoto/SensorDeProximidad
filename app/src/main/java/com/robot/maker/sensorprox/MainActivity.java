package com.robot.maker.sensorprox;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    SensorManager sensorManager;
    Sensor poximidad;
    TextView textView;
    Button button;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        textView = findViewById(R.id.textView);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        poximidad=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a();
            }
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }

    public void a(){
        if(aSwitch.isChecked()) {
            sensorManager.registerListener(this, poximidad, SensorManager.SENSOR_DELAY_NORMAL);
            aSwitch.setText("Apagar");
        }else{
            sensorManager.unregisterListener(this,poximidad);
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0]==0){
            textView.setText("Hola");
        }else{
            textView.setText("Adios");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
