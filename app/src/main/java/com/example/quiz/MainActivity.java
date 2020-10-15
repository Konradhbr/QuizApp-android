package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import cn.pedant.SweetAlert.SweetAlertDialog;




public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        register(R.layout.activity_registerpanel);

        super.onCreate(savedInstanceState);

    }

    public void register(int v) {
        Intent register = new Intent(com.example.quiz.MainActivity.this, RegisterPanel.class);
        startActivity(register);
    }

        }