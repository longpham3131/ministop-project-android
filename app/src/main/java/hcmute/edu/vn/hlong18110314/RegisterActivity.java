package hcmute.edu.vn.hlong18110314;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.hlong18110314.database.Database;

public class RegisterActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    public Button btnRegister;
    public EditText txtEmailRes;
    public EditText txtPasswordRes;
    public EditText txtFullNameRes;
    public Button btnBackToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Database dtb = new Database(getApplicationContext(), Database.DATABASE_NAME, null);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnBackToLogin = (Button) findViewById(R.id.btnBackLogin);
        txtEmailRes = (EditText) findViewById(R.id.txtEmailRes);
        txtPasswordRes = (EditText) findViewById(R.id.txtPasswordRes);
        txtFullNameRes = (EditText) findViewById(R.id.txtFullNameRes);

        btnRegister.setOnClickListener((view) -> {
            String email = txtEmailRes.getText().toString();
            String pass = txtPasswordRes.getText().toString();
            String fullName = txtFullNameRes.getText().toString();

            if(dtb.checkUserExist(email)){
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT);
            }
            else{
                dtb.userCreate(email, pass, fullName, "user",null);
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT);


            }
        });

        btnBackToLogin.setOnClickListener((view) -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

//    public void take_Pic(final View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//
//            if (resultCode == RESULT_OK) {
//
//                Bitmap myBitmap = data.getExtras().getParcelable("data");
//                picture = myBitmap;
//                ImageView photo = (ImageView) findViewById(R.id.imgViewAvatar);
//                photo.setImageBitmap(myBitmap);// here I am setting the pic to an image view for the user to have a look.
//
//            }
//
//        }
//    }
}