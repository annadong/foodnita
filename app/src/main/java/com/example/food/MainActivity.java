package com.example.food;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.MapFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  //  ViewFlipper viewFlipper;
    Button showMap;
    Button showMeNext;

    private Restaurant[] restaurants = {
            new Restaurant("name1", 12.31, 12.31, R.drawable.chipotle),
            new Restaurant("name2", 13.31, 12.31, R.drawable.ramen),
            new Restaurant("name3", 14.31, 12.31, R.drawable.saldking)
    };

    private int count = 0;
//    public class MainActivity extends FragmentActivity{
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.main);
//            OnClickListener listener = new OnClickListener() {
//                public void onClick(View view) {
//                    Fragment fragment = null;
//                    if(view == findViewById(R.id.button1)){
//                        fragment = new FragmentOne();
//                    } else {
//                        fragment = new FragmentTwo();
//                    }
//                    FragmentManager manager = getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.replace(R.id.output, fragment);
//                    transaction.commit();
//                }
//            };
//            Button btn1 = (Button)findViewById(R.id.button1);
//            btn1.setOnClickListener(listener);
//            Button btn2 = (Button)findViewById(R.id.button2);
//            btn2.setOnClickListener(listener);
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        showMap = (Button) findViewById(R.id.next);
        showMeNext = (Button) findViewById(R.id.previous);

        showMap.setOnClickListener(this);
        showMeNext.setOnClickListener(this);


        Bundle bundle = new Bundle();
        bundle.putInt("imagePath", restaurants[0].getImage());
        Fragment imgFrag = new RestaurantImageFragment();
        imgFrag.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragmentContainer, imgFrag);
        transaction.commit();

    }
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (v == showMap) {
//            MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById("MAP_FRAGMENT");
//            if (mapFrag == null || !mapFrag.isVisible()) {
//
//            }

        } else if (v == showMeNext) {
        }
    }
}