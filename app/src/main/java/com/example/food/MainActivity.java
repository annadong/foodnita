package com.example.food;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

        transaction.replace(R.id.fragmentContainer, imgFrag, "IMG_FRAGMENT");
        transaction.commit();

    }
    @Override
    public void onClick(View v) {
        if (v == showMap) {
//            MyFragment myFragment = (MyFragment)getFragmentManager().findFragmentByTag("MY_FRAGMENT");
//            if (myFragment != null && myFragment.isVisible()) {
//                // add your code here
//            }
            GMapFragment mapFrag = (GMapFragment) getSupportFragmentManager().findFragmentByTag("MAP_FRAGMENT");
            if (mapFrag == null || !mapFrag.isVisible()) {
                Bundle bundle = new Bundle();
                bundle.putString("title", restaurants[count].getName());
                bundle.putDouble("x_coord", restaurants[count].getXCoor());
                bundle.putDouble("y_coord", restaurants[count].getYCoor());
                mapFrag = new GMapFragment();
                mapFrag.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, mapFrag, "MAP_FRAGMENT");
                transaction.commit();
            } else {
                return;
            }
        } else if (v == showMeNext) {
            if (count + 1 < restaurants.length) {
                count += 1;
            } else {
                Toast.makeText(MainActivity.this, "no more restaurants", Toast.LENGTH_LONG).show();
                return;
            }

            RestaurantImageFragment imgFrag = (RestaurantImageFragment) getSupportFragmentManager().findFragmentByTag("IMG_FRAGMENT");
            if (imgFrag != null && imgFrag.isVisible()) {
                    imgFrag.showNextImg(restaurants[count].getImage());
            }
            else {
                Bundle bundle = new Bundle();
                bundle.putInt("imagePath", restaurants[count].getImage());
                imgFrag = new RestaurantImageFragment();
                imgFrag.setArguments(bundle);
            }
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, imgFrag, "IMG_FRAGMENT");
            transaction.commit();
        }
    }
}