package com.example.carfull.Models;


import java.util.Date;

import java.time.Duration;


public class DriveData {

    private long id;
    private float kmDrived = 0;
    private float startFuel = 0;
    private float endFuel = 0;
    private Date startTime = new Date();
    private Date arrivingTime= new Date();
    private Duration duration;
    private Car car;



    public DriveData() {

        this.startTime = new Date();
        this.arrivingTime = new Date();
    }

    public DriveData(long id, float kmDrived, float startFuel, float endFuel, Date startTime, Date arrivingTime, Duration duration, Car car) {
        this.id = id;
        this.kmDrived = kmDrived;
        this.startFuel = startFuel;
        this.endFuel = endFuel;
        this.startTime = startTime;
        this.arrivingTime = arrivingTime;
        this.duration = duration;
        this.car = car;
    }

    public DriveData(float kmDrived, float startFuel, float endFuel, Date startTime, Date arrivingTime, Duration duration, Car car) {
        this.kmDrived = kmDrived;
        this.startFuel = startFuel;
        this.endFuel = endFuel;
        this.startTime = startTime;
        this.arrivingTime = arrivingTime;
        this.duration = duration;
        this.car = car;
    }

    public float getKmDrived() {
        return kmDrived;
    }

    public void setKmDrived(float kmDrived) {
        this.kmDrived += kmDrived;
    }

    public float getStartFuel() {
        return startFuel;
    }

    public void setStartFuel(float startFuel) {
        this.startFuel = startFuel;
    }

    public float getEndFuel() {
        return endFuel;
    }

    public void setEndFuel(float endFuel) {
        this.endFuel = endFuel;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Date Date) {
        this.arrivingTime = arrivingTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        System.out.println(car.toString());
        this.car = car;
    }

    @Override
    public String toString() {
        return "DriveData{" +
                ", kmDrived=" + kmDrived +
                ", startFuel=" + startFuel +
                ", endFuel=" + endFuel +
                ", startTime=" + startTime +
                ", arrivingTime=" + arrivingTime +
                ", duration=" + duration +
                ", car=" + car.toString() +
                '}';
    }
}
