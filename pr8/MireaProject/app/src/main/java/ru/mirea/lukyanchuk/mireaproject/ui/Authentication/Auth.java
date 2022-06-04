package ru.mirea.lukyanchuk.mireaproject.ui.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.lukyanchuk.mireaproject.MainActivity;
import ru.mirea.lukyanchuk.mireaproject.R;

public class Auth extends AppCompatActivity implements View.OnClickListener{

    private TextView mStatusTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    private FirebaseAuth mAuth;

    public static String nickname = "soonie-doongie-dori";
    public String emailName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mStatusTextView = findViewById(R.id.status);
        mEmailField = findViewById(R.id.emailField);
        mPasswordField = findViewById(R.id.passwordField);

        findViewById(R.id.signInBut).setOnClickListener(this);
        findViewById(R.id.createAccButton).setOnClickListener(this);
        findViewById(R.id.signOutBut).setOnClickListener(this);
        findViewById(R.id.mainScreenBut).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText("Signed in");
            findViewById(R.id.emailField).setVisibility(View.GONE);
            findViewById(R.id.passwordField).setVisibility(View.GONE);
            findViewById(R.id.signInBut).setVisibility(View.GONE);
            findViewById(R.id.createAccButton).setVisibility(View.GONE);
            findViewById(R.id.signOutBut).setVisibility(View.VISIBLE);
            findViewById(R.id.mainScreenBut).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText("Signed out");
            findViewById(R.id.signInBut).setVisibility(View.VISIBLE);
            findViewById(R.id.createAccButton).setVisibility(View.VISIBLE);
            findViewById(R.id.emailField).setVisibility(View.VISIBLE);
            findViewById(R.id.passwordField).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutBut).setVisibility(View.GONE);
            findViewById(R.id.mainScreenBut).setVisibility(View.GONE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = mEmailField.getText().toString();
        emailName = email;
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            int i = 0;
            nickname = "";
            while (emailName.charAt(i) != '@'){
                nickname += emailName.charAt(i);
                i++;
            }
            System.out.println(nickname);
            mEmailField.setError(null);
        }
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText("Authentication failed");
                        }
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.createAccButton) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.signInBut) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.signOutBut) {
            signOut();
        } else if (i == R.id.mainScreenBut) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}