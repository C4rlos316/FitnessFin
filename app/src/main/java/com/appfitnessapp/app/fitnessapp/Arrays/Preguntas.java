package com.appfitnessapp.app.fitnessapp.Arrays;

public class Preguntas {

    public String id_pregunta;
    public String pregunta;
    public String nombre_pregunta;
    public String admin;


    public Preguntas(String id_pregunta, String pregunta, String nombre_pregunta, String admin) {
        this.id_pregunta = id_pregunta;
        this.pregunta = pregunta;
        this.nombre_pregunta = nombre_pregunta;
        this.admin = admin;
    }

    public Preguntas(){}

    public String getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(String id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getNombre_pregunta() {
        return nombre_pregunta;
    }

    public void setNombre_pregunta(String nombre_pregunta) {
        this.nombre_pregunta = nombre_pregunta;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}