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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MasterAdmin_CreateTournamentScreen extends AppCompatActivity {

    EditText masterAdmin_createTournament_tournamentName, masterAdmin_createTournament_clubName;
    NumberPicker masterAdmin_createTournament_roundsPicker;
    Button masterAdmin_createTournament_addTournamentToDB_button, masterAdmin_createTournament_cancel_button;

    private DatabaseReference databaseReference;

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

        databaseReference = FirebaseDatabase.getInstance().getReference("tournaments");



        masterAdmin_createTournament_addTournamentToDB_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tournamentName = masterAdmin_createTournament_tournamentName.getText().toString().trim();
                String clubName = masterAdmin_createTournament_clubName.getText().toString().trim();
                Integer numberOfRounds = masterAdmin_createTournament_roundsPicker.getValue();

                if (!tournamentName.isEmpty() && !clubName.isEmpty()){
                    addTournamentToDatabase(tournamentName, clubName, numberOfRounds);
                }else{
                    Toast.makeText(MasterAdmin_CreateTournamentScreen.this, "Please fill all Inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addTournamentToDatabase(String tournamentName, String clubName, int numberOfRounds){

        List<Round> rounds = new ArrayList<>();
        for (int i = 1; i <= numberOfRounds; i++) {
            String roundName = "Etapa " + i;
            String roundId = String.valueOf(i);
            Round round = new Round(roundId, roundName);
            rounds.add(round);
        }

        Tournament tournament = new Tournament(tournamentName, clubName, rounds);

        databaseReference.child(tournamentName).setValue(tournament).addOnSuccessListener(
                aVoid -> {
                    Toast.makeText(this, "Tournament Added Successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to Add Tournament", Toast.LENGTH_SHORT).show();
        });
    }
}