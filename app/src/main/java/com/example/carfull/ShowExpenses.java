package com.example.carfull;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carfull.Models.Expenses;

public class ShowExpenses extends Fragment {

    private ShowExpensesViewModel mViewModel;

    public static ShowExpenses newInstance() {
        return new ShowExpenses();
    }

    private TextView Distanz;
    private TextView duration;
    private TextView method;
    private TextView perUnit;
    private TextView car;
    private TextView Total;
    private TextView fuel;


    private AppInformation appInformation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show_expenses_fragment, container, false);

        Distanz = (TextView) view.findViewById(R.id.Distanz);
        duration = (TextView) view.findViewById(R.id.duration);
        method = (TextView) view.findViewById(R.id.method);
        perUnit = (TextView) view.findViewById(R.id.doofesFeld);
        car = (TextView) view.findViewById(R.id.car);
        Total = (TextView) view.findViewById(R.id.Total);
        fuel = (TextView) view.findViewById(R.id.fuel);
        appInformation = AppInformation.getInstance();


        return inflater.inflate(R.layout.show_expenses_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ShowExpensesViewModel.class);
        // TODO: Use the ViewModel
    }


    public void setExpens(Expenses expens) {
        Distanz.setText(expens.getDriveData().getDuration().toString());
        duration.setText(expens.getDriveData().getDuration().toString());
        method.setText(expens.getExpensesCalculationMethod().toString());
        perUnit.setText((int) expens.getExpensesPerUnit());
        car.setText(expens.getDriveData().getCar().getDesignation());
        Total.setText((int) calculat(expens));
        fuel.setText((int) fuelUsed(expens));

    }

    public double fuelUsed(Expenses expenses) {
        double total = 0;
        switch (expenses.getExpensesCalculationMethod()) {
            case "Distanc_in_km":

                break;
            case "realFuel":
                total = (expenses.getDriveData().getStartFuel() - expenses.getDriveData().getEndFuel());
                expenses.setTotal(total);
                return total;
            case "expectet_Fuel":
                total = (expenses.getDriveData().getKmDrived() * expenses.getDriveData().getCar().getFullMileagePerKm());
                expenses.setTotal(total);
                return total;
            case "Time":

                ;
                break;

        }
        return total;
    }


    public double calculat(Expenses expenses) {
        double total = 0;
        switch (expenses.getExpensesCalculationMethod()) {
            case "Distanc_in_km":
                total = expenses.getDriveData().getKmDrived() * expenses.getExpensesPerUnit();
                expenses.setTotal(total);
                return total;
            case "realFuel":
                total = (expenses.getDriveData().getStartFuel() - expenses.getDriveData().getEndFuel()) * expenses.getExpensesPerUnit();
                expenses.setTotal(total);
                return total;
            case "expectet_Fuel":
                total = (expenses.getDriveData().getKmDrived() * expenses.getDriveData().getCar().getFullMileagePerKm()) * expenses.getExpensesPerUnit();
                expenses.setTotal(total);
                return total;
            case "Time":
                total = Double.parseDouble(expenses.getDriveData().getDuration().toString()) * expenses.getExpensesPerUnit();
                expenses.setTotal(total);
                return total;

        }
        return total;

    }




}
