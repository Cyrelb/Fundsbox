package com.luxembourgaifm.fundsbox.activities;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.luxembourgaifm.fundsbox.R;
import com.luxembourgaifm.fundsbox.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class InvestActivity  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private SeekBar seekBar;
    PieChartView pieChartView;
    private Button investButton;
    private EditText amount;

    private int colorRisk2 = Color.rgb(192, 255, 140);  // green
    private int colorRisk2bis =  Color.rgb(220, 250, 120); // green
    private int colorRisk2ter =  Color.rgb(230, 250, 110); // green
    private int colorRisk3 = Color.rgb(140, 234, 255);  // blue
    private int colorRisk3bis = Color.rgb(150, 224, 255);  // blue
    private int colorRisk4 = Color.rgb(255, 247, 140); // yellow
    private int colorRisk4bis = Color.rgb(255, 230, 140); // yellow
    private int colorRisk5 =  Color.rgb(255, 208, 140);// Orange
    private int colorRisk5bis =  Color.rgb(255, 198, 140);// Orange
    private int colorRisk6 = Color.rgb(255, 160, 140);
    private int colorRisk6bis = Color.rgb(255, 150, 140);
    private int colorRisk6ter = Color.rgb(255, 130, 120);
    private int colorRisk6quat = Color.rgb(255, 100, 100);

    private String asset1Name = "Short duration govies at work";
    private String asset2Name = "Short duration at work";
    private String asset3Name = "Inflation at work";
    private String asset4Name = "Gov bonds at work";
    private String asset5Name = "Fixed income at work";
    private String asset6Name = "Corp bonds at work";
    private String asset7Name = "High Yield at work";
    private String asset8Name = "Dynamic";
    private String asset9Name = "Equities at work";
    private String asset10Name = "European Equities at work";
    private String asset11Name = "Asian Equities at work";
    private String asset12Name = "Sustainable equities at work ";

    private double perc50 = 0.5;
    private double perc10= 0.1;
    private double perc30 = 0.3;
    private double perc20 = 0.2;

    private String user =  SharedPrefManager.getInstance(InvestActivity.this).getUser();

    private int selectedRisk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);

        investButton = findViewById(R.id.investButton);
        investButton.setOnClickListener(this);

        amount = findViewById(R.id.amount);


        seekBar = (SeekBar) findViewById(R.id.seekBar);
        // Set SeekBar Thumbnail Programmatically
        //seekBar.setThumb(getResources().getDrawable(R.drawable.seekbar_progressbar));
        // Add listener to SeekBar


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int seekBarProgress = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
              //  textView.setText("Current Progress: " + seekBarProgress + " / " + seekBar.getMax());
                selectedRisk = seekBarProgress;

                switch (seekBarProgress) {

                    case 0: {
                        List<SliceValue> pieData = new ArrayList<>();
                        pieData.add(new SliceValue(50, colorRisk2).setLabel(asset1Name));
                        pieData.add(new SliceValue(50, colorRisk2).setLabel(asset2Name));
                        PieChartData pieChartData = new PieChartData(pieData);
                        pieChartData.setHasLabels(true);
                        pieChartData.setHasCenterCircle(true).setCenterText1("Conservative").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
                        pieChartView.setPieChartData(pieChartData);
                        break;
                    }
                    case 1: {
                        List<SliceValue> pieData = new ArrayList<>();
                        pieData.add(new SliceValue(10, colorRisk2).setLabel(asset1Name));
                        pieData.add(new SliceValue(10, colorRisk2bis).setLabel(asset2Name));
                        pieData.add(new SliceValue(30, colorRisk3).setLabel(asset3Name));
                        pieData.add(new SliceValue(30, colorRisk3bis).setLabel(asset4Name));
                        pieData.add(new SliceValue(10, colorRisk4).setLabel(asset5Name));
                        pieData.add(new SliceValue(10, colorRisk4bis).setLabel(asset6Name));
                        PieChartData pieChartData = new PieChartData(pieData);
                        pieChartData.setHasLabels(true);
                        pieChartData.setHasCenterCircle(true).setCenterText1("Conservative plus").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
                        pieChartView.setPieChartData(pieChartData);
                        break;
                    }
                    case 2: {
                        List<SliceValue> pieData = new ArrayList<>();
                        pieData.add(new SliceValue(10, colorRisk3).setLabel(asset3Name));
                        pieData.add(new SliceValue(10, colorRisk3bis).setLabel(asset4Name));
                        pieData.add(new SliceValue(30, colorRisk4).setLabel("Fixed income at work"));
                        pieData.add(new SliceValue(30, colorRisk4bis).setLabel("Corp bonds at work"));
                        pieData.add(new SliceValue(10, colorRisk5).setLabel("High Yield at work"));
                        pieData.add(new SliceValue(10, colorRisk5bis).setLabel("Dynamic"));
                        PieChartData pieChartData = new PieChartData(pieData);
                        pieChartData.setHasLabels(true);
                        pieChartData.setHasCenterCircle(true).setCenterText1("Balanced").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
                        pieChartView.setPieChartData(pieChartData);
                        break;
                    }
                    case 3: {
                        List<SliceValue> pieData = new ArrayList<>();
                        pieData.add(new SliceValue(10, colorRisk4).setLabel(asset5Name));
                        pieData.add(new SliceValue(10, colorRisk4bis).setLabel(asset6Name));
                        pieData.add(new SliceValue(30, colorRisk5).setLabel(asset7Name));
                        pieData.add(new SliceValue(30, colorRisk5bis).setLabel(asset8Name));
                        pieData.add(new SliceValue(10, colorRisk6).setLabel(asset9Name));
                        pieData.add(new SliceValue(10, colorRisk6bis).setLabel(asset10Name));
                        PieChartData pieChartData = new PieChartData(pieData);
                        pieChartData.setHasLabels(true);
                        pieChartData.setHasCenterCircle(true).setCenterText1("Dynamic").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
                        pieChartView.setPieChartData(pieChartData);
                        break;
                    }
                    case 4: {
                        List<SliceValue> pieData = new ArrayList<>();
                        pieData.add(new SliceValue(30, colorRisk6).setLabel(asset9Name));
                        pieData.add(new SliceValue(30, colorRisk6bis).setLabel(asset10Name));
                        pieData.add(new SliceValue(20, colorRisk6ter).setLabel(asset11Name));
                        pieData.add(new SliceValue(20, colorRisk6quat).setLabel(asset12Name));
                        PieChartData pieChartData = new PieChartData(pieData);
                        pieChartData.setHasLabels(true);
                        pieChartData.setHasCenterCircle(true).setCenterText1("Dynamic plus").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
                        pieChartView.setPieChartData(pieChartData);
                        break;
                    }


                }


            }

        });

        pieChartView = findViewById(R.id.chart);

        List<SliceValue> pieData = new ArrayList<>();
        pieData.add(new SliceValue(50, colorRisk2).setLabel(asset1Name));
        pieData.add(new SliceValue(50, colorRisk2).setLabel(asset2Name));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true).setCenterText1("Portfolio").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);


        amount.addTextChangedListener(onTextChangedListener());
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                amount.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    amount.setText(formattedString);
                    amount.setSelection(amount.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                amount.addTextChangedListener(this);
            }
        };
    }



    @Override
    public void onClick(View v) {

        switch(v.getId()) {
// SharedPrefManager.getInstance(InvestActivity.this).getUser()

            /*

                try {
                    Call<ResponseBody> call = RetrofitClient
                            .getmInstance()
                            .getApi()
                            .invest("test@test", "test", "2000", "EUR");

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                            String s = response.body().string();
                            Toast.makeText(InvestActivity.this, s, Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(InvestActivity.this, "ERROR1", Toast.LENGTH_SHORT).show();
                                // e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {


                        }
                    });
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                */
            case R.id.investButton:
                switch (selectedRisk) {
                    case 0: {
                        double amountTrx = Double.valueOf( amount.getText().toString()) * perc50;
                        insertTrx(asset1Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset2Name, Double.toString(amountTrx), "EUR" );
                        break;
                    }
                    case 1: {
                        double amountTrx = Double.valueOf( amount.getText().toString()) * perc10;
                        insertTrx(asset1Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset2Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc30;
                        insertTrx(asset2Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset3Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc10;
                        insertTrx(asset4Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset5Name, Double.toString(amountTrx), "EUR" );
                        break;
                    }
                    case 2: {
                        double amountTrx = Double.valueOf( amount.getText().toString()) * perc10;
                        insertTrx(asset3Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset4Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc30;
                        insertTrx(asset5Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset6Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc10;
                        insertTrx(asset7Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset8Name, Double.toString(amountTrx), "EUR" );
                        break;
                    }
                    case 3: {
                        double amountTrx = Double.valueOf( amount.getText().toString()) * perc10;
                        insertTrx(asset5Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset6Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc30;
                        insertTrx(asset7Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset8Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc10;
                        insertTrx(asset9Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset10Name, Double.toString(amountTrx), "EUR" );
                        break;

                    }
                    case 4: {
                        double amountTrx = Double.valueOf( amount.getText().toString()) * perc30;
                        insertTrx(asset9Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset10Name, Double.toString(amountTrx), "EUR" );
                        amountTrx = Double.valueOf( amount.getText().toString()) * perc20;
                        insertTrx(asset11Name, Double.toString(amountTrx), "EUR" );
                        insertTrx(asset12Name, Double.toString(amountTrx), "EUR" );
                        break;
                    }
                }


                /*
                String asset = "asset1";


                RequestQueue queue = Volley.newRequestQueue(this);
                String url = "http://luxembourgaifm.com/index.php?user="
                        + SharedPrefManager.getInstance(InvestActivity.this).getUser()
                        + "&asset="
                        + asset
                        + "&amount="
                        + amount.getText()
                        +"&ccy=EUR";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(InvestActivity.this, response, Toast.LENGTH_SHORT).show();

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(InvestActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                */
                startActivity(new Intent(investButton.getContext() ,ConfirmationActivity.class));
                break;


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void insertTrx(String asset, String amount, String ccy ) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://luxembourgaifm.com/index.php?user="
                + user
                + "&asset="
                + asset
                + "&amount="
                + amount
                +"&ccy="
                +ccy;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(InvestActivity.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(InvestActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
