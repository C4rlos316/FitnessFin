package com.appfitnessapp.app.fitnessapp.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.appfitnessapp.app.fitnessapp.Arrays.Respuestas;
import com.appfitnessapp.app.fitnessapp.R;

import java.util.ArrayList;

public class AdapterRespuestas  extends RecyclerView.Adapter<AdapterRespuestas.RespuestasViewHolder>
        implements View.OnClickListener {

    AdapterRespuestas adapterPreguntas;
    private View.OnClickListener listener;
    ArrayList<Respuestas> respuestas;


    public static class RespuestasViewHolder extends RecyclerView.ViewHolder{


        TextView txtPregunta;
        TextView edtRespuesta;

        public RespuestasViewHolder (View itemView) {
            super(itemView);
            txtPregunta=itemView.findViewById(R.id.txtPregunta);
            edtRespuesta=itemView.findViewById(R.id.edtRespuesta);


        }

    }

    public AdapterRespuestas(ArrayList<Respuestas>respuestas){

        this.respuestas=respuestas;
    }

    @Override
    public AdapterRespuestas.RespuestasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.respuestas_diseno,null,false);
        v.setOnClickListener(this);

        AdapterRespuestas.RespuestasViewHolder holder=new AdapterRespuestas.RespuestasViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRespuestas.RespuestasViewHolder holder,  int position) {
        Respuestas respuesta = respuestas.get(position);


        holder.txtPregunta.setText(respuesta.getId_pregunta());
        holder.edtRespuesta.setText(respuesta.getRespuesta());




    }




    @Override
    public int getItemCount() {
        return respuestas.size();
    }



    public void setOnClickListener(View.OnClickListener listener) {



        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }


}
