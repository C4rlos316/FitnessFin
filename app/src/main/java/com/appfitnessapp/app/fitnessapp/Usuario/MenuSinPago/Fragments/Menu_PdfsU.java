package com.appfitnessapp.app.fitnessapp.Usuario.MenuSinPago.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appfitnessapp.app.fitnessapp.Adapters.AdapterFeed;
import com.appfitnessapp.app.fitnessapp.Arrays.Feed;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.Login.IniciarSesion;
import com.appfitnessapp.app.fitnessapp.R;
import com.appfitnessapp.app.fitnessapp.Usuario.DetallePdf;
import com.appfitnessapp.app.fitnessapp.Usuario.PantallaPDF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu_PdfsU extends Fragment {

    private static final int PDF_CODE = 1000 ;


    static DBProvider dbProvider;
    private static final String TAG = "BAJARINFO:";

    AdapterFeed adapterFeed;
    ArrayList<Feed> feeds;

    RecyclerView recyclerView;

    Toolbar toolbar;

    private static FirebaseAuth mAuth;
    LinearLayoutManager linearLayout;

    String id;

    public Menu_PdfsU() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.usuario_14_feed, container, false);

        toolbar=view.findViewById(R.id.toolbar);

        toolbar.setVisibility(View.GONE);


        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            Intent intent=new Intent(getActivity(), IniciarSesion.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else{
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        bajarFeed();

        dbProvider = new DBProvider();



        recyclerView=view.findViewById(R.id.recyclerview);

        linearLayout =new LinearLayoutManager(getActivity());
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


                 if(feeds.get(recyclerView.getChildAdapterPosition(view)).getTipo_feed().equals(Contants.PDF)){

                    if (feeds.get(recyclerView.getChildAdapterPosition(view)).getIs_gratis()){
                        Intent intent = new Intent(getContext(), PantallaPDF.class);
                        intent.putExtra("ViewType","internet");
                        Bundle bundle = new Bundle();
                        bundle.putString("pdf",feeds.get(recyclerView.getChildAdapterPosition(view)).getUrl_tipo());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getContext(), DetallePdf.class);
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


                }


            }
        });



        return view;
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

                        if (feed.tipo_feed.equals(Contants.PDF)) {
                            feeds.add(feed);
                            adapterFeed.notifyDataSetChanged();

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








    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PDF_CODE && resultCode == RESULT_OK && data != null){

            Uri selecPdf=data.getData();
            Intent intent=new Intent(getContext(),PantallaPDF.class);
            intent.putExtra("ViewType","storage");
            intent.putExtra("FileUri",selecPdf.toString());
            startActivity(intent);
        }

    }



}
