package com.appfitnessapp.app.fitnessapp.Usuario.MenuPago;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.Login.IniciarSesion;
import com.appfitnessapp.app.fitnessapp.R;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuPago.Fragments.MenuPerfilU;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuSinPago.Fragments.Menu_ConocenosU;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuSinPago.Fragments.Menu_EbooksU;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuSinPago.Fragments.Menu_PdfsU;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuSinPago.Fragments.Menu_RecetariosU;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.appfitnessapp.app.fitnessapp.Usuario.MenuPago.Fragments.MenuAsesoriaPago;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuPago.Fragments.MenuInicioPago;
import com.appfitnessapp.app.fitnessapp.Usuario.MenuPago.Fragments.MenuRutinasPago;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;

public class Menu_UsuarioPago extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static FirebaseAuth mAuth;




    DBProvider dbProvider;


    private ProgressDialog progressDialog;

    private static final String TAG = "Principal: ";

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__usuario_pago);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);

        dbProvider = new DBProvider();



        mAuth = FirebaseAuth.getInstance();




        DrawerLayout drawer = findViewById(R.id.drawer_layout_pago);
        NavigationView navigationView = findViewById(R.id.nav_view_pago);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportDefaultUsuario();

        navigationView.getMenu().getItem(0).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(5).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(6).setActionView(R.layout.menu_imagen);
        navigationView.getMenu().getItem(7).setActionView(R.layout.menu_imagen);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_pago);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu__usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        boolean fragmentTransaction = false;
        Fragment fragment = null;
        switch (item.getItemId()) {

            case R.id.nav_inicio:
                fragment= new MenuInicioPago();
                fragmentTransaction = true;

                break;


            case R.id.nav_conocenos:
                fragment= new Menu_ConocenosU();
                fragmentTransaction = true;

                break;

            case R.id.nav_asesorias:
                fragment= new MenuAsesoriaPago();
                fragmentTransaction = true;

                break;


            case R.id.nav_rutinas:

                fragment=new MenuRutinasPago();
                fragmentTransaction = true;

                break;

            case R.id.nav_pdfs:
                fragment=new Menu_PdfsU();
                fragmentTransaction = true;

                break;


            case R.id.nav_ebooks:
                fragment=new Menu_EbooksU();
                fragmentTransaction = true;

                break;

            case R.id.nav_recetarios:
                fragment=new Menu_RecetariosU();
                fragmentTransaction = true;

                break;

            case R.id.nav_perfil:

                fragment=new MenuPerfilU();
                fragmentTransaction = true;

                break;

            case R.id.nav_cerrarsesion:
                mAuth.signOut();
                Intent intent=new Intent(Menu_UsuarioPago.this, IniciarSesion.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                break;
        }

        if (fragmentTransaction){

            getSupportFragmentManager().beginTransaction().replace(R.id.content_usuario_pago,fragment).commit();
            getSupportActionBar().setTitle(item.getTitle()); }



        DrawerLayout drawer =findViewById(R.id.drawer_layout_pago);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void getSupportDefaultUsuario(){
        NavigationView navigationView=findViewById(R.id.nav_view_pago);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_usuario_pago,new MenuInicioPago()).commit();

        MenuItem item =navigationView.getMenu().getItem(0);
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());

    }

}
