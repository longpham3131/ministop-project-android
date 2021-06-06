package hcmute.edu.vn.hlong18110314;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;

public class LoginActivity extends AppCompatActivity {
    public Button loginBtn;
    public EditText emailText;
    public EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.txtEmailLog);
        passwordText = (EditText) findViewById(R.id.txtPasswordLog);

        loginBtn = (Button) findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(v -> {
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

        });

    }
}