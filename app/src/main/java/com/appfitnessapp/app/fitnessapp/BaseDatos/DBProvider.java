package com.appfitnessapp.app.fitnessapp.BaseDatos;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DBProvider {

    private static final String TAG = "DBPROVIDER: ";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    DatabaseReference reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public DatabaseReference dbRef()
    {

        reference = database.getReference();
        return reference;
    }

    public DatabaseReference usersRef() {
        return dbRef().child(Contants.TABLA_USUARIOS);
    }

    public DatabaseReference tablaFeed() {
        return dbRef().child(Contants.TABLA_FEED);
    }

    public DatabaseReference asesoriaInfo() {
        return dbRef().child(Contants.TABLA_ASESORIA_INFO);
    }

    public DatabaseReference formulario() {
        return dbRef().child(Contants.TABLA_FORMULARIO);
    }

    public DatabaseReference planes() {
        return dbRef().child(Contants.TABLA_PLANES);
    }

    public DatabaseReference respuestas() {
        return dbRef().child(Contants.TABLA_RESPUESTA);
    }

    public DatabaseReference valoracionAsesoria() {return dbRef().child(Contants.TABLA_VALORACIONES_ASESORIA);}

    public DatabaseReference estadisticaAlimentos() {return dbRef().child(Contants.TABLA_ESTADISTICAS_ALIMENTOS);}

    public DatabaseReference estadisticaEjercicios() {return dbRef().child(Contants.TABLA_ESTADISTICAS_EJERCICIOS);}

    public DatabaseReference recetas() {return dbRef().child(Contants.TABLA_RECETAS);}

    public DatabaseReference ingredientes() {return dbRef().child(Contants.INGREDIENTES);}

    public DatabaseReference preparacion() {return dbRef().child(Contants.PREPARACION);}

    public DatabaseReference tablaPlanEntrenamiento() {return dbRef().child(Contants.TABLA_PLAN_EJERCICIO);}


    public DatabaseReference tablaEjercicios() {return dbRef().child(Contants.EJERCICIOS);}







    public DatabaseReference chatRef() {return dbRef().child(Contants.TABLA_CHAT);}


    //Creacion usuario
    public void createUser( String email, String id,
                           String name, String pass, String phone,
                           String photo, String token, String type,String pesoActual,String estatura,String objetivo) {
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.ID_USUARIO, id);
        updates.put(Contants.TELEFONO_USUARIO, phone);
        updates.put(Contants.NOMBRE_USUARIO, name);
        updates.put(Contants.EMAIL_USUARIO, email);
        updates.put(Contants.TOKEN_USUARIO, token);
        updates.put(Contants.TIPO_USUARIO, type);
        updates.put(Contants.FOTO_USUARIO, photo);
        updates.put(Contants.CONTRASENA_USUARIO, pass);
        updates.put(Contants.PESO_ACTUAL, pesoActual);
        updates.put(Contants.ESTATURA, estatura);
        updates.put(Contants.OBJETIVO, objetivo);



        usersRef().child(id).updateChildren(updates);
    }


    public void  subirFeed(String tipo_feed,Boolean is_gratis,String imagen_feed,String costo_pdf,String url_tipo,
                           String timestamp,String descripcion){
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.TIPO_FEED, tipo_feed);
        updates.put(Contants.IS_GRATIS, is_gratis);
        updates.put(Contants.IMAGEN_FEED, imagen_feed);
        updates.put(Contants.COSTO_PDF, costo_pdf);
        updates.put(Contants.URL_TIPO, url_tipo);
        updates.put(Contants.TIMESTAMP, timestamp);
        updates.put(Contants.DESCRIPCION, descripcion);

        tablaFeed().child(tipo_feed).updateChildren(updates);



    }

    public void updatePhoto(String photo, String id){
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.FOTO_USUARIO, photo);
        usersRef().child(id).updateChildren(updates);
    }

    public void updateImgAntes(String photo,String id){
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.IMAGEN_ANTES, photo);
        valoracionAsesoria().child(id).updateChildren(updates);

    }

    public void updateImgDespues(String photo,String id){
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.IMAGEN_DESPUES, photo);
        valoracionAsesoria().child(id).updateChildren(updates);

    }

    public void updateName(String name, String id) {

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.NOMBRE_USUARIO, name);
        usersRef().child(id).updateChildren(updates);
    }

    public void updatePhone(String telefono, String id) {

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.TELEFONO_USUARIO, telefono);
        usersRef().child(id).updateChildren(updates);
    }

    public void updatePeso(String peso,String id){

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.PESO_ACTUAL, peso);
        usersRef().child(id).updateChildren(updates);

    }

    public void updateObjetivo(String objetivo,String id){

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.OBJETIVO, objetivo);
        usersRef().child(id).updateChildren(updates);

    }

    public void updateEstatura(String estatura,String id){

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.ESTATURA, estatura);
        usersRef().child(id).updateChildren(updates);

    }






    public void updateEmail(String id, String email) {

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.EMAIL_USUARIO, email);
        usersRef().child(id).updateChildren(updates);
    }



   public void updatePass(String id, String pass) {

        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.CONTRASENA_USUARIO, pass);
        usersRef().child(id).updateChildren(updates);
    }


    //Asesoria
    public void subirAsesoria(String alimentos_descripcion,String alimentos_imagen, String costo_asesoria, String descripcion_asesoria,
                              String id_asesoria, String imagen_portada, String rutinas_descripcion, String rutinas_imagen,
                              String video_explicativo){

        Map<String, Object> updates = new HashMap<>();
        updates.put(Contants.ALIMENTOS_DESCRIPCION, alimentos_descripcion);
        updates.put(Contants.ALIMENTOS_IMAGEN, alimentos_imagen);
        updates.put(Contants.COSTO_ASESORIA, costo_asesoria);
        updates.put(Contants.DESCRIPCION_ASESORIA, descripcion_asesoria);
        updates.put(Contants.ID_ASESORIA, id_asesoria);
        updates.put(Contants.IMAGEN_PORTADA, imagen_portada);
        updates.put(Contants.RUTINAS_DESCRIPCION, rutinas_descripcion);
        updates.put(Contants.RUTINAS_IMAGEN, rutinas_imagen);
        updates.put(Contants.VIDEO_EXPLICATIVO, video_explicativo);

        asesoriaInfo().child(id_asesoria).updateChildren(updates);
    }



    //Formulario
    public void subirFormulario(String id_pregunta,String pregunta){
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.ID_PREGUNTA , id_pregunta);
        updates.put(Contants.PREGUNTA , pregunta);

        formulario().child(id_pregunta).updateChildren(updates);

    }


    //Planes
    public void subirPlanes(String costo_plan,String descripcion_plan, String id_plan, Boolean isVendida, String meses_plan,
                            String nombre_plan){
        Map<String, Object> updates = new HashMap<>();

        updates.put(Contants.COSTO_PLAN , costo_plan);
        updates.put(Contants.DESCRIPCION_PLAN , descripcion_plan);
        updates.put(Contants.ID_PLAN , id_plan);
        updates.put(Contants.IS_VENDIDA , isVendida );
        updates.put(Contants.MESES_PLAN , meses_plan);
        updates.put(Contants.NOMBRE_PLAN , nombre_plan);

        planes().child(id_plan).updateChildren(updates);
    }

    //ALIMENTOS
    public void subirAlimentos(String id_usuario,String fecha_cumplida, String tipo_alimentos){
        Map<String, Object> updates = new HashMap<>();

        String key = respuestas().push().getKey();
        updates.put(Contants.ID_USUARIO , id_usuario);
        updates.put(Contants.FECHA_CUMPLIDA , fecha_cumplida);
        updates.put(Contants.TIPO_ALIMENTO , tipo_alimentos);

        estadisticaAlimentos().child(key).updateChildren(updates);
    }


    public void subirRecetas(String id_alimento,String fecha, String tipo,String imagen,String calorias,String tiempo,String cantidad,
                             String nombre){
        Map<String, Object> updates = new HashMap<>();

        String key = respuestas().push().getKey();
        updates.put(Contants.ID_ALIMENTO , key);
        updates.put(Contants.FECHA_ALIMENTO , fecha);
        updates.put(Contants.TIPO_ALIMENTO , tipo);
        updates.put(Contants.IMAGEN_ALIMENTO , imagen);
        updates.put(Contants.KILOCALORIAS , calorias);
        updates.put(Contants.MIN_ALIMENTO , tiempo);
        updates.put(Contants.PORCIONES , cantidad);
        updates.put(Contants.NOMBRE_ALIMENTO , nombre);

        recetas().child(key).updateChildren(updates);
    }



    //Respuestas
    public void subirRespuestas(String id_pregunta,String id_respuesta, String id_usuario, String respuesta){
        Map<String, Object> updates = new HashMap<>();

        String key = respuestas().push().getKey();
        updates.put(Contants.ID_PREGUNTA , id_pregunta);
        updates.put(Contants.ID_RESPUESTA , key);
        updates.put(Contants.ID_USUARIO , id_usuario);
        updates.put(Contants.RESPUESTA , respuesta);

        respuestas().child(key).updateChildren(updates);
    }


    //ingredientes
    public void subirIngredientes(String id_ingrediente,String id_alimento, String nombre_ingrediente, String cantidad){
        Map<String, Object> updates = new HashMap<>();

        String key = respuestas().push().getKey();
        updates.put(Contants.ID_INGREDIENTE , key);
        updates.put(Contants.ID_ALIMENTO , id_alimento);
        updates.put(Contants.NOMBRE_INGREDIENTE , nombre_ingrediente);
        updates.put(Contants.CANTIDAD , cantidad);

        ingredientes().child(key).updateChildren(updates);
    }

    //preparacion
    public void subirPasos(String id_paso,String id_alimento, String nombre_paso, String descripcion_paso){
        Map<String, Object> updates = new HashMap<>();

        String key = preparacion().push().getKey();
        updates.put(Contants.ID_PREPARACION , key);
        updates.put(Contants.ID_ALIMENTO , id_alimento);
        updates.put(Contants.NOMBRE_PASO , nombre_paso);
        updates.put(Contants.DESCRIPCION_PASO , descripcion_paso);

        preparacion().child(key).updateChildren(updates);
    }


    //tablaPlanEntrenamiento
    public void subirPlanEntrenamiento(String min_ejercicio,String nivel_ejercicio, String num_ejercicios, String descripcion_ejercicios,
                                       String id_plan_ejercicio){
        Map<String, Object> updates = new HashMap<>();

        String key = tablaPlanEntrenamiento().push().getKey();
        updates.put(Contants.MIN_EJERCICIO , min_ejercicio);
        updates.put(Contants.NIVEL_EJERCICIO , nivel_ejercicio);
        updates.put(Contants.NUM_EJERCICIOS , num_ejercicios);
        updates.put(Contants.DESCRIPCION_EJERCICIOS , descripcion_ejercicios);
        updates.put(Contants.ID_PLAN_EJERCICIO , key);


        tablaPlanEntrenamiento().child(key).updateChildren(updates);
    }

    //tabla Ejercicios
    public void subirEjercicios(String nombre_ejercicio,String rondas, String repeticiones, String video_ejercicio,
                                        String imagenes_ejercicio,String id_ejercicio){
        Map<String, Object> updates = new HashMap<>();

        String key = tablaEjercicios().push().getKey();
        updates.put(Contants.NOMBRE_EJERCICIO , nombre_ejercicio);
        updates.put(Contants.RONDAS , rondas);
        updates.put(Contants.REPETICIONES , repeticiones);
        updates.put(Contants.VIDEO_EJERCICIO , video_ejercicio);
        updates.put(Contants.IMAGENES_EJERCICIO , imagenes_ejercicio);
        updates.put(Contants.ID_EJERCICIO , key);

        tablaEjercicios().child(key).updateChildren(updates);
    }

    //Valoraciones
    public void subirValoraciones(String descripcion_valoracion,String fecha_valoracion, String id_asesoria, String id_valoracion,
                                  String imagen_antes,String imagen_despues, String nombre_usuario_valoracion,String valoracion){
        Map<String, Object> updates = new HashMap<>();

        String key = valoracionAsesoria().push().getKey();

        updates.put(Contants.DESCRIPCION_VALORACION , descripcion_valoracion);
        updates.put(Contants.FECHA_VALORACION , fecha_valoracion);
        updates.put(Contants.ID_ASESORIA , id_asesoria);
        updates.put(Contants.ID_VALORACION , key);
        updates.put(Contants.IMAGEN_ANTES , imagen_antes);
        updates.put(Contants.IMAGEN_DESPUES , imagen_despues);
        updates.put(Contants.ID_USUARIO_VALORACION , nombre_usuario_valoracion);
        updates.put(Contants.VALORACION , valoracion);

        valoracionAsesoria().child(key).updateChildren(updates);
        }



    public void subirChats(String id_servicio, String senderid, String text){
        Map<String, Object> data = new HashMap<>();
        String key = chatRef().push().getKey();
        data.put(Contants.ID_SERVICIO, id_servicio);
        data.put(Contants.SENDERID, senderid);
        data.put(Contants.TEXT, text);
        chatRef().child(id_servicio).child(key).updateChildren(data);
    }



}
