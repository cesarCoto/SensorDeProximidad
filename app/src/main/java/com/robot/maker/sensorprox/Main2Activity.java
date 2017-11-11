package com.robot.maker.sensorprox;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity  implements SensorEventListener{

    SensorManager sensorManager;
    Sensor sensorPox;

    ImageView imageView;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);
        aSwitch = findViewById(R.id.switch_activar);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        sensorPox = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensorPox ==null){
            Toast.makeText(this, "No cuentas con sensor de Proximidad", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    encender();
                }
            });
        }
    }
    private void encender(){
        if(aSwitch.isChecked()){
            sensorManager.registerListener(this,sensorPox,SensorManager.SENSOR_DELAY_NORMAL);
            aSwitch.setText("Apagar");
        }else{
            sensorManager.unregisterListener(this,sensorPox);
            aSwitch.setText("Encender");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0] ==0){
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
