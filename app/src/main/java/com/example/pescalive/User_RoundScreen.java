package com.example.pescalive;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class User_RoundScreen extends AppCompatActivity {

    TextView roundName;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_round_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        roundName = findViewById(R.id.user_roundScreen_roundName);

        String roundId = getIntent().getStringExtra("roundId");
        String tournamentId = getIntent().getStringExtra("tournamentId");

        Log.d("Debug", "User Round Screen Debug01 " + roundId);
        Log.d("Debug", "User Round Screen Debug02 " + tournamentId);

        databaseReference = FirebaseDatabase.getInstance().getReference("tournaments");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.d("Debug", "User_RoundScreen - Snapshot Exists");
                    boolean tournamentFound = false;

                    for (DataSnapshot tournamentSnapshot : snapshot.getChildren()) {
                        Tournament tournament = tournamentSnapshot.getValue(Tournament.class);


                        if (tournament != null && tournament.getTournamentId() != null && tournament.getTournamentId().equals(tournamentId)) {
                            Log.d("Debug", "Tournament with ID: " + tournamentId + " found");

                            tournamentFound = true;
                            List<Round> rounds = tournament.getRounds();


                            if (rounds != null) {
                                for (Round round : rounds) {

                                    if (round != null && round.getRoundId() != null && round.getRoundId().equals(roundId)) {
                                        Log.d("Debug", "Round with ID: " + roundId + " found");
                                        roundName.setText(round.getName());
                                        return;
                                    }
                                }
                            }


                            Toast.makeText(User_RoundScreen.this, "Round not found", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    if (!tournamentFound) {
                        Toast.makeText(User_RoundScreen.this, "Tournament not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(User_RoundScreen.this, "No tournaments found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Debug", "Database Error: " + error.getMessage());
            }
        });
    }
}
