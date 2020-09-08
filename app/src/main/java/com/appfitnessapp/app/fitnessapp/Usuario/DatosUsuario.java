package com.appfitnessapp.app.fitnessapp.Usuario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;


public class DatosUsuario extends AppCompatActivity {

    Button btnGuardar;
    ImageButton imgHombre,imgMujer;
    Spinner spinnerAltura,spinnerPeso,spinnerBuscando;

    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;
    private ProgressDialog progressDialog;
    String id,admin;
    ArrayAdapter<String> altura;
    ArrayAdapter<String> peso;
    ArrayAdapter<String> buscando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_10_datos);

        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("Formulario");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id =extras.getString("id");
            admin=extras.getString("admin");


        }


        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);

        dbProvider = new DBProvider();


        spinnerAltura=findViewById(R.id.spinnerAltura);
        spinnerPeso=findViewById(R.id.spinnerPeso);
        spinnerBuscando=findViewById(R.id.spinnerBuscando);


        imgHombre=findViewById(R.id.imgHombre);
        imgMujer=findViewById(R.id.imgMujer);


        btnGuardar=findViewById(R.id.btnGuardar);



        imgHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imgHombre.isClickable()){

                    imgHombre.setImageDrawable(getResources().getDrawable(R.drawable.hombre_seleccionado));
                    imgMujer.setImageDrawable(getResources().getDrawable(R.drawable.ic_mujer));

                }

                else if (!imgHombre.isClickable()){

                    imgHombre.setImageDrawable(getResources().getDrawable(R.drawable.ic_hombre_noseleccionado));
                    imgMujer.setImageDrawable(getResources().getDrawable(R.drawable.ic_mujer));


                }

            }
        });


        imgMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgMujer.isClickable()){

                    imgHombre.setImageDrawable(getResources().getDrawable(R.drawable.ic_hombre_noseleccionado));
                    imgMujer.setImageDrawable(getResources().getDrawable(R.drawable.mujer_seleccionado));

                }

                else if (!imgMujer.isClickable()){

                    imgHombre.setImageDrawable(getResources().getDrawable(R.drawable.ic_hombre_noseleccionado));
                    imgMujer.setImageDrawable(getResources().getDrawable(R.drawable.ic_mujer));


                }


            }
        });

      altura = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Contants.estatura);
      peso = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Contants.peso);
      buscando = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Contants.objetivos);


        spinnerAltura.setAdapter(altura);
        spinnerAltura.setPrompt("¿Cual es tu estatura?");
        spinnerPeso.setAdapter(peso);
        spinnerPeso.setPrompt("¿Cual es tu peso?");
        spinnerBuscando.setAdapter(buscando);
        spinnerBuscando.setPrompt("¿Que estas objetivos?");



        spinnerAltura.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinnerAltura.getSelectedView()).setTextColor(Color.GRAY);
            }
        });


        spinnerPeso.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinnerPeso.getSelectedView()).setTextColor(Color.GRAY);
            }
        });

        spinnerBuscando.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinnerBuscando.getSelectedView()).setTextColor(Color.GRAY);
            }
        });



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //seleccionar el texto que se pone en el spinner
                String objetivo = spinnerBuscando.getSelectedItem().toString();
                String estatura = spinnerAltura.getSelectedItem().toString();
                String peso = spinnerPeso.getSelectedItem().toString();

                if (imgHombre.isClickable()){

                    dbProvider.updateObjetivo(objetivo, id);
                    dbProvider.updateEstatura(estatura, id);
                    dbProvider.updatePeso(peso,id);



                    new CountDownTimer(1000,1){
                        @Override
                        public void onTick(long l) {
                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(DatosUsuario.this, "Se guardaron tus datos correctamente.", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(DatosUsuario.this, Formulario.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle bundle = new Bundle();
                            bundle.putString("id",id);
                            bundle.putString("admin",admin);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();

                        }
                    }.start();

                }

                else if (imgMujer.isClickable()){


                    dbProvider.updateObjetivo(objetivo, id);
                    dbProvider.updateEstatura(estatura, id);
                    dbProvider.updatePeso(peso,id);

                    new CountDownTimer(1000,1){
                        @Override
                        public void onTick(long l) {
                        }

                        @Override
                        public void onFinish() {
                            Intent intent=new Intent(DatosUsuario.this, Formulario.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle bundle = new Bundle();
                            bundle.putString("id",id);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();

                        }
                    }.start();

                }

            }
        });


    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Por favor llena el formulario para continuar.", Toast.LENGTH_SHORT).show();

    }

}
