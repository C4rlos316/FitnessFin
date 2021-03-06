package com.appfitnessapp.app.fitnessapp.Usuario;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CambiarContrasena extends AppCompatActivity {
    Button btnCambiar;
    EditText edtContrasena;
    TextView txtCam;

    private static FirebaseAuth mAuth;
    private static final String TAG = "INGRESAR:";
    static DBProvider dbProvider;
    private String id;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_22_cambiarcontrasena);


        Toolbar toolbarback=findViewById(R.id.include);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        edtContrasena=findViewById(R.id.ContraEdtUCambiar);

        btnCambiar=findViewById(R.id.btnCambiarContrasU);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);

        dbProvider = new DBProvider();

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = edtContrasena.getText().toString();

                if (correo.isEmpty()){

                    Toast.makeText(CambiarContrasena.this, "Necesitas escribir una contraseña", Toast.LENGTH_SHORT).show();
                }else {

                    progressDialog.setMessage("Actualizando contraseña");
                    progressDialog.show();
                    changePass(id,correo);

                }

            }
        });


    }

    public void changePass(final String id, final String pass) {
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        user.updatePassword(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CambiarContrasena.this, "Se ha cambiado la contraseña correctamente, vuelve a iniciar sesión.", Toast.LENGTH_SHORT).show();
                    dbProvider.updatePass(pass, id);
                } else {
                    Toast.makeText(CambiarContrasena.this, "Lo siento, hubo un error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    }
