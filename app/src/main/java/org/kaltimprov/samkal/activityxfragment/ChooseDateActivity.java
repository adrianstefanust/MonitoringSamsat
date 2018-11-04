package org.kaltimprov.samkal.activityxfragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.kaltimprov.samkal.R;
import org.kaltimprov.samkal.network.RESTHelper;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;


public class ChooseDateActivity extends AppCompatActivity {
    int tahun;
    int  bulan;
    int hari;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.chooseDate)
    RelativeLayout rl;
    Button okButton;
    String[] bulanArray= {"Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};
    private RESTHelper rRest;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);
        Button selectDate = findViewById(R.id.btnDate);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        rRest = new RESTHelper();

        okButton = findViewById(R.id.button_ok);
        final TextView date = findViewById(R.id.tvSelectedDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ChooseDateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + " " + bulanArray[(month)] + " " + year);
                                tahun = year;
                                bulan = month;
                                hari=day;
                            }
                        }, year, month, dayOfMonth);
                okButton.setVisibility(View.VISIBLE);
                datePickerDialog.show();
            }
        });

        okButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String temp = tahun+"-"+bulan+"-"+hari;
                rRest.monitoring(temp);
                progressDialog = new ProgressDialog(ChooseDateActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setTitle("Mohon Tunggu");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent i = new  Intent(ChooseDateActivity.this,PDFViewerActivity.class);
                        i.putExtra("pdf",1);
                        startActivity(i);
                    }
                },1000 );

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    }

