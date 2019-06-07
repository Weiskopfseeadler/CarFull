package com.example.carfull;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.carfull.Models.ExpensesCalculationMethods;

import java.util.Calendar;

public class BeforeDrive extends AppCompatActivity {


    private Spinner CarSpinner;
    private Spinner method;
    private EditText perUnit;
    private EditText fuel;

    private Button startButton;
    private AppInformation appInformation;
    private TextView textView3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_drive);
        appInformation = AppInformation.getInstance();


        CarSpinner = (Spinner) findViewById(R.id.CarSpinner);
        method = (Spinner) findViewById(R.id.Method);
        perUnit = (EditText) findViewById(R.id.doofesFeld);
        fuel = (EditText) findViewById(R.id.Fuel);
        startButton = (Button) findViewById(R.id.StartButton);




        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, appInformation.sqlOpenHelper.getCarsNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        method.setAdapter(new ArrayAdapter<ExpensesCalculationMethods>(this, android.R.layout.simple_spinner_item, ExpensesCalculationMethods.values()));


        CarSpinner.setAdapter(adapter);
    }


    public void onStart(View view){


        appInformation.newExpenses.setUserId(appInformation.user.getId());
        appInformation.newExpenses.getDriveData().setCar(appInformation.sqlOpenHelper.getCareByName(CarSpinner.getSelectedItem().toString()));
        appInformation.newExpenses.setExpensesCalculationMethod((ExpensesCalculationMethods) method.getSelectedItem());
        appInformation.newExpenses.getDriveData().setStartTime(Calendar.getInstance().getTime());
        appInformation.newExpenses.setExpensesPerUnit(Double.parseDouble(perUnit.getText().toString()));
        appInformation.newExpenses.getDriveData().setStartFuel(Float.parseFloat(perUnit.getText().toString()));



        textView3.setText(appInformation.toString());
        System.out.println(appInformation.toString());

        Intent intent = new Intent(this, DriveActivity.class);
        startActivity(intent);

    }



}
