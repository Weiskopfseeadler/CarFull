package com.example.carfull;

import com.example.carfull.DBStuff.SQLOpenHelper;
import com.example.carfull.Models.Car;
import com.example.carfull.Models.Expenses;
import com.example.carfull.Models.User;

import java.util.List;


public class AppInformation {

    private static AppInformation instance;

    public SQLOpenHelper sqlOpenHelper;
    public User user ;
    public Expenses newExpenses;
    public List<Car> carList;


    private AppInformation() {

        };

    public static synchronized AppInformation getInstance () {



        if (AppInformation.instance == null) {
            AppInformation.instance = new AppInformation ();
            AppInformation.instance.newExpenses = new Expenses();
            AppInformation.instance.user = new User();



        return AppInformation.instance;
    }
        return AppInformation.instance;
    }

    @Override
    public String toString() {
        return "AppInformation{" +
                " user=" + user.toString() +
                ", newExpenses=" + newExpenses +
                ", carList=" + carList +
                '}';
    }
}

