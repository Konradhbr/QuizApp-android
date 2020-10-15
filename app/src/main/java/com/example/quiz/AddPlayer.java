package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlayer extends AppCompatActivity {

    Button addPlayer ,goToMenu;
    EditText playerName;
    TextView  comment2;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Quiz quiz = new Quiz();
    String liczba="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        addPlayer = findViewById(R.id.btn_add);
        playerName = findViewById(R.id.player);
        comment2 = findViewById(R.id.comment2);
        goToMenu = findViewById(R.id.menu);

        Intent intent = getIntent();
        liczba = intent.getStringExtra(quiz.zmienna);

        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intTo = new Intent(com.example.quiz.AddPlayer.this, HomeActivity.class);
                startActivity(intTo);
            }
        });


        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                String name = playerName.getText().toString();
                String result = liczba;
                Player player = new Player(name,result);
                reference.child(name).setValue(player);


                Toast.makeText(AddPlayer.this, "Dodano gracza", Toast.LENGTH_LONG ).show();
            }
        });


    }
}