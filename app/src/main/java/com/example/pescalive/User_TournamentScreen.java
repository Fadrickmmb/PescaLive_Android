package com.example.pescalive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

public class User_TournamentScreen extends AppCompatActivity {

    TextView tournamentName;
    DatabaseReference databaseReference;
    LinearLayout roundsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_tournament_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tournamentName = findViewById(R.id.user_tournamentScreen_tournamentName);
        roundsLayout = findViewById(R.id.roundsLayout);

        String tournamentId = getIntent().getStringExtra("tournamentId");
        Log.d("Debug", "Tournament ID:" + tournamentId);
        databaseReference = FirebaseDatabase.getInstance().getReference("tournaments").child(tournamentId);

        fetchTournamentDetails();
    }

    private void fetchTournamentDetails() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Tournament tournament = dataSnapshot.getValue(Tournament.class);
                if (tournament != null) {
                    tournamentName.setText(tournament.getName());
                    loadRounds(tournament.getRounds(), tournament.getTournamentId());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(User_TournamentScreen.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRounds(List<Round> rounds, String tournamentId) {
        if (rounds != null && !rounds.isEmpty()) {
            for (Round round : rounds) {
                TextView roundView = new TextView(this);
                roundView.setText(round.getName());
                roundView.setPadding(16, 16, 16, 16);
                roundView.setTextSize(18);

                roundView.setOnClickListener(v -> openRoundScreen(round.getRoundId()));

                roundsLayout.addView(roundView);
            }
        }
    }


    private void openRoundScreen(String roundId) {
        Intent intent = new Intent(User_TournamentScreen.this, User_RoundScreen.class);
        intent.putExtra("roundId", roundId);
        Log.d("Debug01", roundId);
        startActivity(intent);
    }
}
