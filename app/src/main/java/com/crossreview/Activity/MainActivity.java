package com.crossreview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.crossreview.Fragment.Welcome.WelcomeFragment;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;

public class MainActivity extends AppCompatActivity {

    public static FrameLayout mcontainer;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        bindView();
        viewSetup();
    }

    private void bindView() {

        mcontainer = findViewById(R.id.mcontainer);

    }

    private void viewSetup() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        replaceFragment(new WelcomeFragment(),false, KeyClass.FRAGMENT_WELCOME,KeyClass.FRAGMENT_WELCOME);


    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, String transactionName, String tag) {
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(MainActivity.mcontainer.getId(), fragment, tag);
            if (addToBackStack)
                fragmentTransaction.addToBackStack(transactionName);
            fragmentTransaction.commitAllowingStateLoss();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}