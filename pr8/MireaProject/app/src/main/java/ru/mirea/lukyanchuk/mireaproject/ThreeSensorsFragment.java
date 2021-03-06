package ru.mirea.lukyanchuk.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThreeSensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThreeSensorsFragment extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThreeSensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment showSensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    @NonNull
    public static ThreeSensorsFragment newInstance(String param1, String param2) {
        ThreeSensorsFragment fragment = new ThreeSensorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private SensorManager sensorManager;
    private TextView lightSensorValue;
    private TextView magneticText;
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private Sensor lightSensor;
    private Sensor magneticSensor;
    private Sensor accelerometerSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three_sensors, container, false);

        lightSensorValue = view.findViewById(R.id.light_sensor);
        // magnetic
        magneticText =  view.findViewById(R.id.magnetic_sensor);
        //????????????????????????
        azimuthTextView = view.findViewById(R.id.acceler_x);
        pitchTextView = view.findViewById(R.id.acceler_y);
        rollTextView = view.findViewById(R.id.acceler_z);

        if (getActivity() != null){
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float valueMagnetic = event.values[0];
            magneticText.setText("Magnetic field: " + valueMagnetic);
        }
        else  if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        }
        else if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            String newValue = "Light: "+event.values[0] + " ????";
            lightSensorValue.setText(newValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}