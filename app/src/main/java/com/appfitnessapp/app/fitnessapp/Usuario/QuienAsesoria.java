package com.appfitnessapp.app.fitnessapp.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.appfitnessapp.app.fitnessapp.Adapters.AdapterAdmin;
import com.appfitnessapp.app.fitnessapp.Arrays.Usuarios;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuienAsesoria extends AppCompatActivity {

    ArrayList<Usuarios> asesorias;
    RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;

    AdapterAdmin adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_quienasesoria);

        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        dbProvider = new DBProvider();
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);


        bajarUsuarios();


        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        asesorias=new ArrayList<>();
        adapter = new AdapterAdmin(asesorias);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuienAsesoria.this, TipoPlanes.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",asesorias.get(recyclerView.getChildAdapterPosition(v)).getId_usuario());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    public void bajarUsuarios(){
        Log.e(TAG,"Usuarios 2: ");
        dbProvider = new DBProvider();

        dbProvider.usersRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG,"Usuarios 4: ");
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Log.e(TAG, "Usuarios: " + snapshot);
                        Usuarios usuarios = snapshot.getValue(Usuarios.class);

                        if (usuarios.getId_usuario()!=null) {
                             if (usuarios.getTipo_usuario().equals(Contants.ADMIN)) {
                                 asesorias.add(usuarios);
                                 adapter.notifyDataSetChanged();
                                 progressDialog.dismiss();




                            }

                        }

                    }
                }else{
                    Log.e(TAG,"Usuarios 3: ");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"ERROR: ");
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
