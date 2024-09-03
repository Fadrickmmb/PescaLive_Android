package com.example.pescalive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;

public class MasterAdmin_MainScreen extends AppCompatActivity {

    Button masterAdmin_mainScreen_createTournament_button, masterAdmin_mainScreen_editTournament_button;
    Button masterAdmin_mainScreen_createUser_button, masterAdmin_mainScreen_editUser_button;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_master_admin_main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        masterAdmin_mainScreen_createTournament_button = findViewById(R.id.masterAdmin_mainScreen_createTournament_button);
        masterAdmin_mainScreen_editTournament_button = findViewById(R.id.masterAdmin_mainScreen_editTournament_button);
        masterAdmin_mainScreen_createUser_button = findViewById(R.id.masterAdmin_mainScreen_createUser_button);
        masterAdmin_mainScreen_editUser_button = findViewById(R.id.masterAdmin_mainScreen_editUser_button);
        logoutButton = findViewById(R.id.masterAdmin_mainScreen_logout_button);

        masterAdmin_mainScreen_createTournament_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasterAdmin_CreateTournamentScreen.class);
                startActivity(intent);
                finish();
            }
        });

        masterAdmin_mainScreen_editTournament_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasterAdmin_EditTournamentScreen.class);
                startActivity(intent);
                finish();
            }
        });

        masterAdmin_mainScreen_createUser_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasterAdmin_CreateUserScreen.class);
                startActivity(intent);
                finish();
            }
        });

        masterAdmin_mainScreen_editUser_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasterAdmin_EditUserScreen.class);
                startActivity(intent);
                finish();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), TournamentsListScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}