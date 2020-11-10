package com.crossreview.Fragment.Authentication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crossreview.Activity.MainActivity;
import com.crossreview.Fragment.CheckOutFragment;
import com.crossreview.Model.GetOtpResponseModel;
import com.crossreview.Model.LoginResponseModel;
import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.Utility;
import com.crossreview.ViewModel.GetOtpViewModel;
import com.crossreview.ViewModel.LoginViewModel;


public class LoginFragment extends Fragment implements View.OnClickListener, Observer<GetOtpResponseModel> {

    private View mview;
    private Context mctx;
    private EditText txt_email_et, txt_email_varification_otp_et;
    private TextView countinue_btn, txt_cant_login, txt_signup, txt_edit_email;
    private RelativeLayout txt_edit_email_rl;
    private GetOtpViewModel getOtpViewModel;
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModelSetup();
    }

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

        getOtpViewModel = new ViewModelProvider(this).get(GetOtpViewModel.class);
        getOtpViewModel.getOtp.observe(this, this);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.login.observe(this, loginResponseModelObserver);

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
                    if (txt_email_et.getText().toString().isEmpty()) {

                        Toast.makeText(mctx, "Please enter email id", Toast.LENGTH_SHORT).show();
                        txt_email_et.requestFocus();
                        Utility.showKeyboard(getActivity());
                        return;
                    } else {

                        txt_email_et.clearFocus();
                        Utility.hideKeyboard(view);
                        getOtpViewModel.getOtpfun(txt_email_et.getText().toString(), mctx);
                    }
                } else {

                    String otp = txt_email_varification_otp_et.getText().toString();

                    if (otp.isEmpty() && otp.length() < 7) {

                        Toast.makeText(mctx, "Please enter valid Otp", Toast.LENGTH_SHORT).show();
                        txt_email_varification_otp_et.requestFocus();
                        Utility.showKeyboard(getActivity());
                        return;

                    } else {

                        txt_email_varification_otp_et.clearFocus();
                        Utility.hideKeyboard(view);

                        loginViewModel.login(txt_email_et.getText().toString(), otp);

                    }

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

    @Override
    public void onChanged(GetOtpResponseModel getOtpResponseModel) {

        if (txt_email_et.getVisibility() == View.VISIBLE) {

//                    Toast.makeText(mctx, "send otp", Toast.LENGTH_SHORT).show();

            txt_email_et.setVisibility(View.GONE);
            txt_edit_email_rl.setVisibility(View.VISIBLE);
            txt_email_varification_otp_et.setVisibility(View.VISIBLE);
            txt_edit_email.setText(txt_email_et.getText().toString());
        }
    }

    private final Observer<LoginResponseModel> loginResponseModelObserver = new Observer<LoginResponseModel>() {
        @Override
        public void onChanged(LoginResponseModel loginResponseModel) {


            ((MainActivity) getActivity()).replaceFragment(new CheckOutFragment(), true, KeyClass.FRAGMENT_CHECKOUT,
                    KeyClass.FRAGMENT_CHECKOUT);

        }
    };


}