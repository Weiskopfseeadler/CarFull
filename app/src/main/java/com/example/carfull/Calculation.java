package com.example.carfull;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Calculation extends AppCompatActivity {



    private AppInformation appInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        appInformation = AppInformation.getInstance();

        ShowExpenses showExpenses  = (ShowExpenses) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        showExpenses.setExpens(appInformation.newExpenses);
    }

    public void onStart(View view){


        appInformation.sqlOpenHelper.addExpenses(appInformation.newExpenses);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}
