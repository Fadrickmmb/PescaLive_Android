package com.example.pescalive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MasterAdmin_EditTournamentScreen extends AppCompatActivity {

    Spinner tournamentNameSpinner;
    EditText changeNameInput;
    NumberPicker addRoundPicker;
    Spinner deleteRoundSpinner;

    Button saveButton, cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_master_admin_edit_tournament_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tournamentNameSpinner = findViewById(R.id.masterAdmin_editTournament_tournamentName_spinner);
        changeNameInput = findViewById(R.id.masterAdmin_editTournament_changeName_input);
        addRoundPicker = findViewById(R.id.masterAdmin_editTournament_addRound_picker);
        deleteRoundSpinner = findViewById(R.id.masterAdmin_editTournament_deleteRound_spinner);
        saveButton = findViewById(R.id.masterAdmin_editTournament_save_button);
        cancelButton = findViewById(R.id.masterAdmin_editTournament_cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasterAdmin_MainScreen.class);
                startActivity(intent);
                finish();
            }
        });

        addRoundPicker.setMinValue(1);
        addRoundPicker.setMaxValue(5);



    }
}