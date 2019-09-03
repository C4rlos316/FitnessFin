package com.appfitnessapp.app.fitnessapp.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appfitnessapp.app.fitnessapp.Arrays.Usuarios;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.appfitnessapp.app.fitnessapp.Usuario.UsuarioHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;


public class Registro extends AppCompatActivity {

    private static final String TAG = "CREAR CUENTA:";

    private ProgressDialog progressDialog;
    private static FirebaseAuth mAuth;
    static DBProvider dbProvider;
    String mensaje;
    String id, email, name;
    Boolean yaCreado;

    String refreshedToken = FirebaseInstanceId.getInstance().getToken();

    TextInputEditText edtCorreo,edtContrasena,edtNombre,edtTelefono;
    LinearLayout btnIniciarSesion,btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_05_registro);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        yaCreado = false;


        edtCorreo=findViewById(R.id.edtCorreo);
        edtContrasena=findViewById(R.id.editContrasena);
        edtNombre=findViewById(R.id.edtNombreUsuario);
        edtTelefono=findViewById(R.id.edtTelefono);

        btnIniciarSesion=findViewById(R.id.linearIniciarSesion);
        btnRegistrar=findViewById(R.id.linearRegistrar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = Objects.requireNonNull(edtCorreo.getText()).toString();
                String contrasena = Objects.requireNonNull(edtContrasena.getText()).toString();
                String nombre = Objects.requireNonNull(edtNombre.getText()).toString();
                String telefono = Objects.requireNonNull(edtTelefono.getText()).toString();

                if (!correo.isEmpty() && !contrasena.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()) {
                    progressDialog.setMessage("Creando cuenta...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    register(correo,"nil",nombre,contrasena,telefono,"nil",refreshedToken,Contants.USUARIO);
                }else{
                    Toast.makeText(Registro.this, "Verifica que tengas todos los datos.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this, IniciarSesion.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void register( final String email, final String id,
                          final String name,final String pass,  final String phone,
                          final String photo, final String token,final String type) {

        Log.e(TAG, "REGISTRO: " + email);
        mAuth = FirebaseAuth.getInstance();
        final String[] mensaje = new String[1];

        final String[] id_user = new String[1];
        dbProvider = new DBProvider();

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(Registro.this, "Hubo un error, Revisa si el correo no esta ya existente.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    dbProvider.createUser(email,user.getUid(),name,pass,phone,photo,token,type,"nil","nil","nil");
                    Intent intent = new Intent(Registro.this, UsuarioHome.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }




}
