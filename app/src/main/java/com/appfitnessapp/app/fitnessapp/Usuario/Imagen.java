package com.appfitnessapp.app.fitnessapp.Usuario;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.appfitnessapp.app.fitnessapp.R;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Imagen extends AppCompatActivity {

    PhotoView imgImagen;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_14_feed_imagen);


        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);
        getSupportActionBar().setTitle("");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        tipo =extras.getString("imagen");

        imgImagen=findViewById(R.id.imgImagen);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imgImagen);
        photoViewAttacher.update();
        loadImageFromUrl(tipo);



        }


    private void loadImageFromUrl(String url) {

        Picasso.get().load(url).into(imgImagen);
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
    }


    }
