// User_TournamentScreen.java
package com.example.pescalive;

import android.os.Bundle;
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

public class User_TournamentScreen extends AppCompatActivity {

    TextView tournamentName;
    DatabaseReference databaseReference;

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

        String tournamentId = getIntent().getStringExtra("tournamentId");

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
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(User_TournamentScreen.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
