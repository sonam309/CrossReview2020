package com.crossreview.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;


public class DocumentFragment extends Fragment {

    private Context mctx;
    private View mview;
    private WebView doc_webview;
    private String picPath="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){

            picPath=getArguments().getString(KeyClass.DOCUMENT_URL);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mview == null) {
            mctx = getActivity();
            // Inflate the layout for this fragment
            mview = inflater.inflate(R.layout.fragment_document, container, false);

        }
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bindView();
        viewSetup();

    }

    private void bindView() {

        doc_webview = mview.findViewById(R.id.doc_webview);


    }

    private void viewSetup() {

        doc_webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                doc_webview.loadUrl(picPath);

                    return true ;
            }
        });

    }
}