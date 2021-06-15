package hcmute.edu.vn.hlong18110314;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;

public class LoginActivity extends AppCompatActivity {
    public Button btnLogin;
    public  Button btnRegister;
    public EditText emailText;
    public EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.txtEmailLog);
        passwordText = (EditText) findViewById(R.id.txtPasswordLog);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            Database dtb = new Database(getApplicationContext(),Database.DATABASE_NAME,null);
            UserModel user = dtb.userValidation(email,password);

            if(user!= null){
                UserModel.CURRENT_USER = user;
                if(user.getRole().trim().equals("admin")){

                }
                else if(user.getRole().trim().equals("user")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
            else {
                OpenDialogUpdate("Đăng nhập", "Email hoặc mật khẩu không đúng");
            }

        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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