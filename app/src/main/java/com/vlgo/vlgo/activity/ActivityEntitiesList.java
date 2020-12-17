package com.vlgo.vlgo.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vlgo.vlgo.R;
import com.vlgo.vlgo.adapter.AdapterEntities;
import com.vlgo.vlgo.component.ComponentDostoprims;
import com.vlgo.vlgo.component.Entity;

import java.util.ArrayList;
import java.util.List;

public class ActivityEntitiesList extends AppCompatActivity implements AdapterEntities.SelectItem {

    private CardView cvLoading;
    private RecyclerView rvEntities;
    private Button btnBack;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entities_list);
        type = getIntent().getIntExtra("type", 0);
        cvLoading = findViewById(R.id.cvLoading);
        rvEntities = findViewById(R.id.rvEntitiesList);
        cvLoading.setVisibility(View.VISIBLE);

        btnBack = findViewById(R.id.btnListBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnBack.setVisibility(View.GONE);
        loadEntities();
    }

    private void loadEntities(){
        LoadEntitiesAsync loadEntitiesAsync = new LoadEntitiesAsync();
        loadEntitiesAsync.execute();
    }

    public void initializeRV(){

        List<Entity> entities = new ArrayList<>();
        switch (type){
            case 0:
                entities = ComponentDostoprims.getInstance().getDostoprims();
                break;
            case 1:
                entities = ComponentDostoprims.getInstance().getPlyazhi();
                break;
            case 2:
                entities = ComponentDostoprims.getInstance().getExcursions();
                break;
            case 3:
                entities = ComponentDostoprims.getInstance().getExtremal();
                break;
        }
        AdapterEntities adapterEntities = new AdapterEntities(entities, getApplicationContext(), this);
        rvEntities.setAdapter(adapterEntities);
        rvEntities.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                Intent i = new Intent(ActivityEntitiesList.this, ActivityMenu.class);
                startActivity(i);
                break;
        }
        return  true;
    }

    @Override
    public void more(int index) {

        Intent i = new Intent(ActivityEntitiesList.this, ActivityEntityInfo.class);
        i.putExtra("type", type);
        i.putExtra("index", index);
        startActivityForResult(i, 123);
    }

    @Override
    public void makeWay(int index) {
        Intent i = new Intent(ActivityEntitiesList.this, MapsActivity.class);
        i.putExtra("type", type);
        i.putExtra("index", index);
        startActivityForResult(i, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 123:

                break;
        }
    }

    public class LoadEntitiesAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            //Прогружаем все данные
            ComponentDostoprims.getInstance();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cvLoading.setVisibility(View.GONE);
            initializeRV();
        }
    }
}