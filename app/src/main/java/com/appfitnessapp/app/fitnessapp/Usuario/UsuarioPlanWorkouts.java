package com.appfitnessapp.app.fitnessapp.Usuario;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class UsuarioPlanWorkouts extends AppCompatActivity {

    ImageButton imgHome,imgPerfil,imgChat;
    TextView btnRecetas,txtTiempo,txtIntensidad,txtEjericios;
    ImageView imgVideo,imgPrincipal;

    static DBProvider dbProvider;
    BajarInfo bajarInfo;
    private static final String TAG = "BAJARINFO:";

    String descripcion,idEjercicio;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_19_plan_entrenamiento);

        Toolbar toolbarback = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        dbProvider = new DBProvider();
        bajarInfo = new BajarInfo();
        bajarPlanEjercicios();

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        imgHome=findViewById(R.id.imgHome);
        imgPerfil=findViewById(R.id.imgPerfil);
        imgChat=findViewById(R.id.imgChat);

        imgVideo=findViewById(R.id.imgVideo);
        imgPrincipal=findViewById(R.id.imgPrincipal);


        btnRecetas=findViewById(R.id.btnRecetas);

        txtTiempo=findViewById(R.id.txtTiempo);
        txtIntensidad=findViewById(R.id.txtIntensidad);
        txtEjericios=findViewById(R.id.txtEjericicios);



        imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlanWorkouts.this, RutinaUsuario.class);
                Bundle bundle = new Bundle();
                bundle.putString("descripcion",descripcion);
                bundle.putString("id",idEjercicio);
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

                        Date date = new Date();
                        //diaBase
                        String dia = planEntrenamiento.getDia_ejercicio();
                        //diaSemana
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        int fechaAc = calendar.get(Calendar.DAY_OF_WEEK);

                        if (planEntrenamiento.getId_plan_ejercicio()!=null) {

                            if (planEntrenamiento.getId_usuario().equals(id)){

                                imgVideo.setVisibility(View.VISIBLE);
                                if (fechaAc == 1&&dia.equals("1")) {
                                    imgVideo.setVisibility(View.VISIBLE);
                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");



                                }


                                else if (fechaAc == 2&&dia.equals("2")) {
                                    imgVideo.setVisibility(View.VISIBLE);

                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");



                                }

                                else if (fechaAc == 3&&dia.equals("3")) {
                                    imgVideo.setVisibility(View.VISIBLE);

                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");


                                }
                                else if (fechaAc == 4&&dia.equals("4")) {
                                    imgVideo.setVisibility(View.VISIBLE);

                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");

                                }

                                else if (fechaAc == 5&&dia.equals("5")) {
                                    imgVideo.setVisibility(View.VISIBLE);


                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");

                                }


                                else if (fechaAc == 6&&dia.equals("6")) {
                                    imgVideo.setVisibility(View.VISIBLE);

                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");

                                }


                                else if (fechaAc == 7&&dia.equals("7")) {
                                    imgVideo.setVisibility(View.VISIBLE);


                                    idEjercicio = planEntrenamiento.getId_plan_ejercicio();
                                    descripcion = planEntrenamiento.getDescripcion_ejercicios();
                                    txtTiempo.setText(planEntrenamiento.getMin_ejercicio() + " min");
                                    txtEjericios.setText(planEntrenamiento.getNum_ejercicios() + " ejercicios");
                                    txtIntensidad.setText(planEntrenamiento.getNivel_ejercicio() + " Intensidad");





                                }

                                else{
                                    imgVideo.setVisibility(View.GONE);
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
        // Handle presses on the action bar items
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
        overridePendingTransition(
                getIntent().getIntExtra("anim id in", R.anim.move_in),
                getIntent().getIntExtra("anim id out", R.anim.move_leeft_in));

    }




}
