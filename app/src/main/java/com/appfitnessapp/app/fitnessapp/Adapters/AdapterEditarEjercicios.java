package com.appfitnessapp.app.fitnessapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appfitnessapp.app.fitnessapp.Arrays.Ejercicios;
import com.appfitnessapp.app.fitnessapp.Arrays.PlanEntrenamiento;
import com.appfitnessapp.app.fitnessapp.R;

import java.util.ArrayList;

public class AdapterEditarEjercicios extends RecyclerView.Adapter<AdapterEditarEjercicios.AsesoriasViewHolder>
        implements View.OnClickListener {

    AdapterEditarEjercicios adapterPlanes;

    private View.OnClickListener listener;
    ArrayList<Ejercicios> asesorias;
    private static final String TAG = "BAJARINFO:";


    public static class AsesoriasViewHolder extends RecyclerView.ViewHolder {


        TextView txtNombreEjercicio;

        public AsesoriasViewHolder(View itemView) {
            super(itemView);


            txtNombreEjercicio = itemView.findViewById(R.id.txtDia);

        }

    }

    public AdapterEditarEjercicios(ArrayList<Ejercicios> asesorias) {

        this.asesorias = asesorias;
    }

    @Override
    public AdapterEditarEjercicios.AsesoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_diseno_tabladia, null, false);
        v.setOnClickListener(this);

        AdapterEditarEjercicios.AsesoriasViewHolder holder = new AdapterEditarEjercicios.AsesoriasViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEditarEjercicios.AsesoriasViewHolder holder, int position) {
        Ejercicios asesoria = asesorias.get(position);

        holder.txtNombreEjercicio.setText(asesoria.getNombre_ejercicio());

    }


    @Override
    public int getItemCount() {
        return asesorias.size();
    }


    public void setOnClickListener(View.OnClickListener listener) {


        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

}

