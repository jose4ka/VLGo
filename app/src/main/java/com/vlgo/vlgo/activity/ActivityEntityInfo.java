package com.vlgo.vlgo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vlgo.vlgo.R;
import com.vlgo.vlgo.adapter.AdapterImages;
import com.vlgo.vlgo.component.ComponentDostoprims;
import com.vlgo.vlgo.component.Entity;

public class ActivityEntityInfo extends AppCompatActivity {

    private int type, index;

    private RecyclerView recyclerView;

    private Entity currentEntity;
    private Button btnMakeWay;
    private TextView tvName, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_info);
        type = getIntent().getIntExtra("type", 0);
        index = getIntent().getIntExtra("index", 0);
        recyclerView = findViewById(R.id.rvImages);

        tvName = findViewById(R.id.tvEntityInfoName);
        tvDescription = findViewById(R.id.tvEntityInfoDescription);

        btnMakeWay = findViewById(R.id.btnInfoMakeWay);
        btnMakeWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEntityInfo.this, MapsActivity.class);
                i.putExtra("type", type);
                i.putExtra("index", index);
                startActivity(i);
            }
        });

        initEntity();
        initImages();
        fillActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_back:
                finish();
                break;
        }
        return  true;
    }

    private void initImages(){
        AdapterImages adapterImages = new AdapterImages(currentEntity.getImagesIds(), getApplicationContext());
        recyclerView.setAdapter(adapterImages);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initEntity(){
        switch (type){
            case 0:
                currentEntity = ComponentDostoprims.getInstance().getDostoprims().get(index);
                break;
            case 1:
                currentEntity = ComponentDostoprims.getInstance().getPlyazhi().get(index);
                break;
            case 2:
                currentEntity = ComponentDostoprims.getInstance().getExcursions().get(index);
                break;
            case 3:
                currentEntity = ComponentDostoprims.getInstance().getExtremal().get(index);
                break;
        }

    }

    private void fillActivity(){
        tvName.setText(currentEntity.getName());
        tvDescription.setText(currentEntity.getDescription());
    }
}