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

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.hlong18110314.database.Database;

public class RegisterActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    public Button btnTakePic;
    public Button btnRegister;
    public Bitmap picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnTakePic = (Button) findViewById(R.id.btnTakePic);
        btnTakePic.setOnClickListener((view)->{
            take_Pic(view);
        });

        Database dtb = new Database(getApplicationContext(), Database.DATABASE_NAME, null);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        EditText emailView = (EditText) findViewById(R.id.txtEmailRes);
        EditText passView = (EditText) findViewById(R.id.txtPasswordRes);

        btnRegister.setOnClickListener((view) -> {
            String email = emailView.getText().toString();
            String pass = passView.getText().toString();


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            if(!dtb.checkUserExist(email)){
                dtb.userCreate(email, pass, "", "user",byteArray);
            }


        });
    }

    public void take_Pic(final View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Bitmap myBitmap = data.getExtras().getParcelable("data");
                picture = myBitmap;
                ImageView photo = (ImageView) findViewById(R.id.imgViewAvatar);
                photo.setImageBitmap(myBitmap);// here I am setting the pic to an image view for the user to have a look.

            }

        }
    }
}