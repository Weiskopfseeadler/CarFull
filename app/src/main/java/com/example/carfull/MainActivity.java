package com.example.carfull;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carfull.DBStuff.SQLOpenHelper;
import com.example.carfull.Models.Car;
import com.example.carfull.Models.User;

import static android.Manifest.permission;

public class MainActivity extends AppCompatActivity {

    private AppInformation appInformation;

    public SQLOpenHelper sqlOpenHelper;

    private EditText name;
    private EditText prename;
    private EditText identificationNumber;

    private TextView errormessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        prename = findViewById(R.id.prename);
        identificationNumber = findViewById(R.id.identificationNumber);
        errormessage = findViewById(R.id.Errormessage);



//                        .sendMail(email);//
////        Email email = EmailBuilder.startingBlank()
////                .from("Pädi", "patrick.grunder@zapp.ch")
////                .to("Fritz", "patrick.grunder@csbe.ch")
////                .withSubject("Email is send!")
////                .withPlainText("IT WORK ")
////                .buildEmail();
////
////        MailerBuilder
////                .withSMTPServer("imap.quickline.com", 143, "patrick.grunder@zapp.ch", "Pok3mon")
////                .withTransportStrategy(TransportStrategy.SMTP)
////                .buildMailer()
////                .testConnection();







        if ((PermissionChecker.checkSelfPermission(MainActivity.this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (PermissionChecker.checkSelfPermission(MainActivity.this,
                permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION, permission.INTERNET}, 10);
            }
        }


        appInformation = AppInformation.getInstance();

        appInformation.sqlOpenHelper = new SQLOpenHelper(MainActivity.this);
        sqlOpenHelper = new SQLOpenHelper(MainActivity.this);


        System.out.println(sqlOpenHelper.getExpensesFromuser(1));


        if (appInformation.sqlOpenHelper.getCars().size() <= 0) {
            Car car = new Car("Lambo Ursus 1", "Lamborghini Ursus", 23);
            Car car1 = new Car("Lambo Ursus 2", "Lamborgini Ursus", 22);
            Car car2 = new Car("Lambo Hurican 1", "Lamborgini Hurican", 30);
            Car car3 = new Car("Lambo Hurican 2", "Lamborgini Hurican", 35);

            sqlOpenHelper.addCar(car);
            sqlOpenHelper.addCar(car1);
            sqlOpenHelper.addCar(car2);
            sqlOpenHelper.addCar(car3);
            sqlOpenHelper.addCar(car1);
            sqlOpenHelper.addCar(car1);

            User user = new User("Patrick", "Grunder");

            sqlOpenHelper.addUser(user,4242);
        }



        TextView txt = findViewById(R.id.text);
        if (appInformation.sqlOpenHelper.getCars().size() > 0) {
        }
    }

    public void onStart(View view) {


        appInformation.user = sqlOpenHelper.getUserBylogin(name.getText().toString(), prename.getText().toString(), Integer.parseInt(identificationNumber.getText().toString()));
        if (appInformation.user != null) {
            Intent intent = new Intent(this, BeforeDrive.class);
            startActivity(intent);
        } else {
            errormessage.setVisibility(View.VISIBLE);
        }
    }
}
