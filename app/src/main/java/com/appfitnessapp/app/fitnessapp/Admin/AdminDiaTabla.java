package com.appfitnessapp.app.fitnessapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.appfitnessapp.app.fitnessapp.Adapters.AdapterDiaTabla;
import com.appfitnessapp.app.fitnessapp.Arrays.PlanEntrenamiento;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class AdminDiaTabla extends AppCompatActivity {


    RecyclerView recyclerView;
    String id;
    ArrayList<PlanEntrenamiento> entrenamientos;


    TextView btnAgregarDia;

    static DBProvider dbProvider;
    private static final String TAG = "BAJARINFO:";

    AdapterDiaTabla adapterDiaTabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dia_tabla);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }


        bajarPlanEjercicios();

        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("Workouts");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recyclerview);

        btnAgregarDia=findViewById(R.id.txtNuevo);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        entrenamientos=new ArrayList<>();
        adapterDiaTabla=new AdapterDiaTabla(entrenamientos);
        recyclerView.setAdapter(adapterDiaTabla);


        adapterDiaTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminDiaTabla.this, AdminEditarEjercicio.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("id_plan",entrenamientos.get(recyclerView.getChildAdapterPosition(v)).getId_plan_ejercicio());
                bundle.putString("descripcion",entrenamientos.get(recyclerView.getChildAdapterPosition(v)).getDescripcion_ejercicios());
                bundle.putString("minutos",entrenamientos.get(recyclerView.getChildAdapterPosition(v)).getMin_ejercicio());
                bundle.putString("numeros",entrenamientos.get(recyclerView.getChildAdapterPosition(v)).getNum_ejercicios());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        btnAgregarDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminDiaTabla.this, EscogerPlan.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }

    public void bajarPlanEjercicios(){

        dbProvider = new DBProvider();
        dbProvider.tablaPlanEntrenamiento().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                entrenamientos.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Feed: " + dataSnapshot);
                        PlanEntrenamiento planEntrenamiento = snapshot.getValue(PlanEntrenamiento.class);

                        if (planEntrenamiento.getId_usuario().equals(id)){


                            entrenamientos.add(planEntrenamiento);
                            adapterDiaTabla.notifyDataSetChanged();

                        }
                    }
                }
                else {
                    Log.e(TAG, "Feed: ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "ERROR: ");
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}