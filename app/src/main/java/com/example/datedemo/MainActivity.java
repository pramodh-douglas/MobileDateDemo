package com.example.datedemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.datedemo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Dog> DogList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReadDogData();
        Log.d("DATEDEMO", DogList.size() + " Dog items in the list");

        //things to do in class
        //Create DogAdapter object
        //DogAdapter dogAdapter = new DogAdapter(DogList);

        DogAdapter dogAdapter = new DogAdapter(DogList, new DogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                binding.txtViewAdoptionSumary.setText("Thank you for taking " + DogList.get(i).getDogName());
            }
        });

        //SetAdapter for recycler view
        binding.recyclerViewDogItems.setAdapter(dogAdapter);

        //set layout manager for recyclerview
        binding.recyclerViewDogItems.setLayoutManager((new LinearLayoutManager(this)));

        //In the adapter, we need to set up click listener interface, and call the
        //method
        //In the main activity, we will then implement this method
        //to update txtViewAdoptionSummary

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ReadDogData(){
        DogList = new ArrayList<>();
        InputStream inputStream
                = getResources().openRawResource(R.raw.doginfo);

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            //no header line, if header line is present read first line using if
            while((csvLine = reader.readLine()) != null){
                String[] eachDogFields = csvLine.split(",");
                int dogId = Integer.parseInt(eachDogFields[0]);
                int dogDrawable = getResources()
                                    .getIdentifier
                                    (eachDogFields[1],"drawable",getPackageName());
                String dogBreed = eachDogFields[2];
                String dogName = eachDogFields[3];
                String dogDobStr = eachDogFields[4];

                //Parse dogDobStr into LocalDate object using format pattern
                //d - date fields, one or more digits (e.g., 8, 18, 31)
                //MMM - three letter code for the month (e.g., JAN, MAY, JUN)
                //yyyy - 4 digit year (e.g., 2023, 2020, 1768)

                //dd - two digit dates (e.g., 01, 08, 31, 18)
                //MM - two digit month number (e.g., 01, 11, 10, 12)
                //yy - two digit year (e.g., 20, 23, 68)

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dob = LocalDate.parse(dogDobStr, formatter);

                Dog eachDog = new Dog(dogId,dogName,dogBreed,dogDrawable,dob);
                DogList.add(eachDog);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}