package com.appfitnessapp.app.fitnessapp.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.appfitnessapp.app.fitnessapp.Arrays.PlanEntrenamiento;
import com.appfitnessapp.app.fitnessapp.BaseDatos.BajarInfo;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class AdminWorkouts extends AppCompatActivity {

    TextView btnRecetas,txtTiempo,txtIntensidad,txtEjericios;
    ImageView imgVideo,imgPrincipal;

    static DBProvider dbProvider;
    BajarInfo bajarInfo;
    private static final String TAG = "BAJARINFO:";

    String descripcion,idEjercicio;

    String id;

    TextView txtEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_09_workouts);


        Toolbar toolbarback = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        dbProvider = new DBProvider();
        bajarInfo = new BajarInfo();
        bajarPlanEjercicios();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }

        imgVideo=findViewById(R.id.imgVideo);
        imgPrincipal=findViewById(R.id.imgPrincipal);


        btnRecetas=findViewById(R.id.btnRecetas);

        txtTiempo=findViewById(R.id.txtTiempo);
        txtIntensidad=findViewById(R.id.txtIntensidad);
        txtEjericios=findViewById(R.id.txtEjericicios);

        txtEditar=findViewById(R.id.txtEditar);



        txtEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminWorkouts.this, AdminDiaTabla.class);
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
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Feed: " + dataSnapshot);
                        PlanEntrenamiento planEntrenamiento = snapshot.getValue(PlanEntrenamiento.class);

                        if (planEntrenamiento.getId_usuario().equals(id)){

                            Date date = new Date();
                            //diaBase
                            String dia = planEntrenamiento.getDia_ejercicio();
                            //diaSemana
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            int fechaAc = calendar.get(Calendar.DAY_OF_WEEK);
                            if (fechaAc==1){
                                if (dia.equals("1")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }

                            }
                            if (fechaAc==2){
                                if (dia.equals("2")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }

                            }

                            if (fechaAc==3){
                                if (dia.equals("3")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }

                            }
                            if (fechaAc==4){
                                if (dia.equals("4")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }
                            }

                            if (fechaAc==5){
                                if (dia.equals("5")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }
                            }


                            if (fechaAc==6){
                                if (dia.equals("6")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }
                            }


                            if (fechaAc==7){
                                if (dia.equals("7")){
                                    idEjercicio=planEntrenamiento.getId_plan_ejercicio();
                                    descripcion=planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio()+" min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios()+" ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio()+" Intensidad");
                                }
                            }
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
