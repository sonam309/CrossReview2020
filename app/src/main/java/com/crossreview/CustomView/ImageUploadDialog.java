package com.crossreview.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crossreview.R;

public class ImageUploadDialog extends Dialog {

    public TextView gallery,camera;

    public ImageUploadDialog(@NonNull Context context) {
        super(context);
    }

    public ImageUploadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ImageUploadDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.phto_gallery_dialog);

        camera=findViewById(R.id.camera);
        gallery=findViewById(R.id.phone);
    }
}
