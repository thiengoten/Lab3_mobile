package com.example.lab3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnDetails;
    Button btnAdd;
    String imageUri;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDetails = findViewById(R.id.view_details_button);
        btnAdd = findViewById(R.id.save_details_button);



        // when the button is clicked, the Details activity is started
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Details.getIntent(getApplicationContext()));
            }
        });

        // when the button is clicked, the Add activity is started
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();
                // when button is clicked, clear the text fields
                EditText name = findViewById(R.id.name_input);
                EditText dob = findViewById(R.id.dob_input);
                EditText email = findViewById(R.id.email_input);

                name.setText("");
                dob.setText("");
                email.setText("");
            }
        });
    }

    private void saveDetails() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        EditText name = findViewById(R.id.name_input);
        EditText dob = findViewById(R.id.dob_input);
        EditText email = findViewById(R.id.email_input);

        String nameString = name.getText().toString().trim();
        String dobString = dob.getText().toString().trim();
        String emailString = email.getText().toString().trim();

        long userId = databaseHelper.insertUser(nameString, dobString, emailString, imageUri);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.custom_alert_layout, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        RelativeLayout bottomLayout = view.findViewById(R.id.bottom);
        bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
       if(view.getId() == R.id.radio_Button){
           imageUri = "ic_android_black_24dp";
       } else if(view.getId() == R.id.radio_Button2){
           imageUri = "baseline_catching_pokemon_24";
       } else if(view.getId() == R.id.radio_Button3){
           imageUri = "baseline_egg_24";
       }
    }
}