package com.example.wheatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv,tv2,tv3,tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
    }

    public void get(View v){
        String apikey="c0efdf4a53b81943f8546d5d690e11f8";
        String city = et.getText().toString();
        String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=c0efdf4a53b81943f8546d5d690e11f8";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    String temperature  = object.getString( "temp");
                    String feelstemp  = object.getString( "feels_like");
                    String pressureP  = object.getString( "pressure");
                    String humidityH  = object.getString( "humidity");
                    Double temp = Double.parseDouble(temperature) -273.15;
                    Double temp2 = Double.parseDouble(feelstemp) -273.15;
                    tv.setText("Temperature : "+temp.toString().substring(0,5)+"°C");
                    tv2.setText("Feels like : "+temp2.toString().substring(0,5)+"°C");
                    tv3.setText("Pressure : "+pressureP+"mmHg");
                    tv4.setText("Humidity : "+humidityH+"%");
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }},
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Please check city name", Toast.LENGTH_SHORT).show();
                }
        }) ;
        queue.add(request);
    }
}
