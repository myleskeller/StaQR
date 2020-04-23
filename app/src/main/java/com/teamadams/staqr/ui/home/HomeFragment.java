package com.teamadams.staqr.ui.home;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.teamadams.staqr.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.description);
        SpannableStringBuilder docs_colored = new SpannableStringBuilder(
                "• Use the Documentation section to review articles posted by the campus IT department for common issues.");
        docs_colored.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                10, // start
                23, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        TextView textView2 = root.findViewById(R.id.docs);
        textView2.setText(docs_colored);

        SpannableStringBuilder support_chat_colored = new SpannableStringBuilder(
                "• Use the Live Chat support feature to instantly chat with the campus IT department.");
        support_chat_colored.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                10, // start
                19, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        TextView textView3 = root.findViewById(R.id.support_chat);
        textView3.setText(support_chat_colored);

        SpannableStringBuilder support_call_colored = new SpannableStringBuilder(
                "• Use the Help Desk support feature to place a phone call to the campus IT department.");
        support_call_colored.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                10, // start
                19, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        TextView textView4 = root.findViewById(R.id.support_call);
        textView4.setText(support_call_colored);

        SpannableStringBuilder qr_colored = new SpannableStringBuilder(
                "• Use the QR Scanner to scan the barcode affixed to troublesome equipment on your campus.");
        qr_colored.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),
                8, // start
                19, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        TextView textView5 = root.findViewById(R.id.qr);
        textView5.setText(qr_colored);

        return root;
    }
}
