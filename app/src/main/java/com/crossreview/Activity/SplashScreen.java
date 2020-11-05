package com.crossreview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.crossreview.Fragment.AppInfoSwipeFragment;
import com.crossreview.Fragment.SwipeFragment;
import com.crossreview.Fragment.Welcome.SplashFragment;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;

public class SplashScreen extends AppCompatActivity {

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        bindView();
    }

    private void bindView() {


        container=findViewById(R.id.container);

        replaceFragment(new SplashFragment(),false, KeyClass.FRAGMENT_SPLASH,KeyClass.FRAGMENT_SPLASH);


        new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

//               Intent intent= new Intent(SplashScreen.this, MainActivity.class);
//               startActivity(intent);
//               finish();

               replaceFragment(new AppInfoSwipeFragment(),false, KeyClass.FRAGMENT_APP_INFO_SWIPE,KeyClass.FRAGMENT_APP_INFO_SWIPE);

           }
       },2000);


    }


    public void replaceFragment(Fragment fragment, boolean addToBackStack, String transactionName, String tag) {
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(container.getId(), fragment, tag);
            if (addToBackStack)
                fragmentTransaction.addToBackStack(transactionName);
            fragmentTransaction.commitAllowingStateLoss();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}