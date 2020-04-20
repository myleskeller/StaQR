package com.teamadams.staqr.ui.dialer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.teamadams.staqr.R;

import java.time.LocalDate;
import java.time.LocalTime;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.teamadams.staqr.Dialog.createDialog;

public class DialerFragment extends Fragment {

    private WebView support;

    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // All Initializations
        View dialerView = inflater.inflate(R.layout.fragment_dialer, container, false);
        support = dialerView.findViewById(R.id.webView);
        support.setVisibility(INVISIBLE);
        WebSettings webSettings = support.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return dialerView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pickSupport();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void pickSupport() { //dialog popup for choosing support method
        if (isSupportOnline())
            createDialog(this.getActivity(), "Choose Support",
                    "Please select a method of assistance.", "Phone Call", "Live Chat",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { // OK
                            dialServiceDesk();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { // Cancel
                            openLiveSupport();
                        }
                    }, new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            returnToHome();
                        }
                    });
        else
            createDialog(getActivity(), "Phone Support Only",
                    "Live Chat assistance is currently unavailable. Press OK to initiate a phone call with the USF IT Service Desk.",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { // OK
                            dialServiceDesk();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isSupportOnline() { //checks if help desk is open now
        if (LocalDate.now().getDayOfWeek().equals(LocalDate.parse("2020-04-18").getDayOfWeek()) ||
                LocalDate.now().getDayOfWeek().equals(LocalDate.parse("2020-04-19").getDayOfWeek()))
            //i couldn't figure out a more elegant way to obtain a "day of week" object of sat/sun

            return false;
        if (LocalTime.now().isBefore(LocalTime.parse("07:00")) || LocalTime.now().isAfter(LocalTime.parse("18:00")))
            return false;
        else
            return true;
    }

    private void dialServiceDesk() { //passes dialer intent
        Intent dialerIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(getString(R.string.service_desk_tel))));
        startActivity(dialerIntent);
    }

    private void openLiveSupport() { //loads live support view into webview
        support.loadUrl(getString(R.string.helpdesk_chat_url));
        support.setVisibility(VISIBLE);
    }

    private void returnToHome() { //goes to home fragment
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.popBackStack();
    }
}
