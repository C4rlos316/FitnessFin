package com.appfitnessapp.app.fitnessapp.Admin;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;


public class EscogerPlan extends AppCompatActivity {

    TextView btnGuardar;
    ImageView btnAgregarEjercicio;
    EditText edtDescripcion,edtTiempo,edtNumEjercicios;

    ArrayAdapter<String> Sdia;
    ArrayAdapter<String> SNivel;

    Spinner spinnerDia,spinnerNivel;

    RecyclerView recyclerView;

    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;

    String id,keyPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_07_escogerplan);

        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("Plan de ejercicios");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }

        dbProvider = new DBProvider();

        recyclerView = findViewById(R.id.recyclerview);

        spinnerDia = findViewById(R.id.spinnerDia);

        spinnerNivel = findViewById(R.id.spinnerNivel);


        Sdia = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Contants.dias_ejercicios);

        SNivel = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Contants.nivel_ejercicio);



        spinnerDia.setAdapter(Sdia);
        spinnerDia.setPrompt("Día de ejercicio");

        spinnerNivel.setAdapter(SNivel);
        spinnerNivel.setPrompt("Nivel de ejercicio");

        spinnerDia.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinnerDia.getSelectedView()).setTextColor(Color.GRAY);
            }
        });


        spinnerNivel.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinnerNivel.getSelectedView()).setTextColor(Color.GRAY);
            }
        });

        btnAgregarEjercicio=findViewById(R.id.btnAgregarEjercicio);

        edtDescripcion=findViewById(R.id.edtDescripcion);

        edtTiempo=findViewById(R.id.edtTiempo);
        edtNumEjercicios=findViewById(R.id.edtNumEjercicios);


        btnGuardar=findViewById(R.id.txtGuardar);




        btnAgregarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = dbProvider.tablaPlanEntrenamiento().push().getKey();
                String descripcion = edtDescripcion.getText().toString();
                String tiempo = edtTiempo.getText().toString();
                String ejercicios = edtNumEjercicios.getText().toString();
                String diaEscogido = spinnerDia.getSelectedItem().toString();
                String nivelEjercicio = spinnerNivel.getSelectedItem().toString();

                keyPlan=key;

                if (!descripcion.isEmpty()&&!tiempo.isEmpty()&&!ejercicios.isEmpty()){


                    switch (diaEscogido) {
                        case "Domingo":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "1");
                            Intent intent = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("key",keyPlan);
                            bundle.putString("id",id);
                            bundle.putString("descripcion",descripcion);
                            bundle.putString("tiempo",tiempo);
                            bundle.putString("ejercicios",ejercicios);
                            intent.putExtras(bundle);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            break;
                        case "Lunes":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "2");
                            Intent intent1 = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("key",keyPlan);
                            bundle1.putString("id",id);
                            bundle1.putString("descripcion",descripcion);
                            bundle1.putString("tiempo",tiempo);
                            bundle1.putString("ejercicios",ejercicios);
                            intent1.putExtras(bundle1);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent1);
                            finish();

                            break;
                        case "Martes":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "3");
                            Intent intent2 = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("key",keyPlan);
                            bundle2.putString("id",id);
                            bundle2.putString("descripcion",descripcion);
                            bundle2.putString("tiempo",tiempo);
                            bundle2.putString("ejercicios",ejercicios);
                            intent2.putExtras(bundle2);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);
                            finish();
                            break;
                        case "Miércoles":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "4");
                            Intent intent3 = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("key",keyPlan);
                            bundle3.putString("id",id);
                            bundle3.putString("descripcion",descripcion);
                            bundle3.putString("tiempo",tiempo);
                            bundle3.putString("ejercicios",ejercicios);
                            intent3.putExtras(bundle3);
                            intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent3);
                            finish();

                            break;
                        case "Jueves":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "5");
                            Intent intent4 = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle4 = new Bundle();
                            bundle4.putString("key",keyPlan);
                            bundle4.putString("id",id);
                            bundle4.putString("descripcion",descripcion);
                            bundle4.putString("tiempo",tiempo);
                            bundle4.putString("ejercicios",ejercicios);
                            intent4.putExtras(bundle4);
                            intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent4);
                            finish();

                            break;
                        case "Viernes":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "6");
                            Intent intent5 = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle5 = new Bundle();
                            bundle5.putString("key",keyPlan);
                            bundle5.putString("id",id);
                            bundle5.putString("descripcion",descripcion);
                            bundle5.putString("tiempo",tiempo);
                            bundle5.putString("ejercicios",ejercicios);
                            intent5.putExtras(bundle5);
                            intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent5);
                            finish();

                            break;
                        case "Sábado":
                            dbProvider.subirPlanEjercicio(tiempo, nivelEjercicio, ejercicios, descripcion, key, id, "7");
                            Intent intent6 = new Intent(EscogerPlan.this, EjerciciosLista.class);
                            Bundle bundle6 = new Bundle();
                            bundle6.putString("key",keyPlan);
                            bundle6.putString("id",id);
                            bundle6.putString("descripcion",descripcion);
                            bundle6.putString("tiempo",tiempo);
                            bundle6.putString("ejercicios",ejercicios);
                            intent6.putExtras(bundle6);
                            intent6.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent6);
                            finish();

                            break;
                    }
            }
                else {
                    Toast.makeText(EscogerPlan.this, "Revisa  que todos los campos esten llenos.", Toast.LENGTH_SHORT).show();

                }




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
