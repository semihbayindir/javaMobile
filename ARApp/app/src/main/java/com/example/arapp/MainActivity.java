package com.example.arapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import io.github.sceneview.ar.ArSceneView;
import io.github.sceneview.ar.node.ArModelNode;

public class MainActivity extends AppCompatActivity {

    ArSceneView sceneView;
    ExtendedFloatingActionButton placeButton;
    ArModelNode modelNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sceneView = findViewById(R.id.sceneView);
        placeButton = findViewById(R.id.place);
        modelNode = ArModelNode().apply {
            loadModelGlbAsync(

            )
        }

    }

}