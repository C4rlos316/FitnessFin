package com.appfitnessapp.app.fitnessapp.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appfitnessapp.app.fitnessapp.Adapters.AdapterDiaTabla;
import com.appfitnessapp.app.fitnessapp.Adapters.AdapterEditarEjercicios;
import com.appfitnessapp.app.fitnessapp.Arrays.Ejercicios;
import com.appfitnessapp.app.fitnessapp.Arrays.PlanEntrenamiento;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AdminEditarEjercicio  extends AppCompatActivity {

    TextView btnRecetas, btnGuardar;
    EditText edtDescripcion, edtTiempo, edtNumEjercicios;


    RecyclerView recyclerView;

    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;

    String id;


    ImageButton btnAgregarEjecicio;

    String id_plan,id_ejercicio,minutos,descripcion,numeros,dias,nivel;


    ArrayList<Ejercicios> ejercicios;
    AdapterEditarEjercicios editarEjercicios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin07_editarplan);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        Toolbar toolbarback = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("Plan de ejercicios");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            id_plan = extras.getString("id_plan");
            minutos = extras.getString("minutos");
            descripcion = extras.getString("descripcion");
            numeros = extras.getString("numeros");
            dias = extras.getString("dias");
            nivel = extras.getString("nivel");

            bajarEjercicios();
        }

        dbProvider = new DBProvider();

        recyclerView = findViewById(R.id.recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ejercicios=new ArrayList<>();
        editarEjercicios=new AdapterEditarEjercicios(ejercicios);
        recyclerView.setAdapter(editarEjercicios);



        btnRecetas = findViewById(R.id.btnRecetas);

        edtDescripcion = findViewById(R.id.edtDescripcion);

        edtTiempo = findViewById(R.id.edtTiempo);
        edtNumEjercicios = findViewById(R.id.edtNumEjercicios);


        btnGuardar = findViewById(R.id.txtGuardar);

        btnGuardar.setText("Actualizar");


        edtDescripcion.setText(descripcion);
        edtNumEjercicios.setText(numeros);
        edtTiempo.setText(minutos);



        btnAgregarEjecicio=findViewById(R.id.btnAgregarEjercicio);


        btnAgregarEjecicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminEditarEjercicio.this, EjerciciosLista.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("key",id_plan);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        editarEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(AdminEditarEjercicio.this);
                dialogo1.setTitle("Eliminar Ejercicio");
                dialogo1.setMessage("¿Quieres eliminar este ejercicio para el usuario?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                        Query ejercicios = ref.child(Contants.TABLA_PLAN_EJERCICIO).child(id_plan).child(Contants.EJERCICIOS).orderByChild(Contants.ID_EJERCICIO).equalTo(id_ejercicio);

                        ejercicios.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot inscritosSnapshot : dataSnapshot.getChildren()) {
                                    inscritosSnapshot.getRef().removeValue();

                                    Toast.makeText(AdminEditarEjercicio.this, "Se borro el ejercicio", Toast.LENGTH_SHORT).show();
                                    finish();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e(TAG, "onCancelled", databaseError.toException());
                            }
                        });

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        dialogo1.dismiss();

                    }
                });
                dialogo1.show();

                /*
                Intent intent = new Intent(AdminEditarEjercicio.this, AdminEditarValoresEjercicios.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("id_plan",id_plan);
                bundle.putString("id_ejercicio",id_ejercicio);
                bundle.putString("nombre",ejercicios.get(recyclerView.getChildAdapterPosition(v)).getNombre_ejercicio());
                bundle.putString("repeteciones",ejercicios.get(recyclerView.getChildAdapterPosition(v)).getRepeticiones());
                bundle.putString("rondas",ejercicios.get(recyclerView.getChildAdapterPosition(v)).getRondas());
                bundle.putString("video",ejercicios.get(recyclerView.getChildAdapterPosition(v)).getVideo_ejercicio());

                intent.putExtras(bundle);
                startActivity(intent);


*/

            }
        });



        btnRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Intent intent = new Intent(AdminEditarEjercicio.this, AgregarRecetas.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

*/

            }
        });




        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcionEjer = edtDescripcion.getText().toString();
                String numeroEjer = edtNumEjercicios.getText().toString();
                String minEjer = edtTiempo.getText().toString();




                if (!descripcionEjer.equals(descripcion)){

                    dbProvider.updateDescripcionEjercicio(id_plan,descripcionEjer);
                    Toast.makeText(AdminEditarEjercicio.this, "Se actualizó la descripcion.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AdminEditarEjercicio.this, AdminDiaTabla.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",id);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }

                if (!numeroEjer.equals(numeros)){

                    dbProvider.updateNumeroEjercicio(id_plan,numeroEjer);
                    Toast.makeText(AdminEditarEjercicio.this, "Se actualizó el numero de ejercicios.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AdminEditarEjercicio.this, AdminDiaTabla.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",id);
                    intent.putExtras(bundle);
                    startActivity(intent);



                }


                if (!minEjer.equals(minutos)){

                    dbProvider.updateMinEjercicio(id_plan,minEjer);
                    Toast.makeText(AdminEditarEjercicio.this, "Se actualizó los minutos.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AdminEditarEjercicio.this, AdminDiaTabla.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",id);
                    intent.putExtras(bundle);
                    startActivity(intent);



                }

                if (descripcionEjer.isEmpty()&&numeroEjer.isEmpty()&&minEjer.isEmpty()){
                    Toast.makeText(AdminEditarEjercicio.this, "Necesitas rellenar los campos ", Toast.LENGTH_SHORT).show();
                }

                if (descripcionEjer.equals(descripcion)&&numeroEjer.equals(numeros)&&minEjer.equals(minutos)){
                    Intent intent=new Intent(AdminEditarEjercicio.this, AdminPlanUsuario.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }



            }
        });

    }



    public void bajarEjercicios(){

        Log.e(TAG, "Ejercicio: " + id_plan);
        dbProvider = new DBProvider();
        dbProvider.tablaPlanEntrenamiento().child(id_plan).child(Contants.EJERCICIOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ejercicios.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Ejercicio2: " + snapshot);

                        final Ejercicios ejercicio = snapshot.getValue(Ejercicios.class);

                        if (ejercicio.getId_ejercicio()!=null){

                            id_ejercicio=ejercicio.getId_ejercicio();

                            ejercicios.add(ejercicio);
                            editarEjercicios.notifyDataSetChanged();



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