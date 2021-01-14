package com.crossreview.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.crossreview.Activity.MainActivity;
import com.crossreview.CustomView.AddPoints_popup;
import com.crossreview.Model.ClsResultStateResponseModel;
import com.crossreview.Model.GetSelfDetailsModel;
import com.crossreview.R;
import com.crossreview.Utilites.Constant;
import com.crossreview.Utilites.KeyClass;
import com.crossreview.Utilites.PrefrenceShared;
import com.crossreview.ViewModel.GetAvailablePointsViewModel;
import com.crossreview.ViewModel.MakePaymentViewModel;
import com.crossreview.ViewModel.PreviewInfoViewModel;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


public class CheckOutFragment extends Fragment implements View.OnClickListener, Observer<GetSelfDetailsModel> {

    private View mview;
    private Context mctx;
    private TextView txt_avaliable_points, txt_employment_bg_check_tv, txt_employment_bg_check_points_tv, txt_employment_edu_check_tv,
            txt_employment_edu_check_points_tv, txt_employment_criminal_bg_check_tv, txt_employment_criminal_bg_check_points_tv,
            txt_total_paid_points_tv, txt_back_btn;
    private CardView pay_now_btn;
    private AddPoints_popup addPointsPopup = new AddPoints_popup(MainActivity.context, R.style.DialogDim);
    private GetAvailablePointsViewModel getAvailablePointsViewModel;
    private String totalPoints, availPoints;
    private MakePaymentViewModel makePaymentViewModel;
    private int empPoints = 0, eduPoints = 0, criPoints = 0;


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
            mview = inflater.inflate(R.layout.fragment_check_out, container, false);

        }


        return mview;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        viewSetup();

        getAvailablePointsViewModel.getAvailPoints(getActivity());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void bindView() {

        txt_avaliable_points = mview.findViewById(R.id.txt_avaliable_points);
        txt_employment_bg_check_tv = mview.findViewById(R.id.txt_employment_bg_check_tv);
        txt_employment_bg_check_points_tv = mview.findViewById(R.id.txt_employment_bg_check_points_tv);
        txt_employment_edu_check_tv = mview.findViewById(R.id.txt_employment_edu_check_tv);
        txt_employment_edu_check_points_tv = mview.findViewById(R.id.txt_employment_edu_check_points_tv);
        txt_employment_criminal_bg_check_tv = mview.findViewById(R.id.txt_employment_criminal_bg_check_tv);
        txt_employment_criminal_bg_check_points_tv = mview.findViewById(R.id.txt_employment_criminal_bg_check_points_tv);
        txt_total_paid_points_tv = mview.findViewById(R.id.txt_total_paid_points_tv);


        txt_back_btn = mview.findViewById(R.id.txt_back_btn);
        pay_now_btn = mview.findViewById(R.id.pay_now_btn);
    }

    private void viewModelSetup() {

        getAvailablePointsViewModel = new ViewModelProvider(this).get(GetAvailablePointsViewModel.class);
        getAvailablePointsViewModel.getPoints.observe(this, this);

        makePaymentViewModel = new ViewModelProvider(this).get(MakePaymentViewModel.class);
        makePaymentViewModel.makePayment.observe(this, makepaymentObserver);


    }

    private void viewSetup() {

        txt_back_btn.setOnClickListener(this);
        pay_now_btn.setOnClickListener(this);


        String eduChildCount = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EduChildCount);
        String empChildCount = PrefrenceShared.getInstance().getPreferenceData().getValueFromKey(KeyClass.EmpChildCount);

        if (eduChildCount != null && empChildCount != null) {

            if (!eduChildCount.equals("0") && !empChildCount.equals("0")) {
                empPoints = (30 + (10 * (Integer.parseInt(empChildCount))));
                eduPoints = (30 + (10 * (Integer.parseInt(eduChildCount))));
                criPoints = 30;
            }

            if (empChildCount != null) {

                txt_employment_bg_check_points_tv.setText(String.valueOf(empPoints));
            }
            if (eduChildCount != null) {

                txt_employment_edu_check_points_tv.setText(String.valueOf(eduPoints));
            }
            txt_employment_criminal_bg_check_points_tv.setText(String.valueOf(criPoints));

            int total = empPoints + eduPoints + criPoints;

            totalPoints = String.valueOf(total);

            txt_total_paid_points_tv.setText(totalPoints);

        }
//
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.txt_back_btn:

                getActivity().onBackPressed();

                break;

            case R.id.pay_now_btn:

                makePayments();
                break;
        }

    }

    private void openDialog() {

        addPointsPopup.show();
        Window window = addPointsPopup.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }


    public void onBackPressed() {

        ((MainActivity) getActivity()).replaceFragment(new PreviewFragment(), true, KeyClass.FRAGMENT_PREVIEW, KeyClass.FRAGMENT_PREVIEW);
    }

    @Override
    public void onChanged(GetSelfDetailsModel getSelfDetailsModel) {

        if (getSelfDetailsModel != null) {

            availPoints = getSelfDetailsModel.getData().getAmount();
            int amount = Integer.parseInt(availPoints);

            txt_avaliable_points.setText(String.valueOf(amount));
        }
    }

    private final Observer<ClsResultStateResponseModel> makepaymentObserver = new Observer<ClsResultStateResponseModel>() {
        @Override
        public void onChanged(ClsResultStateResponseModel clsResultStateResponseModel) {

            ((MainActivity) getActivity()).replaceFragment(new ThankYouFragment(), false, KeyClass.FRAGMENT_THANK_YOU,
                    KeyClass.FRAGMENT_THANK_YOU);

        }
    };

    public void makePayments() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Constant.Amount, totalPoints);

        JsonObject data = new JsonObject();
        data.add(Constant.data, jsonObject);

        if (availPoints != null && totalPoints != null) {

            int avilP = Integer.parseInt(availPoints);
            int totalP = Integer.parseInt(totalPoints);


            if (totalP <= avilP) {


                makePaymentViewModel.makePayment(data, mctx);

            }
        } else {

            openDialog();
        }
    }

}