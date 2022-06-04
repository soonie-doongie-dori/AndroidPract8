package ru.mirea.lukyanchuk.mireaproject.ui.stories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.lukyanchuk.mireaproject.MainActivity;
import ru.mirea.lukyanchuk.mireaproject.R;
import ru.mirea.lukyanchuk.mireaproject.ui.App;

public class StoriesView extends AppCompatActivity {
    private EditText number;
    private EditText personNameAdd;
    private EditText petNameAdd;
    private Button but;
    private AppDatabase db;
    private StoriesDAO storiesDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_view);
        db = App.getInstance().getDatabase();
        storiesDAO = db.storiesDAO();

        number = findViewById(R.id.storyNumberEdit);
        personNameAdd = findViewById(R.id.personNameEdit);
        petNameAdd = findViewById(R.id.petNameEdit);

        but = findViewById(R.id.saveButt);
        but.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {

        Story story =new Story();
        story.number = number.getText().toString();
        story.personName = personNameAdd.getText().toString();
        story.petName = petNameAdd.getText().toString();

        storiesDAO.insert(story);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
