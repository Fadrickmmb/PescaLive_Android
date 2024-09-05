// TournamentAdapter.java
package com.example.pescalive;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TournamentAdapter extends RecyclerView.Adapter<TournamentAdapter.TournamentViewHolder> {

    private List<Tournament> tournamentList;
    private Context context;

    public TournamentAdapter(List<Tournament> tournamentList, Context context) {
        this.tournamentList = tournamentList;
        this.context = context;
    }

    @NonNull
    @Override
    public TournamentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tournament_item, parent, false);
        return new TournamentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TournamentViewHolder holder, int position) {
        Tournament tournament = tournamentList.get(position);
        holder.tournamentName.setText(tournament.getName());

        // Pass the tournament ID to the User_TournamentScreen
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, User_TournamentScreen.class);
            intent.putExtra("tournamentId", tournament.getTournamentId()); // Pass the tournament ID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tournamentList.size();
    }

    public static class TournamentViewHolder extends RecyclerView.ViewHolder {
        TextView tournamentName;

        public TournamentViewHolder(@NonNull View itemView) {
            super(itemView);
            tournamentName = itemView.findViewById(R.id.tournament_name);
        }
    }
}
