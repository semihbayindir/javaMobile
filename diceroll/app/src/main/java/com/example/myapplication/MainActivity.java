package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView ImageViewDice;
    private ImageView ImageViewDice2;
    private Random random1 = new Random();

    DatabaseHelper mDatabaseHelper;
    private EditText editText;
    private Button btnAdd, btnViewData;


    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);
        ImageViewDice = findViewById(R.id.image1);
        ImageViewDice2 = findViewById(R.id.image2);
        Button button = (Button) findViewById(R.id.button);
        btnViewData = (Button) findViewById(R.id.btnView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
                rollDice();
            }

        });
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    private void rollDice() {
        int randomNumber1 = random1.nextInt(6)+1;
        String newEntry = String.valueOf(randomNumber1);

        switch (randomNumber1){
            case 1 :
                ImageViewDice.setImageResource(R.drawable.dice1);
                AddData(newEntry);
                break;
            case 2 :
                ImageViewDice.setImageResource(R.drawable.dice2);
                AddData(newEntry);
                break;
            case 3 :
                ImageViewDice.setImageResource(R.drawable.dice3);
                AddData(newEntry);
                break;
            case 4 :
                ImageViewDice.setImageResource(R.drawable.dice4);
                AddData(newEntry);
                break;
            case 5 :
                ImageViewDice.setImageResource(R.drawable.dice5);
                AddData(newEntry);
                break;
            case 6 :
                ImageViewDice.setImageResource(R.drawable.dice6);
                AddData(newEntry);
                break;


        }
    }
    private void rollDice2() {
        int randomNumber1 = random1.nextInt(6)+1;
        String newEntry = String.valueOf(randomNumber1);

        switch (randomNumber1){
            case 1 :
                ImageViewDice2.setImageResource(R.drawable.dice1);
                AddData(newEntry);
                break;
            case 2 :
                ImageViewDice2.setImageResource(R.drawable.dice2);
                AddData(newEntry);
                break;
            case 3 :
                ImageViewDice2.setImageResource(R.drawable.dice3);
                AddData(newEntry);
                break;
            case 4 :
                ImageViewDice2.setImageResource(R.drawable.dice4);
                AddData(newEntry);
                break;
            case 5 :
                ImageViewDice2.setImageResource(R.drawable.dice5);
                AddData(newEntry);
                break;
            case 6 :
                ImageViewDice2.setImageResource(R.drawable.dice6);
                AddData(newEntry);
                break;


        }
    }
}