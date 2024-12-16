package com.example.projectwmp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView usernameText, emailText;
    private Button logoutButton;
    private SharedPreferences sharedPreferences;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        dbHelper = new DBHelper(this);

        // Initialize views
        usernameText = findViewById(R.id.usernameText);
        emailText = findViewById(R.id.emailText);
        logoutButton = findViewById(R.id.logoutButton);

        // Get user data
        String username = sharedPreferences.getString("username", "");
        User user = dbHelper.getUser(username);

        // Display user data
        usernameText.setText("Username: " + user.getUsername());
        emailText.setText("Email: " + user.getEmail());

        logoutButton.setOnClickListener(v -> logout());
    }

    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        finish();
    }
}