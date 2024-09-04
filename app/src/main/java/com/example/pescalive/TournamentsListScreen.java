package com.example.pescalive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TournamentsListScreen extends AppCompatActivity {

    Button loginButton;
    private RecyclerView tournamentsRecyclerView;
    private TournamentAdapter tournamentAdapter;
    private List<Tournament> tournamentList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tournaments_list_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.tournamentsList_login_button);
        tournamentsRecyclerView = findViewById(R.id.tournaments_recycler_view);
        tournamentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tournamentList = new ArrayList<>();
        tournamentAdapter = new TournamentAdapter(tournamentList, this);
        tournamentsRecyclerView.setAdapter(tournamentAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("tournaments");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

        fetchTournaments();
    }

    private void fetchTournaments() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tournamentList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tournament tournament = snapshot.getValue(Tournament.class);
                    tournament.setTournamentId(snapshot.getKey());
                    tournamentList.add(tournament);
                }
                tournamentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }



}