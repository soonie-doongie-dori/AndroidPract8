package ru.mirea.lukyanchuk.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
    private Button saveButton;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText ageText;
    private SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        saveButton = view.findViewById(R.id.saveBut);
        saveButton.setOnClickListener(this::updateButtonClick);

        firstNameText = view.findViewById(R.id.FisrtNameEdit);
        lastNameText = view.findViewById(R.id.LastNameEdit);
        ageText = view.findViewById(R.id.petNameEdit);

        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);;

        try {
            firstNameText.setText(preferences.getString("first name", null));
            lastNameText.setText(preferences.getString("last name", null));
            ageText.setText(preferences.getString("age", null));
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return view;
    }


    private void updateButtonClick(View view) {
        Log.d("TAG", "button clicked");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("first name", firstNameText.getText().toString());
        editor.putString("last name", lastNameText.getText().toString());
        editor.putString("age", ageText.getText().toString());
        editor.apply();
        Toast.makeText(getActivity(), "Обновление сохранено", Toast.LENGTH_SHORT).show();
    }
}