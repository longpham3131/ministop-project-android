package hcmute.edu.vn.hlong18110314.ui.userDetail;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.hlong18110314.LoginActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;

public class UserInfoFragment extends Fragment {

    public UserInfoViewModel mViewModel;
    public TextView txtEmailUpdate;
    public EditText txtPasswordUpdate;
    public EditText txtDisplayNameUpdate;
    public Button btnUpdate;
    public Button btnSignOut;
    public  RecyclerView rvListOrder;
    public Database database;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }
    public LayoutInflater inflaterTempt;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {

        inflaterTempt = inflater;

        mViewModel =
                new ViewModelProvider(this).get(UserInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userinfo, container, false);
        database = new Database(getContext(), Database.DATABASE_NAME, null);

        txtEmailUpdate = (TextView) root.findViewById(R.id.txtEmailUpdate);
        txtPasswordUpdate = (EditText) root.findViewById(R.id.txtPasswordUpdate);
        txtDisplayNameUpdate = (EditText) root.findViewById(R.id.txtDisplayNameUpdate);
        //Set text
        txtEmailUpdate.setText(UserModel.CURRENT_USER.getEmail());
        txtPasswordUpdate.setText(UserModel.CURRENT_USER.getPassword());
        txtDisplayNameUpdate.setText(UserModel.CURRENT_USER.getFullName());

        btnUpdate = (Button) root.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener((view) -> {
            UserModel.CURRENT_USER.setFullName(txtDisplayNameUpdate.getText().toString());
            UserModel.CURRENT_USER.setPassword(txtPasswordUpdate.getText().toString());
            database.updateUser(UserModel.CURRENT_USER);
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT);
        });
        btnSignOut = (Button) root.findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener((view) -> {
            Intent intent = new Intent(inflater.getContext(), LoginActivity.class);
            UserModel.CURRENT_USER = null;
            startActivity(intent);
        });
        rvListOrder = (RecyclerView) root.findViewById(R.id.rvListOrder);
        List<OrderModel> listOrder = database.getAllOrdersByUserId(UserModel.CURRENT_USER.getId());
        Log.e("LIST ORDER", String.valueOf(listOrder.size()));
        UserInfoAdapter adapter = new UserInfoAdapter(listOrder);
        rvListOrder.setAdapter(adapter);
        rvListOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        int curSize = adapter.getItemCount();
        return root;
    }
//    public static void IntentToActivity( int order_id){
//        Intent intent = new Intent( )
//    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserInfoViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onResume() {
        super.onResume();
        if(UserModel.CURRENT_USER == null){
            Intent intent = new Intent(inflaterTempt.getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

}