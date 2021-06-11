package hcmute.edu.vn.hlong18110314.ui.userDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserInfoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public UserInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is service fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}