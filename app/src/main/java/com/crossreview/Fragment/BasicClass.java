package com.crossreview.Fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.crossreview.Activity.MainActivity;
import com.crossreview.CustomView.ImageUploadDialog;
import com.crossreview.R;
import com.crossreview.Utilites.Utility;

import java.io.File;

public class BasicClass extends Fragment {

    public int START_ACTIVITY_CAMERA_CODE = 101;
    public int START_ACTIVITY_GALLERY_CODE = 102;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 103;
    private ImageUploadDialog imageUploadDialog;
    public int GALLERY_ACCESS_REQUEST = 104;
    public int CAMERA_ACCESS_REQUEST = 105;
    public File photoFile;
    public String path_of_pic = "";
    private boolean photoGalleryDialogFlag = false;


    public void checkPermission(Boolean pdfUpload) {

        String[] PERMISSIONS = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,

        };

        if (!Utility.hasPermissions(MainActivity.context, PERMISSIONS)) {
            requestPermissions(PERMISSIONS, REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
//        if (!photoGalleryDialogFlag) {
//            openCamera();
//        }
        else {
            openCameraGalleryDialog(pdfUpload);
        }
    }


    public void openCameraGalleryDialog(Boolean pdfUpload) {
        if (imageUploadDialog == null || !imageUploadDialog.isShowing()) {
            imageUploadDialog = new ImageUploadDialog(MainActivity.context, R.style.DialogDim);
            imageUploadDialog.show();
            Window window = imageUploadDialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);

            imageUploadDialog.gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uploadintentimage(pdfUpload);
                    imageUploadDialog.dismiss();
                }
            });
            imageUploadDialog.camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCamera();
                    imageUploadDialog.dismiss();
                }
            });
        }
    }


    public void Uploadintentimage(Boolean pdfUpload) {


        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (Utility.hasPermissions(MainActivity.context, PERMISSIONS)) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            String[] mimetypes = new String[0];
            if(!pdfUpload) {
                 mimetypes = new String[]{"image/*"};
            }else {
                mimetypes = new String[]{"image/*","application/pdf"};
            }
            intent.putExtra(Intent.EXTRA_MIME_TYPES,mimetypes );
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            startActivityForResult(intent, START_ACTIVITY_GALLERY_CODE);

        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, GALLERY_ACCESS_REQUEST);
        }
    }


    public void openCamera() {
        String[] PERMISSIONS = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (Utility.hasPermissions(MainActivity.context, PERMISSIONS)) {

            photoFile = null;

            boolean flag = true;
            try {
                photoFile = createImageFile();

            } catch (Exception ex) {
                flag = false;
            }

            if (photoFile != null && flag) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (Build.VERSION.SDK_INT >= 24) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(MainActivity.context, MainActivity.context.getApplicationContext().getPackageName() + ".provider", photoFile));
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                }
                startActivityForResult(intent, START_ACTIVITY_CAMERA_CODE);
            }

        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_ACCESS_REQUEST);

        }
    }


    private File createImageFile() throws Exception {

        String imageFileName = "JPEG_" + "" + "_";

        File storageDir = new File(MainActivity.context.getExternalFilesDir(null).getAbsolutePath(), "images");

        if (!storageDir.exists()) {
            if (!storageDir.mkdirs()) {
                return null;
            }
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        path_of_pic = image.getAbsolutePath();

        return image;
    }


}
