package com.example.gyorgyitamas.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdatLekeres extends AppCompatActivity {

    private AdatbazisSegito myDb;
    private TextView idResult;
    private Button idBtn2, idBtn3, idBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_lekeres);

        init();
        onClickListeners();
    }

    private void init(){

        idResult = (TextView) findViewById(R.id.idResult);
        idBtn2 = (Button) findViewById(R.id.idBtn2);
        idBtn3 = (Button) findViewById(R.id.idBtn3);
        idBtn4 = (Button) findViewById(R.id.idBtn4);

        myDb = new AdatbazisSegito(this);
    }

    private void onClickListeners(){

        idBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatLekeres();
            }
        });

        idBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(AdatLekeres.this,AdatRogzites.class);
                startActivity(goback);
                finish();
            }
        });

        idBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gonext = new Intent(AdatLekeres.this,AdatTorles.class);
                startActivity(gonext);
                finish();
            }
        });
        idResult.setMovementMethod(new ScrollingMovementMethod());
    }

    private void adatLekeres(){

        Cursor res = myDb.adatLekerdezes();
        StringBuffer stringBuffer = new StringBuffer();
        if (res!=null && res.getCount()>0)
        {
            while (res.moveToNext())
            {
                // stringbuffer = egy hosszú string amihez hozzá fűzzük (appendeljük) a változókat

                stringBuffer.append("ID: " + res.getString(0) + "\n");
                stringBuffer.append("Keresztnév: " + res.getString(1) + "\n");
                stringBuffer.append("Vezetéknév: " + res.getString(2) + "\n");
                stringBuffer.append("Jegy: " + res.getString(3) + "\n");
            }
            idResult.setText(stringBuffer.toString());
            Toast.makeText(AdatLekeres.this, "Adat sikeresen lekérve!", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(AdatLekeres.this, "Nincs adat, amit le lehetne kérdezni!", Toast.LENGTH_SHORT).show();
        }
    }
}
