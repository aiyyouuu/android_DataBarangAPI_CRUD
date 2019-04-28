package com.example.mahasiswapc.myapplication.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahasiswapc.myapplication.R;
import com.example.mahasiswapc.myapplication.model.getall.DataItem;
import com.example.mahasiswapc.myapplication.model.getall.GetResponse;
import com.example.mahasiswapc.myapplication.presenter.MainPresenter;
import com.example.mahasiswapc.myapplication.presenter.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    EditText etNama, etDeskripsi;
    Button btnSubmit, btnLihat;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNama = findViewById(R.id.name);
        etDeskripsi = findViewById(R.id.description);
        btnSubmit = findViewById(R.id.btn_submit);
        btnLihat = findViewById(R.id.btn_lihat);
        mainPresenter = new MainPresenter(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mainPresenter.createItems(etNama.getText().toString(), etDeskripsi.getText().toString());
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), Lihat.class);
                startActivity(x);
            }
        });

    }


    @Override
    public void getSuccess(GetResponse list) {

    }

    @Override
    public void setToast(String message) {
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onFailure(String failureMessage) {

    }
}
