package com.appfitnessapp.app.fitnessapp.Usuario;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.appfitnessapp.app.fitnessapp.Arrays.Usuarios;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class DatosUsuario extends AppCompatActivity {

    Button btnGuardar;
    ImageButton imgHombre,imgMujer;
    Spinner spinnerAltura,spinnerPeso,spinnerBuscando;

    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;
    private ProgressDialog progressDialog;
    String id;
    int selectionEstatura,selectionPeso,selectionObjetivo;
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

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);


        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        dbProvider = new DBProvider();


        bajarUsuarios();



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

                String hombre=Contants.HOMBRE;
                String mujer=Contants.MUJER;


                if (imgHombre.isClickable()){

                }

                if (!String.valueOf(selectionObjetivo).equals(objetivo)){
                    dbProvider.updateObjetivo(objetivo, id);
                }
                if (!String.valueOf(selectionEstatura).equals(estatura)){
                    dbProvider.updateEstatura(estatura, id);
                }
                if (!String.valueOf(selectionPeso).equals(peso)){
                    dbProvider.updatePeso(peso,id);
                }

            }
        });


    }

    public void bajarUsuarios(){
        dbProvider = new DBProvider();

        progressDialog.setMessage("Cargando Información...");
        progressDialog.show();

        dbProvider.usersRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "Usuarios 4: ");
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //Log.e(TAG,"Usuarios: "+ snapshot);
                        Log.e(TAG, "Usuarios: " + snapshot);
                        Usuarios usuarios = snapshot.getValue(Usuarios.class);

                        if (usuarios.getId_usuario().equals(id)) {


                            //para bajar la info y ponerle en el spinner
                            selectionEstatura= altura.getPosition(usuarios.getEstatura());
                            spinnerAltura.setSelection(selectionEstatura);

                            selectionPeso= peso.getPosition(usuarios.getPeso_actual());
                            spinnerPeso.setSelection(selectionPeso);

                            selectionObjetivo= buscando.getPosition(usuarios.getObjetivo());
                            spinnerBuscando.setSelection(selectionObjetivo);


                        }

                    }
                } else {
                    Log.e(TAG, "Usuarios 3: ");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
