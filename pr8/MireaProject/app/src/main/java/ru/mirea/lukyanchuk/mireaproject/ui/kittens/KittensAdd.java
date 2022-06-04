package ru.mirea.lukyanchuk.mireaproject.ui.kittens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.lukyanchuk.mireaproject.MainActivity;
import ru.mirea.lukyanchuk.mireaproject.R;
import ru.mirea.lukyanchuk.mireaproject.ui.App;
import ru.mirea.lukyanchuk.mireaproject.ui.stories.AppDatabase;

public class KittensAdd extends AppCompatActivity {
    private EditText kittyName;
    private EditText kittyType;
    private EditText kittyAge;
    private Button button;

    private AppDatabase db;
    private KittensDao kittenDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kittens_view);

        db = App.getInstance().getDatabase();
        kittenDao = db.kittensDao();

        kittyName = findViewById(R.id.editNameKit);
        kittyType = findViewById(R.id.editTypeKit);
        kittyAge = findViewById(R.id.editAgeKit);

        button = findViewById(R.id.btnSaveKit);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {

        Kittens kit = new Kittens();
        kit.name = kittyName.getText().toString();
        kit.type = kittyType.getText().toString();
        kit.age = kittyAge.getText().toString();

        kittenDao.insert(kit);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
