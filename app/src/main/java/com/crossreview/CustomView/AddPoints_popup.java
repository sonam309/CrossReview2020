package com.crossreview.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crossreview.R;

public class AddPoints_popup extends Dialog {

    public TextView txt_user_name_tv,txt_url_tv;


    public AddPoints_popup(@NonNull Context context) {
        super(context);
    }

    public AddPoints_popup(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AddPoints_popup(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_points_poppup_layout);

        txt_user_name_tv=findViewById(R.id.txt_user_name_tv);
        txt_url_tv=findViewById(R.id.txt_url_tv);

    }



}
