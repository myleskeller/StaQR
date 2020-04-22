package com.teamadams.staqr.ui.qrcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.teamadams.staqr.R;

import static com.teamadams.staqr.Dialog.createDialog;


public class QRcodeFragment extends Fragment {

//    TODO: make not-fullscreen
//    TODO: get rid of stupid decorations

    private String toast;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        displayToast();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        scanFromFragment();
    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setOrientationLocked(true);
        IntentIntegrator.forSupportFragment(this).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }

    private void displayToast() {
        if (getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
            }

            // At this point we may or may not have a reference to the activity
            displayToast();
        }
    }

    private void qrCodeCaptureDecision(String code) {
        createDialog(getActivity(), "QR Code Captured",
                "Proceed with captured code " + code + '?', false,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // OK
                        submitQRTicket();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // Cancel
//                        mScannerView.resumeCameraPreview(QRcodeFragment.this);
                    }
                });
    }

    private void submitQRTicket() {
        //TODO: make the thing
    }

    private void returnToHome() { //goes to home fragment
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.popBackStack();
    }
}