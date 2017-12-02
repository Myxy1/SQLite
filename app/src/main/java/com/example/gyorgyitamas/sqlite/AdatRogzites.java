package com.example.gyorgyitamas.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatRogzites extends AppCompatActivity {


    private AdatbazisSegito myDb;
    private EditText idName, idSurname, idMarks;
    private Button idBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_rogzites);

        init();
        onClickListeners();
    }

    private void init(){

        idName = (EditText) findViewById(R.id.idName);
        idSurname = (EditText) findViewById(R.id.idSurname);
        idMarks = (EditText) findViewById(R.id.idMarks);
        idBtn = (Button) findViewById(R.id.idBtn);

        myDb = new AdatbazisSegito(this);
    }

    private void onClickListeners(){

        idBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatRogzites();
                Intent gonext = new Intent(AdatRogzites.this,AdatLekeres.class);
                startActivity(gonext);
                finish();
            }
        });
    }

    private void adatRogzites(){

        if (EmptyWidget())
        {
            String keresztnev = idName.getText().toString();
            String vezeteknev = idSurname.getText().toString();
            String jegy = idMarks.getText().toString();
            Boolean eredmeny = myDb.adatRogzites(keresztnev,vezeteknev,jegy);

            if (eredmeny == true)
            {
                Toast.makeText(AdatRogzites.this, "Adatrögzítés sikeres!", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(AdatRogzites.this, "Adatrögzítés nem sikerült!", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(AdatRogzites.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
        }



    }

    private boolean EmptyWidget()
    {
        if (idName.getText().toString().equals("") ||
            idSurname.getText().toString().equals("") ||
            idMarks.getText().toString().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
