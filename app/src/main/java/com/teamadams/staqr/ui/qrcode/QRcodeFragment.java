package com.teamadams.staqr.ui.qrcode;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.teamadams.staqr.R;

import static com.teamadams.staqr.Dialog.createDialog;

public class QRcodeFragment extends Fragment {

    private final int QR_SCAN_REQUEST_CODE = 123;
    private boolean code_captured = false;
    private QRCodeReaderView qrCodeReaderView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qrcode, container, false);
        if (!isCameraGranted()) {
            requestCameraPermission();
//            return;
        }
        qrCodeReaderView = view.findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setAutofocusInterval(500);
        qrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String text, PointF[] points) {
                Log.e("QRCODE FROM FRAGMENT", text);
                if (code_captured == false) {
                    code_captured = true;
                    submitQRTicket(text);
                }

//                finish();
            }
        });
        qrCodeReaderView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        qrCodeReaderView.forceAutoFocus();
                    }
                }
        );


        return view;
    }

    public boolean isCameraGranted() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this.getActivity(),
                new String[]{Manifest.permission.CAMERA},
                010);

    }

    private void submitQRTicket(String scanned_code) {
        //TODO: (actually) make the thing
        Toast.makeText(getContext(), "Server-side DB Currently Offline", Toast.LENGTH_LONG).show();
        createDialog(getActivity(), "Request Submitted",
                "Your rapid assistance request has been submitted. A technical support employee will be dispatched soon.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // OK
                        returnToHome();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // Cancel
                        returnToHome();
                    }
                }, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        returnToHome();
                    }
                });
    }

    private void returnToHome() { //goes to home fragment
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.popBackStack();
    }
}