package hcmute.edu.vn.hlong18110314.ui.vouchers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VouchersViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public VouchersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is vouchers fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}