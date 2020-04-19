package com.teamadams.staqr.ui.dialer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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