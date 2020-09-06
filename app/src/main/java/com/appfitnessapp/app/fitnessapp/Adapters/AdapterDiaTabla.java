package com.appfitnessapp.app.fitnessapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appfitnessapp.app.fitnessapp.Arrays.PlanEntrenamiento;
import com.appfitnessapp.app.fitnessapp.Arrays.Usuarios;
import com.appfitnessapp.app.fitnessapp.R;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AdapterDiaTabla extends RecyclerView.Adapter<AdapterDiaTabla.AsesoriasViewHolder>
        implements View.OnClickListener {

    AdapterDiaTabla adapterPlanes;

    private View.OnClickListener listener;
    ArrayList<PlanEntrenamiento> asesorias;
    private static final String TAG = "BAJARINFO:";


    public static class AsesoriasViewHolder extends RecyclerView.ViewHolder {


        TextView txtDia;

        public AsesoriasViewHolder(View itemView) {
            super(itemView);


            txtDia = itemView.findViewById(R.id.txtDia);

        }

    }

    public AdapterDiaTabla(ArrayList<PlanEntrenamiento> asesorias) {

        this.asesorias = asesorias;
    }

    @Override
    public AdapterDiaTabla.AsesoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_diseno_tabladia, null, false);
        v.setOnClickListener(this);

        AdapterDiaTabla.AsesoriasViewHolder holder = new AdapterDiaTabla.AsesoriasViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiaTabla.AsesoriasViewHolder holder, int position) {
        PlanEntrenamiento asesoria = asesorias.get(position);


        holder.txtDia.setText(asesoria.getDia_ejercicio());



        if (asesoria.getDia_ejercicio().equals("1")){

            holder.txtDia.setText("Domingo");



        }

        if (asesoria.getDia_ejercicio().equals("2")){

            holder.txtDia.setText("Lunes");



        }

        if (asesoria.getDia_ejercicio().equals("3")){

            holder.txtDia.setText("Martes");



        }

        if (asesoria.getDia_ejercicio().equals("4")){

            holder.txtDia.setText("Miercoles");



        }

        if (asesoria.getDia_ejercicio().equals("5")){

            holder.txtDia.setText("Jueves");



        }

        if (asesoria.getDia_ejercicio().equals("6")){

            holder.txtDia.setText("Viernes");



        }

        if (asesoria.getDia_ejercicio().equals("7")){

            holder.txtDia.setText("Sabado");



        }

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

