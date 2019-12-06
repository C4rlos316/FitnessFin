package com.appfitnessapp.app.fitnessapp.menu_nuevo.FragmentsU;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appfitnessapp.app.fitnessapp.Arrays.EstadisticaAlimentos;
import com.appfitnessapp.app.fitnessapp.Arrays.EstadisticaEjercicio;
import com.appfitnessapp.app.fitnessapp.Arrays.Usuarios;
import com.appfitnessapp.app.fitnessapp.BaseDatos.Contants;
import com.appfitnessapp.app.fitnessapp.BaseDatos.DBProvider;
import com.appfitnessapp.app.fitnessapp.Login.SplashPantalla;
import com.appfitnessapp.app.fitnessapp.R;
import com.appfitnessapp.app.fitnessapp.Usuario.Calificar;
import com.appfitnessapp.app.fitnessapp.Usuario.ChatActivityUsuario;
import com.appfitnessapp.app.fitnessapp.Usuario.CustomBarChartRender;
import com.appfitnessapp.app.fitnessapp.Usuario.EditarPerfil;
import com.appfitnessapp.app.fitnessapp.Usuario.ListaComprado;
import com.appfitnessapp.app.fitnessapp.Usuario.ListaRecordatorio;
import com.appfitnessapp.app.fitnessapp.Usuario.UsuarioHome;
import com.appfitnessapp.app.fitnessapp.Usuario.UsuarioPerfil;
import com.appfitnessapp.app.fitnessapp.Usuario.UsuarioPlan;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPerfilU extends Fragment {


    ImageButton imgHome, imgPlan, imgChat, btnDerecha, btnIzquierda;
    CircularImageView imgPersona;
    TextView txtNombre, txtPeso, txtAltura, txtObjetivo, txtDiasSemana;
    LinearLayout btnCalificar, btnRecordatorio;

    private ProgressDialog progressDialog;
    private static final String TAG = "BAJARINFO:";
    static DBProvider dbProvider;


    LinearLayout linearCerrar, btnPdf;


    private static FirebaseAuth mAuth;

    BarChart chart, chartHorizontal;

    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;

    ArrayList<BarEntry> BARENTRYH;
    ArrayList<String> BarEntryLabelsH;

    BarDataSet set, setHorizontal;


    String id;

    int index_almuerzo = 0;
    int index_desayuno = 0;
    int index_cena = 0;

    int index_domingo = 0;
    int index_lunes = 0;
    int index_martes = 0;
    int index_miercoles = 0;
    int index_jueves = 0;
    int index_viernes = 0;
    int index_sabado = 0;
    int index_ejercicio = 0;


    int porcentDesayuno = 0;
    int porcentComida = 0;
    int porcentCena = 0;


    int porcentDomingo = 0, porcentLunes = 0, porcentMartes = 0, porcentMiercoles = 0, porcentJueves = 0, porcentViernes = 0, porcentSabado = 0;


    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    int semanaActual, mesActual;


    String fecha = dateFormat.format(date);
    private AlphaAnimation buttonClick = new AlphaAnimation(3F, 0.9F);


    public MenuPerfilU() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.usuario_21_perfil, container, false);


        setHasOptionsMenu(true);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);

        dbProvider = new DBProvider();
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        bajarUsuarios();
        bajarEstadisticasAlimentos();
        bajarEstadisticasEjercicios();


        imgHome = view.findViewById(R.id.imgHome);
        imgPlan = view.findViewById(R.id.imgPlan);
        imgChat = view.findViewById(R.id.imgChat);

        btnDerecha = view.findViewById(R.id.btnDerecha);
        btnIzquierda = view.findViewById(R.id.btnIzquierda);

        btnRecordatorio = view.findViewById(R.id.btnRecordatorio);


        imgPersona = view.findViewById(R.id.imgPersona);
        //linearCerrar = view.findViewById(R.id.linearCerrar);

        mAuth = FirebaseAuth.getInstance();


        txtNombre = view.findViewById(R.id.txtNombreUsuario);
        txtPeso = view.findViewById(R.id.txtPesoActual);
        txtAltura = view.findViewById(R.id.txtEstatura);
        txtObjetivo = view.findViewById(R.id.txtObjetivo);

        txtDiasSemana = view.findViewById(R.id.txtDiasSemana);


        txtDiasSemana.setText(fecha);

        btnCalificar = view.findViewById(R.id.linearCalificar);

        btnPdf = view.findViewById(R.id.btnPdf);


        btnIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = "";

                if (btnIzquierda.isClickable()) {

                    txtDiasSemana.setText(getPreviousDate(fecha));


                }

            }
        });

        btnDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnIzquierda.isClickable()) {
                    String fecha = "";
                    txtDiasSemana.setText(getNextDate(fecha));
                }

            }
        });


        btnCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent intent = new Intent(getContext(), Calificar.class);
                startActivity(intent);

            }
        });


        imgPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), UsuarioPlan.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move_in, R.anim.move_leeft_in);


            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UsuarioHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move_in, R.anim.move_leeft_in);

            }
        });

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChatActivityUsuario.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move, R.anim.move_leeft);

            }
        });


        /*
        linearCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    v.startAnimation(buttonClick);
                    mAuth.signOut();
                    Toast.makeText(getContext(), "Se ha cerrado la sesión correctamente.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), SplashPantalla.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        });

        */

        btnRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ListaRecordatorio.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move, R.anim.move_leeft);

            }
        });


        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ListaComprado.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move, R.anim.move_leeft);
            }
        });


        //________________________________________________________________________________________________

        chart = (BarChart) view.findViewById(R.id.chart1);
        chartHorizontal = (BarChart) view.findViewById(R.id.chartHorizontal);


        CustomBarChartRender barChartRender = new CustomBarChartRender(chart, chart.getAnimator(), chart.getViewPortHandler());
        barChartRender.setRadius(10);
        CustomBarChartRender barChartRenderH = new CustomBarChartRender(chartHorizontal, chartHorizontal.getAnimator(),
                chartHorizontal.getViewPortHandler());
        barChartRenderH.setRadius(10);

        chart.setRenderer(barChartRender);
        chartHorizontal.setRenderer(barChartRenderH);


        //normal
        BarEntryLabels = new ArrayList<String>();
        AddValuesToBarEntryLabels();


//_____________________________________________________________________________________________


        //////////////////////////////////////////////////////////////////////////////////////////
        //horizontal
        BarEntryLabelsH = new ArrayList<String>();
        AddValuesToBarEntryLabelsHorizontal();


//____________________________________________________________________________________________
        //normal
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(9);
        xAxis.setAxisLineColor(Color.WHITE);
        YAxis yAxisRight = chart.getAxisLeft();
        YAxis yAxisLeft = chart.getAxisRight();
        yAxisRight.setEnabled(false);
        yAxisLeft.setEnabled(false);

        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);

        chart.setLogEnabled(true);
        chart.setTouchEnabled(false);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getDescription().setEnabled(false);
        chart.setDescription(null);
        chart.animateY(2000);
        chart.invalidate();


        //esto para los datos
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return BarEntryLabels.get((int) value);
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {
                return 0;
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

//______________________________________________________________________________________


///////////////////////////////////////////////////////////////////////////////////////////////////////
        //horizontal
        XAxis xAxisH = chartHorizontal.getXAxis();
        xAxisH.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisH.setDrawGridLines(false);
        xAxisH.setTextColor(Color.BLACK);
        xAxisH.setTextSize(9);
        xAxisH.setAxisLineColor(Color.WHITE);
        YAxis yAxisRightH = chartHorizontal.getAxisLeft();
        YAxis yAxisLeftH = chartHorizontal.getAxisRight();
        yAxisRightH.setEnabled(false);
        yAxisLeftH.setEnabled(false);

        chartHorizontal.getAxisRight().setEnabled(false);
        chartHorizontal.getLegend().setEnabled(false);

        chartHorizontal.setLogEnabled(true);
        chartHorizontal.setTouchEnabled(false);
        chartHorizontal.setPinchZoom(false);
        chartHorizontal.setDoubleTapToZoomEnabled(false);
        chartHorizontal.getAxisLeft().setDrawGridLines(false);
        chartHorizontal.getXAxis().setDrawGridLines(false);
        chartHorizontal.getDescription().setEnabled(false);
        chartHorizontal.setDescription(null);
        chartHorizontal.animateY(2000);
        chartHorizontal.invalidate();


        //esto para los datosHorizontal
        IAxisValueFormatter formatterH = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return BarEntryLabelsH.get((int) value);
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {
                return 0;
            }
        };
        xAxisH.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxisH.setValueFormatter(formatterH);

        ///////////////////////////////////////////////////////////////////////////////////////////////////7


        return view;

    }


    public void bajarUsuarios() {
        dbProvider = new DBProvider();

        progressDialog.setMessage("Cargando Información...");
        progressDialog.show();
        dbProvider.usersRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "Usuarios 4: ");
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //Log.e(TAG,"Usuarios: "+ snapshot);
                        Log.e(TAG, "Usuarios: " + snapshot);
                        Usuarios usuarios = snapshot.getValue(Usuarios.class);


                        if (usuarios.getId_usuario().equals(id)) {


                            txtNombre.setText(usuarios.getNombre_usuario());
                            if (usuarios.getEstatura().equals("nil")) {
                                txtAltura.setText("");

                            } else {
                                txtAltura.setText(usuarios.getEstatura());

                            }

                            if (usuarios.getObjetivo().equals("nil")) {
                                txtObjetivo.setText("");

                            } else {
                                txtObjetivo.setText(usuarios.getObjetivo());

                            }

                            if (usuarios.getPeso_actual().equals("nil")) {
                                txtPeso.setText("");

                            } else {
                                txtPeso.setText(usuarios.getPeso_actual());

                            }

                            if (usuarios.getFoto_usuario().equals("nil")) {
                                try {
                                    URL urlfeed = new URL(usuarios.getFoto_usuario());
                                    Picasso.get().load(String.valueOf(urlfeed))
                                            .error(R.mipmap.ic_launcher)
                                            .fit()
                                            .noFade()
                                            .into(imgPersona);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            } else {
                                loadImageFromUrl(usuarios.getFoto_usuario());
                                progressDialog.dismiss();
                            }
                        }


                    }
                } else {
                    Log.e(TAG, "Usuarios 3: ");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "ERROR: ");
            }
        });
    }


    public void bajarEstadisticasAlimentos() {
        dbProvider = new DBProvider();
        dbProvider.estadisticaAlimentos().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                BARENTRYH = new ArrayList<>();

                Log.e(TAG, "Usuarios 4: ");
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "Usuarios: " + snapshot);
                        EstadisticaAlimentos estadisticaAlimentos = snapshot.getValue(EstadisticaAlimentos.class);

                        //semana actual y mes
                        cal.setTime(date);
                        semanaActual = cal.get(Calendar.WEEK_OF_MONTH);
                        mesActual = cal.get(Calendar.MONTH);

                        //fecha base datos
                        String semanaBase = estadisticaAlimentos.getFecha_cumplida();
                        DateFormat dateFormattt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        try {
                            Date convertedDate = dateFormattt.parse(semanaBase);
                            Calendar c = Calendar.getInstance();
                            c.setTime(convertedDate);
                            //sacar mes y semana base
                            int diaBase = c.get(Calendar.WEEK_OF_MONTH);
                            int mesBase = c.get(Calendar.MONTH);

                            if (estadisticaAlimentos.getId_usuario().equals(id)) {
                                // Toast.makeText(UsuarioPerfil.this, "hoy"+semanaActual+"base"+diaBase, Toast.LENGTH_SHORT).show();
                                if (semanaActual == diaBase && mesActual == mesBase) {
                                    if (estadisticaAlimentos.getTipo_alimento().equals(Contants.ALMUERZO)) {
                                        index_almuerzo += 1;
                                        porcentComida = index_almuerzo;

                                    } else if (estadisticaAlimentos.getTipo_alimento().equals(Contants.DESAYUNO)) {
                                        index_desayuno += 1;
                                        porcentDesayuno = index_desayuno;

                                    } else {
                                        index_cena += 1;
                                        porcentCena = index_cena;

                                    }

                                    Log.e(TAG, "Datos:1 " + porcentDesayuno);
                                    Log.e(TAG, "Datos:2 " + porcentCena);
                                    Log.e(TAG, "Datos:3 " + porcentComida);

                                    BARENTRYH.add(new BarEntry(0, 0));
                                    BARENTRYH.add(new BarEntry(1, porcentDesayuno));
                                    BARENTRYH.add(new BarEntry(2, porcentComida));
                                    BARENTRYH.add(new BarEntry(3, porcentCena));
                                    BARENTRYH.add(new BarEntry(4, 0));
                                    setHorizontal = new BarDataSet(BARENTRYH, "");
                                    int[] colors = {
                                            Color.rgb(255, 255, 255),
                                            Color.rgb(255, 50, 0),
                                            Color.rgb(0, 194, 176),
                                            Color.rgb(18, 27, 34),
                                            Color.rgb(255, 255, 255)};
                                    ArrayList<IBarDataSet> dataSetsH = new ArrayList<>();
                                    setHorizontal.setColors(colors);
                                    //eliminar texto arriba
                                    setHorizontal.setDrawValues(false);
                                    dataSetsH.add(setHorizontal);
                                    BarData dataHorizontal = new BarData(dataSetsH);
                                    dataHorizontal.setBarWidth(0.5f);
                                    chartHorizontal.setData(dataHorizontal);

                                }

                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    Log.e(TAG, "Usuarios 3: ");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "ERROR: ");
            }
        });
    }

    public void bajarEstadisticasEjercicios() {
        dbProvider = new DBProvider();

        dbProvider.estadisticaEjercicios().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BARENTRY = new ArrayList<>();

                Log.e(TAG, "Usuarios 4: ");
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //Log.e(TAG,"Usuarios: "+ snapshot);
                        Log.e(TAG, "Usuarios: " + snapshot);
                        EstadisticaEjercicio estadisticaEjercicio = snapshot.getValue(EstadisticaEjercicio.class);

                        //semana actual y mes
                        cal.setTime(date);
                        semanaActual = cal.get(Calendar.WEEK_OF_MONTH);
                        mesActual = cal.get(Calendar.MONTH);

                        //fecha base datos
                        String semanaBase = estadisticaEjercicio.getFecha_cumplida();
                        DateFormat dateFormattt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        try {
                            Date convertedDate = dateFormattt.parse(semanaBase);
                            Calendar c = Calendar.getInstance();
                            c.setTime(convertedDate);
                            //sacar mes y semana base
                            int semanaMesBase = c.get(Calendar.WEEK_OF_MONTH);
                            int mesBase = c.get(Calendar.MONTH);
                            int diaBase = c.get(Calendar.DAY_OF_WEEK);

                            Log.e(TAG, "Dia" + diaBase);


                            if (estadisticaEjercicio.getId_usuario().equals(id)) {
                                if (semanaActual == semanaMesBase && mesActual == mesBase) {
                                    if (diaBase == 1) {
                                        index_domingo += 1;
                                        porcentDomingo = index_domingo;

                                    } else if (diaBase == 2) {
                                        index_lunes += 1;
                                        porcentLunes = index_lunes;

                                    } else if (diaBase == 3) {
                                        index_martes += 1;
                                        porcentMartes = index_martes;


                                    } else if (diaBase == 4) {
                                        index_miercoles += 1;
                                        porcentMiercoles = index_miercoles;


                                    } else if (diaBase == 5) {
                                        index_jueves += 1;
                                        porcentJueves = index_jueves;

                                    } else if (diaBase == 6) {

                                        index_viernes += 1;
                                        porcentViernes = index_viernes;
                                    } else if (diaBase == 7) {
                                        index_sabado += 1;
                                        porcentSabado = index_sabado;

                                    }


                                    Log.e(TAG, "Lunes" + porcentLunes);
                                    Log.e(TAG, "Martes" + porcentMartes);
                                    Log.e(TAG, "Miercoles" + porcentMiercoles);
                                    Log.e(TAG, "Jueves" + porcentJueves);
                                    Log.e(TAG, "Viernes" + porcentViernes);
                                    Log.e(TAG, "Sabado" + porcentSabado);
                                    Log.e(TAG, "Domingo" + porcentDomingo);

                                    BARENTRY.add(new BarEntry(0, porcentDomingo));
                                    BARENTRY.add(new BarEntry(1, porcentLunes));
                                    BARENTRY.add(new BarEntry(2, porcentMartes));
                                    BARENTRY.add(new BarEntry(3, porcentMiercoles));
                                    BARENTRY.add(new BarEntry(4, porcentJueves));
                                    BARENTRY.add(new BarEntry(5, porcentViernes));
                                    BARENTRY.add(new BarEntry(6, porcentSabado));
                                    set = new BarDataSet(BARENTRY, "");
                                    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                                    set.setColors(Collections.singletonList(Color.BLACK));
                                    //eliminar texto arriba
                                    set.setDrawValues(false);
                                    dataSets.add(set);
                                    BarData data = new BarData(dataSets);
                                    data.setBarWidth(0.5f);
                                    chart.setData(data);


                                }
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                } else {
                    Log.e(TAG, "Usuarios 3: ");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "ERROR: ");
            }
        });
    }


    public void AddValuesToBarEntryLabels() {

        BarEntryLabels.add("D");
        BarEntryLabels.add("L");
        BarEntryLabels.add("M");
        BarEntryLabels.add("Mi");
        BarEntryLabels.add("J");
        BarEntryLabels.add("V");
        BarEntryLabels.add("S");
    }


    public void AddValuesToBarEntryLabelsHorizontal() {

        BarEntryLabelsH.add("");
        BarEntryLabelsH.add("");
        BarEntryLabelsH.add("");
        BarEntryLabelsH.add("");
        BarEntryLabelsH.add("");


    }
///////////////////////////////////////////////////////////////////

    private void loadImageFromUrl(String url) {

        Picasso.get().load(url).fit().centerInside().noFade().into(imgPersona);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.editar_usuario, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar_U:
                Click();
                return true;
            default:
                return super.onOptionsItemSelected(item); }
    }

    private void Click(){
        Intent intent = new Intent(getActivity(), EditarPerfil.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.move, R.anim.move_leeft);

    }



    private String getPreviousDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        inputDate = dateFormat.format(date);
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -1);
            inputDate = dateFormat.format(c.getTime());
            Log.d("asd", "selected date : " + inputDate);

            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
            inputDate = "";
        }
        return inputDate;
    }

    private String getNextDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        inputDate = dateFormat.format(date);
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, +1);
            inputDate = dateFormat.format(c.getTime());
            Log.d("asd", "selected date : " + inputDate);

            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
            inputDate = "";
        }
        return inputDate;
    }

    public static class GetWeekOfMonthAndYear {

        public static void main(String[] args) {

            //Create calendar instance
            Calendar calendar = Calendar.getInstance();

            System.out.println("Current week of this month = " + calendar.get(Calendar.WEEK_OF_MONTH));


            System.out.println("Current week of this year = " + calendar.get(Calendar.WEEK_OF_YEAR));

        }
    }


}