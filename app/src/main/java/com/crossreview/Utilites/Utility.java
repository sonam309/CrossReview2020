package com.crossreview.Utilites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.Constants;
import com.bumptech.glide.Glide;
import com.crossreview.Activity.MainActivity;
import com.crossreview.BuildConfig;
import com.crossreview.Interface.awsUploadCallback;
import com.crossreview.Model.CompanyNameModel;
import com.crossreview.R;
import com.google.gson.JsonObject;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Utility {
    private static final String TAG = Utility.class.getSimpleName();
    private static Handler handler = new Handler();
    private static final long DELAY = 1500; // milliseconds
    public static ProgressDialog loading_dialog;
    private static TransferUtility transferUtility;
    private Context context;
    private static TransferUtility sTransferUtility;
    private static AmazonS3Client sS3Client;
    private static AWSCredentialsProvider sMobileClient;






    //hide Keyboard
    public static void hideKeyboard(Activity activity) {
        if (activity == null)
            return;
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null && activity.getCurrentFocus() != null)
                inputManager.hideSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Utility.log("KeyBoardUtil >>> " + e);
        }
    }

    public static void hideKeyboard(View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    //log with value
    public static void log(String value) {
        if (BuildConfig.DEBUG) {
//            log(TAG, value);
            // logFile(value);
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setError(TextView textView, EditText editText,Context context){

        textView.setTextColor(context.getResources().getColor(R.color.error_red));
        editText.setBackground(context.getResources().getDrawable(R.drawable.error_bg));
    }

    public static void removeError(TextView textView,EditText editText,Context context){

        textView.setTextColor(context.getResources().getColor(R.color.black));
        editText.setBackground(context.getResources().getDrawable(R.drawable.square_white_bg));
    }


    public static String convertHeightToInches(Number height) {
        try {
            String[] some_array = MainActivity.context.getResources().getStringArray(R.array.height_arr);
            for (String val : some_array) {
                if (val.startsWith(height.toString())) {
                    return val.substring(val.indexOf(" ") + 1);
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }


    public static String convertWeightToLbs(Number weight) {
        try {
            String[] some_array = MainActivity.context.getResources().getStringArray(R.array.weight_arr);
            for (String val : some_array) {
                String val1 = val.substring(0, val.indexOf("kg"));
                if (Double.parseDouble(val1) == weight.doubleValue()) {
                    return val.substring(val.indexOf(" ") + 1);
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }


    public static boolean isValidMail(String email) {

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(EMAIL_STRING).matcher(email).matches();

    }

    public static boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }


    public static String getStringFromDate(@NonNull Date date, @NonNull String formatString) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat(formatString, Locale.US);
        return format.format(date);
    }


    public static void uploadImageAwsToServer(String pathOfPic, awsUploadCallback callback) {
        transferUtility = getTransferUtility(MainActivity.context);
        String filePath = pathOfPic;
        showLoader();
        if (filePath == null || filePath.equals("")) {
//            Util.showSnackbar(MainActivity.context,
//                    MainActivity.mFragmentContainer, getString(R.string.pathNotFound));
            Toast.makeText(MainActivity.context, " Hum... that image didn't load. Please try again.", Toast.LENGTH_LONG).show();
            hideLoader();
            return;
        }

        File file = new File(filePath);
        final String key = getRandomNumberString() + "_" + file.getName();
        TransferObserver observer = transferUtility.upload(
                key,
                file
        );
        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (state.equals(TransferState.COMPLETED)) {
                    hideLoader();
                    callback.afterAwsUpload((getS3Client(MainActivity.context).getUrl("crox-files",key)).toString());
                } else if (state.equals(TransferState.FAILED)) {
                    hideLoader();
//                    Util.showSnackbar(MainActivity.context,
//                            MainActivity.mFragmentContainer, getString(R.string.failedImageLoad));
                    Toast.makeText(MainActivity.context, "Hum... that image didn't load. Please try again.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
            }

            @Override
            public void onError(int id, Exception ex) {
                hideLoader();
            }
        });
    }


    public static void showLoader() {
        if (loading_dialog != null) {
            loading_dialog.dismiss();
            loading_dialog = null;
        }
        if (loading_dialog == null) {
            loading_dialog = new ProgressDialog(MainActivity.context);
            loading_dialog.show();
            loading_dialog.setCancelable(false);
        }
    }


    public static void hideLoader() {
        if (loading_dialog != null) {
            if (loading_dialog.isShowing())
                loading_dialog.dismiss();
        }

    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public static void uploadAndSetProfileImage(String pathOfPic, ImageView imageView, awsUploadCallback callback) {

        if (pathOfPic != null) {
            uploadImageAwsToServer(pathOfPic, callback);
            File imgFile = new File(pathOfPic);

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
            }
        }
    }

    public static void showKeyboard(Activity context) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }


//    public static void uploadImageAwsToServer(String pathOfPic, Context context, awsUploadCallback callback) {
//        if (isNetworkAvailable(context, false)) {
//
//            if (pathOfPic == null || pathOfPic.equals("")) {
//
//                Toast.makeText(context, "Hum... that image didn\\'t load. Please try again.", Toast.LENGTH_SHORT).show();
//
//
//                return;
//            }
//
//            File file = new File(pathOfPic);
//            final String key = getRandomNumberString() + "_" + file.getName();
//            TransferObserver observer = transferUtility.upload(
//                    key,
//                    file
//            );
//            observer.setTransferListener(new TransferListener() {
//                @Override
//                public void onStateChanged(int id, TransferState state) {
//                    if (state.equals(TransferState.COMPLETED)) {
//
//
//                        JsonObject object = new JsonObject();
//                        JsonObject data = new JsonObject();
//
//                        object.addProperty(Constant.Profile_Picture, key);
//
//                        data.add("data", object);
//                        //uploading image file name to our server
//                        //    Util.upDateDetails(data, MainActivity.context);
//                        callback.afterAwsUpload(key);
//
////                    Toast.makeText(MainActivity.context, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
//                    } else if (state.equals(TransferState.FAILED)) {
//
//                        Toast.makeText(context, "Hum... that image didn\\'t load. Please try again.", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                    //
//                }
//
//                @Override
//                public void onError(int id, Exception ex) {
//
//                }
//            });
//        } else {
//            Toast.makeText(context, "Please check your connection and try again.", Toast.LENGTH_LONG).show();
//        }
//    }


    public static boolean isNetworkAvailable(final Context context, boolean showToast) {
        if (context == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        if (showToast) {
            new Handler(context.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Please check your connection and try again.", Toast.LENGTH_LONG).show();
                }
            });
        }
        return false;
    }


    public static String getPathOfSelectedImage(Uri data) {
        Uri selectedImage = data;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = MainActivity.context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            return cursor.getString(columnIndex);
        }
        return null;
    }

    public static TransferUtility getTransferUtility(Context context) {
        if (sTransferUtility == null) {
            sTransferUtility = TransferUtility.builder()
                    .context(context)
                    .s3Client(getS3Client(context))
                    .awsConfiguration(new AWSConfiguration(context))
                    .build();
        }

        return sTransferUtility;
    }

    public static AmazonS3Client getS3Client(Context context) {
        ClientConfiguration cc = new ClientConfiguration();
        cc.setSocketTimeout(30000);
        if (sS3Client == null) {
            sS3Client = new AmazonS3Client(getCredProvider(context),cc);
        }
        return sS3Client;
    }

    private static AWSCredentialsProvider getCredProvider(Context context) {
        if (sMobileClient == null) {
            final CountDownLatch latch = new CountDownLatch(1);
            AWSMobileClient.getInstance().initialize(context, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails result) {
                    latch.countDown();
                }

                @Override
                public void onError(Exception e) {
                    Log.e(TAG, "onError: ", e);
                    latch.countDown();
                }
            });
            try {
                latch.await();
                sMobileClient = AWSMobileClient.getInstance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sMobileClient;
    }

    public static Bitmap generateImageFromPdf(Uri pdfUri) {
        int pageNumber = 0;
        PdfiumCore pdfiumCore = new PdfiumCore(MainActivity.context);
        try {
            //http://www.programcreek.com/java-api-examples/index.php?api=android.os.ParcelFileDescriptor
            ParcelFileDescriptor fd = MainActivity.context.getContentResolver().openFileDescriptor(pdfUri, "r");
            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);
            pdfiumCore.openPage(pdfDocument, pageNumber);
            int width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNumber);
            int height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNumber);
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            pdfiumCore.renderPageBitmap(pdfDocument, bmp, pageNumber, 0, 0, width, height);

            //saveImage(bmp);
            pdfiumCore.closeDocument(pdfDocument); // important!
            return bmp;
        } catch(Exception e) {
            //todo with exception

            Log.e("ttttttt",e+"");
        }
        return null;
    }


//    public static void loadImage(ImageView imageView, Context context, String profilePic) {
//        Glide.with(context).load(KeyClass.BASE_URL.substring(0,KeyClass.BASE_URL.length()-1)+profilePic).into(imageView);
//    }




}
