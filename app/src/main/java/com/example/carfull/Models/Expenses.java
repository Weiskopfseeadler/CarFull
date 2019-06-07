package com.example.carfull.Models;

public class Expenses {

    private long id = 0;
    private long userId = 0;
    private DriveData driveData;
    private double total = 0;
    private double expensesPerUnit = 0;
    private ExpensesCalculationMethods expensesCalculationMethod;
    private boolean isSend = false;
    private boolean isPayd = false;


    public Expenses() {
        this.driveData = new DriveData();
    }

    public Expenses(long id, long userId, DriveData driveDataList, double total, double expensesPerUnit, ExpensesCalculationMethods expensesCalculationMethod, boolean isSend, boolean isPayd) {
        this.id = id;
        this.userId = userId;
        this.driveData = driveDataList;
        this.total = total;
        this.expensesPerUnit = expensesPerUnit;
        this.expensesCalculationMethod = expensesCalculationMethod;
        this.isSend = isSend;
        this.isPayd = isPayd;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public DriveData getDriveData() {
        return driveData;
    }

    public void setDriveData(DriveData driveData) {
        this.driveData = driveData;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getExpensesPerUnit() {
        return expensesPerUnit;
    }

    public void setExpensesPerUnit(double expensesPerUnit) {
        this.expensesPerUnit = expensesPerUnit;
    }

    public  String getExpensesCalculationMethod() {
        return String.valueOf(expensesCalculationMethod);
    }

    public void setExpensesCalculationMethod(ExpensesCalculationMethods expensesCalculationMethod) {
        this.expensesCalculationMethod = expensesCalculationMethod;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public boolean isPayd() {
        return isPayd;
    }

    public void setPayd(boolean payd) {
        isPayd = payd;
    }


    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", userId=" + userId +
                ", driveData=" + driveData +
                ", total=" + total +
                ", expensesPerUnit=" + expensesPerUnit +
                ", expensesCalculationMethod=" + expensesCalculationMethod +
                ", isSend=" + isSend +
                ", isPayd=" + isPayd +
                '}';
    }
}


