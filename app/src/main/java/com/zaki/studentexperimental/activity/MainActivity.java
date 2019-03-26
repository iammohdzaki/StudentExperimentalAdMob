package com.zaki.studentexperimental.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.zaki.studentexperimental.R;
import com.zaki.studentexperimental.adapter.StudentAdapter;
import com.zaki.studentexperimental.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    public static int RANDOM_COUNT=4;
    private static final String BANNER_AD_ID="ca-app-pub-3940256099942544/6300978111";
    private ArrayList<Object> recyclerList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       // generateRandom();
        addStudent();
        getBannerAds();
        loadBannerAds();

        studentAdapter=new StudentAdapter(recyclerList,this);
        recyclerView.setAdapter(studentAdapter);
    }


    void addStudent(){

        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));
        recyclerList.add(new Student("Zaki",21));

    }

    private void generateRandom(){
        Random rn=new Random();
        RANDOM_COUNT=rn.nextInt(recyclerList.size());
    }
    private void getBannerAds(){

        for (int i=0;i<recyclerList.size();i+=RANDOM_COUNT){
            final AdView adView=new AdView(MainActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(BANNER_AD_ID);
            recyclerList.add(i,adView);
        }

    }

    private void loadBannerAds(){
        for(int i=0;i<recyclerList.size();i++){

            Object item = recyclerList.get(i);
            if(item instanceof AdView){
                final AdView adView=(AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }
}
