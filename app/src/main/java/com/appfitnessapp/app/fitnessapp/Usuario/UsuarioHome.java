package com.appfitnessapp.app.fitnessapp.Usuario;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appfitnessapp.app.fitnessapp.Adapters.AdapterFeed;
import com.appfitnessapp.app.fitnessapp.Arrays.Feed;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.Login.IniciarSesion;
import com.appfitnessapp.app.fitnessapp.R;
import com.appfitnessapp.app.fitnessapp.videoplayer.VideoPlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsuarioHome  extends AppCompatActivity {

    private static final int PDF_CODE = 1000 ;

    static DBProvider dbProvider;
    private static final String TAG = "BAJARINFO:";

    AdapterFeed adapterFeed;
    ArrayList<Feed> feeds;

    RecyclerView recyclerView;


    private static FirebaseAuth mAuth;
    LinearLayoutManager linearLayout;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_14_feed);

        Toolbar toolbarback=findViewById(R.id.toolbar);
        toolbarback.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            Intent intent=new Intent(UsuarioHome.this, IniciarSesion.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else{
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        bajarFeed();

        dbProvider = new DBProvider();




        recyclerView=findViewById(R.id.recyclerview);

        linearLayout =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        //poner orden inverso el recycler
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);

        feeds=new ArrayList<>();
        adapterFeed=new AdapterFeed(feeds);
        recyclerView.setAdapter(adapterFeed);


        adapterFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(feeds.get(recyclerView.getChildAdapterPosition(view)).getTipo_feed().equals(Contants.VIDEO)){

                    Intent intent = new Intent(UsuarioHome.this, VideoPlayer.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("video",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

               else if(feeds.get(recyclerView.getChildAdapterPosition(view)).getTipo_feed().equals(Contants.IMAGEN)){

                    Intent intent = new Intent(UsuarioHome.this, Imagen.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("imagen",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                else if(feeds.get(recyclerView.getChildAdapterPosition(view)).getTipo_feed().equals(Contants.PDF)){

                    if (feeds.get(recyclerView.getChildAdapterPosition(view)).getIs_gratis()){
                        Intent intent = new Intent(UsuarioHome.this, PantallaPDF.class);
                        intent.putExtra("ViewType","internet");
                        Bundle bundle = new Bundle();
                        bundle.putString("pdf",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(UsuarioHome.this, DetallePdf.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("HomeInicio","Pagado");
                        bundle.putString("url",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        bundle.putString("descripcion",feeds.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                        bundle.putString("precio",feeds.get(recyclerView.getChildAdapterPosition(view)).getCosto_pdf());

                        intent.putExtras(bundle);
                        startActivity(intent);

                    }

                }

                else if(feeds.get(recyclerView.getChildAdapterPosition(view)).getTipo_feed().equals(Contants.EBOOKS)){

                    if (feeds.get(recyclerView.getChildAdapterPosition(view)).getIs_gratis()){
                        Intent intent = new Intent(UsuarioHome.this, PantallaPDF.class);
                        intent.putExtra("ViewType","internet");
                        Bundle bundle = new Bundle();
                        bundle.putString("pdf",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(UsuarioHome.this, DetallePdf.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("HomeInicio","Pagado");
                        bundle.putString("url",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        bundle.putString("descripcion",feeds.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                        bundle.putString("precio",feeds.get(recyclerView.getChildAdapterPosition(view)).getCosto_pdf());

                        intent.putExtras(bundle);
                        startActivity(intent);

                    }

                }

                else if(feeds.get(recyclerView.getChildAdapterPosition(view)).getTipo_feed().equals(Contants.RECETARIOS)){

                    if (feeds.get(recyclerView.getChildAdapterPosition(view)).getIs_gratis()){
                        Intent intent = new Intent(UsuarioHome.this, PantallaPDF.class);
                        intent.putExtra("ViewType","internet");
                        Bundle bundle = new Bundle();
                        bundle.putString("pdf",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(UsuarioHome.this, DetallePdf.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("HomeInicio","Pagado");
                        bundle.putString("url",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        bundle.putString("descripcion",feeds.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                        bundle.putString("precio",feeds.get(recyclerView.getChildAdapterPosition(view)).getCosto_pdf());

                        intent.putExtras(bundle);
                        startActivity(intent);

                    }

                }

                else {

                    Toast.makeText(UsuarioHome.this, "hola", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    public void bajarFeed(){

      //  feeds.removeAll(feeds);

        dbProvider = new DBProvider();
        dbProvider.tablaFeed().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                feeds.clear();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Feed: " + dataSnapshot);
                        Feed feed = snapshot.getValue(Feed.class);

                        feeds.add(feed);
                        adapterFeed.notifyDataSetChanged();

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PDF_CODE && resultCode == RESULT_OK && data != null){

            Uri selecPdf=data.getData();
            Intent intent=new Intent(UsuarioHome.this,PantallaPDF.class);
            intent.putExtra("ViewType","storage");
            intent.putExtra("FileUri",selecPdf.toString());
            startActivity(intent);
        }

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
