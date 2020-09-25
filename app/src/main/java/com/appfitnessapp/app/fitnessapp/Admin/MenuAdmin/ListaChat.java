package com.appfitnessapp.app.fitnessapp.Admin.MenuAdmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appfitnessapp.app.fitnessapp.Adapters.AdapterChat;
import com.appfitnessapp.app.fitnessapp.Admin.ChatAdmin.ChatActivityAdmin;
import com.appfitnessapp.app.fitnessapp.Arrays.Inscritos;
import com.appfitnessapp.app.fitnessapp.Arrays.Usuarios;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaChat extends AppCompatActivity {


    AdapterChat adapter;
    ArrayList<Usuarios> asesorias;
    RecyclerView recyclerView;
    String id;

    private ProgressDialog progressDialog;
    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;


    LinearLayout btnAsesoria,btnFormulario,btnFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_lista_chat);

        Toolbar toolbarback=findViewById(R.id.include);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("Chats");


        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        bajarInscritos();

        dbProvider = new DBProvider();
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);

        recyclerView=findViewById(R.id.recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        asesorias=new ArrayList<>();
        adapter=new AdapterChat(asesorias);
        recyclerView.setAdapter(adapter);


        btnFormulario=findViewById(R.id.btnFormulario);
        btnAsesoria=findViewById(R.id.btnAsesoria);
        btnFeed=findViewById(R.id.btnFeed);



        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatActivityAdmin.startActivity(getApplicationContext(),"nil",asesorias.get(recyclerView.getChildAdapterPosition(v)).getId_usuario(),id,"nil");


            }
        });


        btnFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaChat.this, FormularioLista.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.move_in, R.anim.move_leeft_in);
            }
        });

        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaChat.this, AdminAgregarFeed.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.move_in, R.anim.move_leeft_in);
            }
        });


        btnAsesoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaChat.this, AsesoriasAdmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                overridePendingTransition(
                        getIntent().getIntExtra("anim id in", R.anim.move_in),
                        getIntent().getIntExtra("anim id out", R.anim.move_leeft_in));
            }
        });


    }

    public void bajarInscritos(){

        dbProvider = new DBProvider();

        dbProvider.tablaInscritos().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    asesorias.clear();
                    adapter.notifyDataSetChanged();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Log.e(TAG, "Usuarios: " + snapshot);
                        Inscritos inscritos = snapshot.getValue(Inscritos.class);


                        if (inscritos.getAdmin().equals(id)) {
                            if (inscritos.getId_pendiente().equals(false)) {
                                bajarUsuarios2(inscritos.getId_usuario());
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



    public void bajarUsuarios2(final String id_usuario2){
        Log.e(TAG,"Pendiente 2: ");
        dbProvider = new DBProvider();

        dbProvider.usersRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG,"Pendiente 4: ");
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Log.e(TAG, "Pendiente: " + snapshot);
                        Usuarios usuarios = snapshot.getValue(Usuarios.class);

                        if (usuarios.getId_usuario()!=null) {
                            if (usuarios.getId_usuario().equals(id_usuario2)) {
                                Log.e(TAG, "Pendiente Bien: " + snapshot);
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
        overridePendingTransition(
                getIntent().getIntExtra("anim id in", R.anim.move_in),
                getIntent().getIntExtra("anim id out", R.anim.move_leeft_in));

    }

}
