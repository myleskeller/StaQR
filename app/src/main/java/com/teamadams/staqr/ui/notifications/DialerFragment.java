package com.teamadams.staqr.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamadams.staqr.R;

public class DialerFragment extends Fragment {

    private DialerViewModel dialerViewModel;
    private EditText enterPhone;
    private Button dialerButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // All Initializations
        View dialerView = inflater.inflate(R.layout.fragment_dialer, container, false);
        enterPhone = dialerView.findViewById(R.id.dialerEditText);
        dialerButton = dialerView.findViewById(R.id.dialerBtn);

        dialerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneString = enterPhone.getText().toString().trim(); // Get the phone string

                // Dialer Intent
                Intent dialerIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phoneString)));
                startActivity(dialerIntent);
            }
        });
        return dialerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dialerViewModel = ViewModelProviders.of(this).get(DialerViewModel.class);
        dialerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                enterPhone.setText(s);
            }
        });
    }
}
