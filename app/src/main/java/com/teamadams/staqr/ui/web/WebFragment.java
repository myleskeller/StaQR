package com.teamadams.staqr.ui.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.teamadams.staqr.R;

public class WebFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View webViewRoot = inflater.inflate(R.layout.fragment_web, container, false);
        WebView webView = webViewRoot.findViewById(R.id.mainWebView);
        //this stupid function took like 5 hours to decipher.
        webView.setWebViewClient(new WebViewClient() {
            String function = "$(\'#content > div.mainContent.u-flex--large.u-wrapper.u-clearfix > aside > nav > p\').trigger(\'expand\');\n";

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:(function() { " + function + "})()");
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(getString(R.string.usf_it_documentation_url));

        return webViewRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
