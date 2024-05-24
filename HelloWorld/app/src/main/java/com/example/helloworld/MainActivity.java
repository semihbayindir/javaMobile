package com.example.helloworld;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class MainActivity extends AppCompatActivity {
    ListView ListView;
    String countries[] = { "United State of America","Brazil","Turkiye","Egypt" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView = findViewById(R.id.listView);
        ArrayAdapter<String> arr;
        arr= new ArrayAdapter<String>
                (this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        countries);
        ListView.setAdapter(arr);
    }
}