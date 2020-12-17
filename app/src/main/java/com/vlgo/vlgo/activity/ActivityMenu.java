package com.vlgo.vlgo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vlgo.vlgo.R;

public class ActivityMenu extends AppCompatActivity {

    private LinearLayout lnDostoprim, lnPlyazhi, lnEkskursii, lnExtremal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lnDostoprim = findViewById(R.id.lnDostoprim);
        lnDostoprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListByType(0);
            }
        });

        lnPlyazhi = findViewById(R.id.lnPlyazhi);
        lnPlyazhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListByType(1);
            }
        });

        lnEkskursii = findViewById(R.id.lnEkskursii);
        lnEkskursii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListByType(2);
            }
        });

        lnExtremal = findViewById(R.id.lnExtremal);
        lnExtremal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListByType(3);
            }
        });
    }


    private void openListByType(int type){
        Intent i = new Intent(ActivityMenu.this, ActivityEntitiesList.class);
        i.putExtra("type", type);
        startActivity(i);
    }
}