package com.teamadams.staqr.ui.qrcode;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QRActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener, View.OnClickListener {

    private final int REQUEST_CODE_CAMERA = 010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isCameraGranted()) {
            requestCameraPermission();
            return;
        }
    }

    @Override
    public void onQRCodeRead(String qrData, PointF[] points) {
        finishWithResult(qrData);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        int IMAGE_PICKER_REQUEST_CODE = 101;
        if (requestCode == IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                String qrData = decodeQRBitmap(bitmap);
                finishWithResult(qrData);
            } catch (FileNotFoundException f) {
                f.printStackTrace();
                finishWIthError("FileNotFoundException");
            } catch (IOException i) {
                i.printStackTrace();
                finishWIthError("IOException");
            }
        }

    }

    private String decodeQRBitmap(Bitmap bitmap) {
        String decoded = null;

        int[] intArray = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),
                bitmap.getHeight());
        LuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(),
                bitmap.getHeight(), intArray);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        com.google.zxing.Reader reader = new QRCodeReader();
        try {
            Result result = reader.decode(binaryBitmap);
            decoded = result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
            finishWIthError("NotFoundException");
        } catch (ChecksumException e) {
            e.printStackTrace();
            finishWIthError("ChecksumException");
        } catch (FormatException e) {
            e.printStackTrace();
            finishWIthError("FormatException");
        }
        return decoded;
    }

    private void finishWithResult(String qrData) {
        setResult(
                Activity.RESULT_OK, new Intent()
                        .putExtra("qrData", qrData)
        );
        finish();
    }

    private void finishWIthError(String error) {
        setResult(
                Activity.RESULT_CANCELED, new Intent()
                        .putExtra("error", error)
        );
        finish();
    }

    @Override
    public void onBackPressed() {
        finishWIthError("BackPressed");
    }

    private boolean isCameraGranted() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CODE_CAMERA);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    recreate();
                } else {
                    finishWIthError("CameraPermissionDenied");
                }
                break;
            }
        }
    }

}