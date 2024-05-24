package com.example.weather_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView tvResult;

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String key = "e6b358bcb3e47c1040f6e84c03d68bd0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        editText = findViewById(R.id.editText);
        tvResult = findViewById(R.id.tvResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editText.getText().toString().trim();
                if (!city.isEmpty()) {
                    new Weather().execute(city);
                } else {
                    tvResult.setText("Şehir bilgisi boş bırakılamaz!");
                }
            }
        });
    }
    private class Weather extends AsyncTask<String,Void,JSONObject>{
        @Override
        protected JSONObject doInBackground(String... params){
            String city=params[0];
            String jsonString=getWeather(city);
            try{
                return new JSONObject(jsonString);
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject){
            if (jsonObject!=null){
                try{
                    JSONObject main = jsonObject.getJSONObject("main");
                    JSONArray weather = jsonObject.getJSONArray("weather");
                    JSONObject weatherObject = weather.getJSONObject(0);

                    String cityN=jsonObject.getString("name");
                    String description=weatherObject.getString("description");
                    double temp=main.getDouble("temp");
                    float pressure = main.getInt("pressure");
                    int humidity = main.getInt("humidity");

                    String weatherInfo = String.format("Şehir: %s\nDescription: %s\nSıcaklık: %.1f°C\nBasınç: %f\nNem: %d%%\n", cityN, description, temp, pressure, humidity);
                    tvResult.setText(weatherInfo);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String getWeather(String city) {
        String urlString = String.format("%s?q=%s&appid=%s&units=metric", url, city, key);
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    }