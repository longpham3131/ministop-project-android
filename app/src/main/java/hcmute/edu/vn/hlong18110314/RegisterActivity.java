package hcmute.edu.vn.hlong18110314;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
                OpenDialogUpdate("Đăng ký", "LỖI !! : Email của bạn đã được đăng ký");
            }
            else{
                dtb.userCreate(email, pass, fullName, "user",0);
                OpenDialogUpdate("Đăng ký", "Đăng ký thành công");


            }
        });

        btnBackToLogin.setOnClickListener((view) -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
    private void  OpenDialogUpdate(String tittle, String message){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(tittle)
                .setMessage(message)
                .setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
        dialog.show();
    }
}