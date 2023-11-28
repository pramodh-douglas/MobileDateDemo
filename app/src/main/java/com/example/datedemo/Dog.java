package com.example.datedemo;

import java.time.LocalDate;

public class Dog {
    private int id;
    private String dogBreed;
    private String dogName;
    private int dogPicDrawable; //this is not the resource name but the drawable int value

    private LocalDate dogDob; //LocalDate, use String if working with Database

    public int getDogPicDrawable() {
        return dogPicDrawable;
    }

    public void setDogPicDrawable(int dogPicDrawable) {
        this.dogPicDrawable = dogPicDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public LocalDate getDogDob() {
        return dogDob;
    }

    public void setDogDob(LocalDate dogDob) {
        this.dogDob = dogDob;
    }

    public Dog(int id, String dogBreed, String dogName, int dogPicDrawable) {
        this.id = id;
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogPicDrawable = dogPicDrawable;
    }

    public Dog(int id, String dogBreed, String dogName, int dogPicDrawable, LocalDate dogDob) {
        this.id = id;
        this.dogBreed = dogBreed;
        this.dogName = dogName;
        this.dogPicDrawable = dogPicDrawable;
        this.dogDob = dogDob;
    }
}
