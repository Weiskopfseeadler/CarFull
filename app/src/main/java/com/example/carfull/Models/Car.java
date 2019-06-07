package com.example.carfull.Models;

public class Car {

    private long car_id;
    private String designation;
    private String Name;
    private float fullMileagePerKm;

    public Car() {
    }

    public Car(String designation, String name, float fullMileagePerKm) {
        this.designation = designation;
        Name = name;
        this.fullMileagePerKm = fullMileagePerKm;
    }

    public Car(long car_id, String designation, String name, float fullMileagePerKm) {
        this.car_id = car_id;
        this.designation = designation;
        Name = name;
        this.fullMileagePerKm = fullMileagePerKm;
    }

    public long getId() {
        return car_id;
    }

    public void setId(long car_id) {
        this.car_id = car_id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getFullMileagePerKm() {
        return fullMileagePerKm;
    }

    public void setFullMileagePerKm(float fullMileagePerKm) {
        this.fullMileagePerKm = fullMileagePerKm;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", designation='" + designation + '\'' +
                ", Name='" + Name + '\'' +
                ", fullMileagePerKm=" + fullMileagePerKm +
                '}';
    }
}

