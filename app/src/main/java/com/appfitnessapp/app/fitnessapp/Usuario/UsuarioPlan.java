package com.appfitnessapp.app.fitnessapp.Usuario;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appfitnessapp.app.fitnessapp.Adapters.AdapterPlanes;
import com.appfitnessapp.app.fitnessapp.Adapters.AdapterRecetas;
import com.appfitnessapp.app.fitnessapp.Arrays.Feed;
import com.appfitnessapp.app.fitnessapp.Arrays.Recetas;
import com.appfitnessapp.app.fitnessapp.BaseDatos.BajarInfo;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.Login.IniciarSesion;
import com.appfitnessapp.app.fitnessapp.Login.Registro;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UsuarioPlan  extends AppCompatActivity {

    ImageButton imgHome,imgPerfil,imgChat;
    RecyclerView recyclerView,recyclerView2;
    AdapterRecetas adapter,adapter2;
    ArrayList<Recetas> recetas,recetas2;
    TextView btnWorkouts,txtFechaSiguiente,txtFechaActual;



    static DBProvider dbProvider;
    BajarInfo bajarInfo;
    private static final String TAG = "BAJARINFO:";

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    Date date = new Date();

    String fecha = dateFormat.format(date);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_15_plan);

        dbProvider = new DBProvider();
        bajarInfo = new BajarInfo();

        imgHome=findViewById(R.id.imgHome);
        imgPerfil=findViewById(R.id.imgPerfil);
        imgChat=findViewById(R.id.imgChat);
        btnWorkouts=findViewById(R.id.btnWorkouts);

        recyclerView=findViewById(R.id.recycler1);
        recyclerView2=findViewById(R.id.recycler2);

        txtFechaSiguiente=findViewById(R.id.txtfechaSiguiente);
        txtFechaActual=findViewById(R.id.txtfechaActual);

        txtFechaActual.setText(fecha);
        txtFechaSiguiente.setText(getNextDate(fecha));

        bajarRecetas();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        int spanCount = 1;
        int spacing_left = 10;
        int spacing_top=0;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));
        recyclerView2.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recetas = new ArrayList<>();
        recetas2 = new ArrayList<>();
        adapter=new AdapterRecetas(recetas);
        adapter2=new AdapterRecetas(recetas2);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlan.this, DetalleRecetas.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombre",recetas.get(recyclerView.getChildAdapterPosition(v)).getNombre_alimento());
                bundle.putString("imagen",recetas.get(recyclerView.getChildAdapterPosition(v)).getImagen_alimento());
                bundle.putString("tipo",recetas.get(recyclerView.getChildAdapterPosition(v)).getTipo_alimento());
                bundle.putString("calorias",recetas.get(recyclerView.getChildAdapterPosition(v)).getKilocalorias());
                bundle.putString("minutos",recetas.get(recyclerView.getChildAdapterPosition(v)).getMin_alimento());
                bundle.putString("porciones",recetas.get(recyclerView.getChildAdapterPosition(v)).getPorciones());
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        adapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlan.this, DetalleRecetas.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombre",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getNombre_alimento());
                bundle.putString("imagen",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getImagen_alimento());
                bundle.putString("tipo",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getTipo_alimento());
                bundle.putString("calorias",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getKilocalorias());
                bundle.putString("minutos",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getMin_alimento());
                bundle.putString("porciones",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getPorciones());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UsuarioPlan.this, UsuarioPerfil.class);
                startActivity(intent);
                finish();

            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioPlan.this, UsuarioHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioPlan.this, UsuarioChat.class);
                startActivity(intent);
                finish();

            }
        });

        btnWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlan.this, UsuarioPlanWorkouts.class);
                startActivity(intent);

            }
        });

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        final private int spanCount, spacing, spacing_top;
        final private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing_left, int spacing_top) {
            this.spanCount = spanCount;
            this.spacing = spacing_left;
            this.includeEdge = true;
            this.spacing_top = spacing_top;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item phases_position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) { // top edge
                    outRect.top = spacing_top;
                }
                outRect.bottom = spacing_top; // item bottom
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing_top; // item top
                }
            }
        }
    }


    public void bajarRecetas(){

        dbProvider = new DBProvider();
        dbProvider.recetas().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recetas.clear();
                recetas2.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Feed: " + dataSnapshot);
                        Recetas receta = snapshot.getValue(Recetas.class);



                        if (receta.getFecha_alimento().equals(fecha)){

                            recetas.add(receta);
                            adapter.notifyDataSetChanged();
                        }

                        else if (receta.getFecha_alimento().equals(getNextDate(fecha))){
                            recetas2.add(receta);
                            adapter2.notifyDataSetChanged();

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

    private String getNextDate(String inputDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        inputDate = dateFormat.format(date);
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, +1);
            inputDate = dateFormat.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

}
