package com.crossreview.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.crossreview.R;
import com.crossreview.Utilites.KeyClass;


public class DocumentFragment extends Fragment {

    private Context mctx;
    private View mview;
    private WebView doc_webview;
    private String picPath = "";
    private ProgressDialog progressDialog;
    private boolean pdfLoadFlag=false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            picPath = getArguments().getString(KeyClass.DOCUMENT_URL);

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

        progressDialog = new ProgressDialog(mctx);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);


    }

    private void viewSetup() {
        WebSettings webSetting = doc_webview.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setDisplayZoomControls(false);
        webSetting.setDomStorageEnabled(true);
        doc_webview.getSettings().setJavaScriptEnabled(true);
        doc_webview.setWebViewClient(new MyBrowser());
        doc_webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" +picPath);


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

             pdfLoadFlag=true;
            progressDialog.show();

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (pdfLoadFlag) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();

            }else {
                doc_webview.setWebViewClient(new MyBrowser());
                doc_webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" +picPath);
            }


        }

    }
}