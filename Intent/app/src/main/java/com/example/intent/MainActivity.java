package com.example.intent;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.editTextTextPersonName);
    }
    public void go(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
        intent.putExtra("name",name.getText().toString());
        startActivity(intent);
    }
}