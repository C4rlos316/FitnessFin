package com.appfitnessapp.app.fitnessapp.Usuario.Chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.FirebaseChatMainApp;
import com.appfitnessapp.app.fitnessapp.R;

public class ChatActivityUsuario extends AppCompatActivity {


    public static void startActivity(Context context,
                                     String name, String uid, String id_admin, String id_servicio) {
        Intent intent = new Intent(context, ChatActivityUsuario.class);
        intent.putExtra(Contants.ARG_RECEIVER, name);
        intent.putExtra(Contants.ARG_RECEIVER_UID, uid);
        intent.putExtra(Contants.ARG_FIREBASE_TOKEN, id_admin);
        intent.putExtra(Contants.ID_SERVICIO, id_servicio);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    private void init() {
        Intent intent = new Intent(ChatActivityUsuario.this, ChatFragmentUsuario.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",Contants.ARG_RECEIVER);
        bundle.putString("uid",Contants.ARG_RECEIVER_UID);
        bundle.putString("id_admin",Contants.ARG_FIREBASE_TOKEN);
        bundle.putString("token",Contants.TOKEN);
        bundle.putString("id_servicio",Contants.ID_SERVICIO);
        intent.putExtras(bundle);
        intent.putExtra("anim id in", R.anim.down_in);
        intent.putExtra("anim id out", R.anim.down_out);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.move, R.anim.move_leeft);

    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseChatMainApp.setChatActivityOpen(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseChatMainApp.setChatActivityOpen(false);
    }

}
