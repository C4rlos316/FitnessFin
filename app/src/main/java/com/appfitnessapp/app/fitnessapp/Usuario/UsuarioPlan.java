package com.appfitnessapp.app.fitnessapp.Usuario;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appfitnessapp.app.fitnessapp.Adapters.AdapterRecetas;
import com.appfitnessapp.app.fitnessapp.Arrays.PlanAlimenticio;
import com.appfitnessapp.app.fitnessapp.Arrays.PlanEntrenamiento;
import com.appfitnessapp.app.fitnessapp.BaseDatos.BajarInfo;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.androidquery.util.AQUtility.getContext;


public class UsuarioPlan  extends AppCompatActivity {

    ImageButton imgHome,imgPerfil,imgChat;
    RecyclerView recyclerView,recyclerView2,recyclerView3;
    AdapterRecetas adapter,adapter2,adapter3;
    ArrayList<PlanAlimenticio> recetas,recetas2,recetas3;
    TextView btnWorkouts,txtAlmuerzo,txtCena,txtDesayuno,txtNada;


    String id;





    static DBProvider dbProvider;
    BajarInfo bajarInfo;
    private static final String TAG = "BAJARINFO:";

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_15_plan);

        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("Plan alimenticio");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);


        dbProvider = new DBProvider();
        bajarInfo = new BajarInfo();

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        imgHome=findViewById(R.id.imgHome);
        imgPerfil=findViewById(R.id.imgPerfil);
        imgChat=findViewById(R.id.imgChat);
        btnWorkouts=findViewById(R.id.btnWorkouts);

        recyclerView=findViewById(R.id.recycler1);
        recyclerView2=findViewById(R.id.recycler2);
        recyclerView3=findViewById(R.id.recycler3);

        txtAlmuerzo=findViewById(R.id.txtAlmuerzo);
        txtCena=findViewById(R.id.txtCena);
        txtDesayuno=findViewById(R.id.txtDesayuno);

        txtNada=findViewById(R.id.txtNada);


        bajarRecetas();




        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));


        int spanCount = 1;
        int spacing_left = 10;
        int spacing_top=0;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));
        recyclerView2.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));
        recyclerView3.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

        recetas = new ArrayList<>();
        recetas2 = new ArrayList<>();
        recetas3 = new ArrayList<>();
        adapter=new AdapterRecetas(recetas);
        adapter2=new AdapterRecetas(recetas2);
        adapter3=new AdapterRecetas(recetas3);


        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlan.this, DetalleRecetas.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",recetas.get(recyclerView.getChildAdapterPosition(v)).getId_plan_alimenticio());
                bundle.putString("nombre",recetas.get(recyclerView.getChildAdapterPosition(v)).getNombre_alimento());
                bundle.putString("imagen",recetas.get(recyclerView.getChildAdapterPosition(v)).getImagen_alimento());
                bundle.putString("tipo",recetas.get(recyclerView.getChildAdapterPosition(v)).getTipo_alimento());
                bundle.putString("calorias",recetas.get(recyclerView.getChildAdapterPosition(v)).getKilocalorias());
                bundle.putString("minutos",recetas.get(recyclerView.getChildAdapterPosition(v)).getMin_alimento());
                bundle.putString("porciones",recetas.get(recyclerView.getChildAdapterPosition(v)).getPorciones());
                bundle.putString("preciomax",recetas.get(recyclerView.getChildAdapterPosition(v)).getPrecio_mas_alto());
                bundle.putString("preciomin",recetas.get(recyclerView.getChildAdapterPosition(v)).getPrecio_mas_bajo());
                bundle.putString("id_usuario",id);

                intent.putExtras(bundle);
                startActivity(intent);


            }
        });


        adapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlan.this, DetalleRecetas.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getId_plan_alimenticio());
                bundle.putString("nombre",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getNombre_alimento());
                bundle.putString("imagen",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getImagen_alimento());
                bundle.putString("tipo",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getTipo_alimento());
                bundle.putString("calorias",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getKilocalorias());
                bundle.putString("minutos",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getMin_alimento());
                bundle.putString("porciones",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getPorciones());
                bundle.putString("preciomax",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getPrecio_mas_alto());
                bundle.putString("preciomin",recetas2.get(recyclerView2.getChildAdapterPosition(v)).getPrecio_mas_bajo());
                bundle.putString("id_usuario",id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UsuarioPlan.this, DetalleRecetas.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getId_plan_alimenticio());
                bundle.putString("nombre",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getNombre_alimento());
                bundle.putString("imagen",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getImagen_alimento());
                bundle.putString("tipo",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getTipo_alimento());
                bundle.putString("calorias",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getKilocalorias());
                bundle.putString("minutos",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getMin_alimento());
                bundle.putString("porciones",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getPorciones());
                bundle.putString("preciomax",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getPrecio_mas_alto());
                bundle.putString("preciomin",recetas3.get(recyclerView3.getChildAdapterPosition(v)).getPrecio_mas_bajo());
                bundle.putString("id_usuario",id);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {

                Intent intent = new Intent(UsuarioPlan.this, UsuarioPerfil.class);
                startActivity(intent);
                overridePendingTransition(R.anim.move, R.anim.move_leeft);
                finish();

            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioPlan.this, UsuarioHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.move_in, R.anim.move_leeft_in);
                finish();
            }
        });

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioPlan.this, ChatActivityUsuario.class);
                startActivity(intent);
                overridePendingTransition(R.anim.move, R.anim.move_leeft);
                finish();

            }
        });


        btnWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                progressDialog.setMessage("Cargando Información...");
                progressDialog.show();
                progressDialog.setCancelable(false);

                new CountDownTimer(5000,1){

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {

                        progressDialog.dismiss();
                        bajarPlanEjercicios(id);



                    }
                }.start();




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
        dbProvider.tablaPlanAlimenticio().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recetas.clear();
                recetas2.clear();
                recetas3.clear();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Feed: " + dataSnapshot);
                        PlanAlimenticio plan = snapshot.getValue(PlanAlimenticio.class);

                        if (plan.getId_plan_alimenticio() != null){
                            if (plan.getId_usuario().equals(id)) {
                                if (plan.getTipo_alimento().equals(Contants.DESAYUNO)) {

                                    recetas.add(plan);
                                    adapter.notifyDataSetChanged();
                                }
                                if (plan.getTipo_alimento().equals(Contants.ALMUERZO)) {
                                    recetas2.add(plan);
                                    adapter2.notifyDataSetChanged();
                                }
                                if (plan.getTipo_alimento().equals(Contants.CENA)) {
                                    recetas3.add(plan);
                                    adapter3.notifyDataSetChanged();
                                }
                            }
                            else{

                            }
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


    public void bajarPlanEjercicios( final String id){


        progressDialog.setMessage("Cargando Información...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        dbProvider = new DBProvider();
        dbProvider.tablaPlanEntrenamiento().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Feed: " + dataSnapshot);
                        PlanEntrenamiento planEntrenamiento = snapshot.getValue(PlanEntrenamiento.class);

                        Date date = new Date();
                        //diaBase
                        String dia = planEntrenamiento.getDia_ejercicio();
                        //diaSemana
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        int fechaAc = calendar.get(Calendar.DAY_OF_WEEK);

                        if (planEntrenamiento.getId_plan_ejercicio()!=null) {


                            if (planEntrenamiento.getId_usuario().equals(id)){

                                pantalla();



                            }

                            else {
                                progressDialog.dismiss();
                                //Toast.makeText(UsuarioPlan.this, "No hay ejercicios por el momento.", Toast.LENGTH_SHORT).show();
                            }


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


    public void pantalla(){

        Intent intent = new Intent(UsuarioPlan.this, UsuarioPlanWorkouts.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
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
