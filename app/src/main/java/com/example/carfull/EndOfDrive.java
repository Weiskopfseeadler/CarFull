package com.example.carfull;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carfull.DBStuff.SQLOpenHelper;

import java.time.Duration;
import java.util.Calendar;


public class EndOfDrive extends AppCompatActivity {

    public AppInformation appInformation;

    public EditText editText;

    public TextView controll;
    public SQLOpenHelper sqlOpenHelper = new SQLOpenHelper(EndOfDrive.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_drive);
        appInformation = AppInformation.getInstance();

        editText = findViewById(R.id.editText2);


        appInformation.newExpenses.getDriveData().setArrivingTime(Calendar.getInstance().getTime());
        appInformation.newExpenses.getDriveData().setDuration(Duration.ofMillis(appInformation.newExpenses.getDriveData().getArrivingTime().getTime() - appInformation.newExpenses.getDriveData().getStartTime().getTime()
        ));

        long difference =(appInformation.newExpenses.getDriveData().getArrivingTime().getTime()
                - appInformation.newExpenses.getDriveData().getStartTime().getTime());
        System.out.println(difference);






//        http://www.simplejavamail.org/#/about
//        https://gmail-blog.de/gmail-einstellungen-fur-imap-pop3-und-smtp/

        controll.setText(appInformation.toString());
        System.out.println(appInformation.newExpenses);
        sqlOpenHelper.addExpenses(appInformation.newExpenses);
        System.out.println(sqlOpenHelper.getExpensesFromuser(1));



    }

    public void onStart(View view){

        appInformation.newExpenses.getDriveData().setEndFuel(Float.parseFloat(String.valueOf(editText.getText())));
        Intent intent = new Intent(this, Calculation.class);
        startActivity(intent);

    }

}
