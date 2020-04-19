package com.teamadams.staqr.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.w3c.dom.Text;

public class DialerViewModel extends ViewModel {

    private MutableLiveData<String> phoneText = new MutableLiveData<>();

    // Not sure if needed.
    /*public DialerViewModel(String input) {
        phoneText.setValue(input);
    }*/

    public LiveData<String> getText() {
        return phoneText;
    }
}