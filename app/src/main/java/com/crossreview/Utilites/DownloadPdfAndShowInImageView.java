package com.crossreview.Utilites;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.crossreview.Activity.MainActivity;
import com.crossreview.BuildConfig;

import java.io.File;
import java.io.IOException;

import static com.crossreview.Utilites.Utility.generateImageFromPdf;
import static com.crossreview.Utilites.Utility.hideLoader;

public class DownloadPdfAndShowInImageView extends AsyncTask<String, Void, Void> {

    Context context;
    String pdfname;
    ImageView imageView;

    public DownloadPdfAndShowInImageView(Context context, String pdfname, ImageView imageView) {
        this.context = context;
        this.pdfname=pdfname;
        this.imageView=imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(String... strings) {

        String fileUrl = strings[0];
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
        String fileName = strings[1];
// ->five-point-someone-chetan-bhagat_ebook.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(context.getExternalFilesDir(null).getAbsolutePath(), "");
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try {
            pdfFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDownloader.downloadFile(fileUrl, pdfFile);
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        hideLoader();
        // Toast.makeText(context, "Pdf downloaded successfully.", Toast.LENGTH_SHORT).show();
        //Util.sharePdf(context,pdfname);
        //callback.onClik();
        File pdf = new File(MainActivity.context.getExternalFilesDir(null).getAbsolutePath(), "" + pdfname);

        Uri imageUri = FileProvider.getUriForFile(
                MainActivity.context,
                BuildConfig.APPLICATION_ID + ".provider", //(use your app signature + ".provider" )
                pdf);

        imageView.setImageBitmap(generateImageFromPdf(imageUri));
    }
}
