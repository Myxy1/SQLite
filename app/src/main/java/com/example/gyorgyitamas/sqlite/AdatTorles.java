package com.example.gyorgyitamas.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatTorles extends AppCompatActivity {

    private AdatbazisSegito myDb;
    private EditText idID;
    private Button idBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_torles);

        init();
        onClickListeners();
    }

    private void init()
    {
        idID = (EditText) findViewById(R.id.idID);
        idBtn5 = (Button) findViewById(R.id.idBtn5);

        myDb = new AdatbazisSegito(this);
    }

    private void onClickListeners()
    {
        idBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatTorles();
                Intent goback = new Intent(AdatTorles.this,AdatLekeres.class);
                startActivity(goback);
                finish();
            }
        });
    }

    private void adatTorles()
    {
        String id = idID.getText().toString();
        int eredmeny = myDb.adatTorles(id);

        if (eredmeny == 0)
        {
            Toast.makeText(AdatTorles.this, eredmeny + "0 elem törölve!", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(AdatTorles.this, eredmeny + " ID törölve!", Toast.LENGTH_SHORT).show();
        }
    }
}
