package com.example.pescalive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MasterAdmin_CreateTournamentScreen extends AppCompatActivity {

    EditText masterAdmin_createTournament_tournamentName, masterAdmin_createTournament_clubName;
    NumberPicker masterAdmin_createTournament_roundsPicker;
    Button masterAdmin_createTournament_addTournamentToDB_button, masterAdmin_createTournament_cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_master_admin_create_tournament_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        masterAdmin_createTournament_cancel_button = findViewById(R.id.masterAdmin_createTournament_cancel_button);
        masterAdmin_createTournament_addTournamentToDB_button = findViewById(R.id.masterAdmin_createTournament_addTournamentToDB_button);
        masterAdmin_createTournament_tournamentName = findViewById(R.id.masterAdmin_createTournament_tournamentName);
        masterAdmin_createTournament_clubName = findViewById(R.id.masterAdmin_createTournament_clubName);
        masterAdmin_createTournament_roundsPicker = findViewById(R.id.masterAdmin_createTournament_roundsPicker);

        masterAdmin_createTournament_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasterAdmin_MainScreen.class);
                startActivity(intent);
                finish();
            }
        });

        masterAdmin_createTournament_roundsPicker.setMinValue(1);
        masterAdmin_createTournament_roundsPicker.setMaxValue(10);

        String tournamentName = masterAdmin_createTournament_tournamentName.getText().toString().trim();
        String clubName = masterAdmin_createTournament_clubName.getText().toString().trim();
        Integer numberOfRounds = masterAdmin_createTournament_roundsPicker.getValue();

        masterAdmin_createTournament_addTournamentToDB_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tournamentName.isEmpty() && !clubName.isEmpty()){

                }else{
                    Toast.makeText(MasterAdmin_CreateTournamentScreen.this, "Please fill all Inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}