package com.crossreview.Fragment.Authentication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.CheckOutFragment;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private View mview;
    private Context mctx;
    private EditText txt_email_et, txt_email_varification_otp_et;
    private TextView countinue_btn, txt_cant_login, txt_signup, txt_edit_email;
    private RelativeLayout txt_edit_email_rl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {

            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_login, container, false);
        }
        return mview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindView();
        viewModelSetup();
        viewSetup();
    }

    private void bindView() {

        txt_email_et = mview.findViewById(R.id.txt_email_et);
        countinue_btn = mview.findViewById(R.id.countinue_btn);
        txt_cant_login = mview.findViewById(R.id.txt_cant_login);
        txt_signup = mview.findViewById(R.id.txt_signup);
        txt_edit_email_rl = mview.findViewById(R.id.txt_edit_email_rl);
        txt_email_varification_otp_et = mview.findViewById(R.id.txt_email_varification_otp_et);
        txt_edit_email = mview.findViewById(R.id.txt_edit_email);

    }

    private void viewModelSetup() {

    }

    private void viewSetup() {

        countinue_btn.setOnClickListener(this);
        txt_cant_login.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
        txt_edit_email_rl.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.countinue_btn:

                if (txt_email_et.getVisibility() == View.VISIBLE) {

                    Toast.makeText(mctx, "send otp", Toast.LENGTH_SHORT).show();
                    txt_email_et.setVisibility(View.GONE);
                    txt_edit_email_rl.setVisibility(View.VISIBLE);
                    txt_email_varification_otp_et.setVisibility(View.VISIBLE);
                } else {

                    ((MainActivity)getActivity()).replaceFragment(new CheckOutFragment(),true, KeyClass.FRAGMENT_CHECKOUT,
                            KeyClass.FRAGMENT_CHECKOUT);

                }

                break;

            case R.id.txt_cant_login:

                break;

            case R.id.txt_signup:

                break;

            case R.id.txt_edit_email_rl:

                if (txt_edit_email_rl.getVisibility() == View.VISIBLE) {

                    txt_edit_email_rl.setVisibility(View.GONE);
                    txt_email_varification_otp_et.setVisibility(View.GONE);
                    txt_email_et.setVisibility(View.VISIBLE);
                } else {


                }
                break;
        }

    }
}