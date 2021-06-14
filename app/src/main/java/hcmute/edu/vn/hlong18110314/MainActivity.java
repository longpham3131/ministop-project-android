package hcmute.edu.vn.hlong18110314;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.CategoryModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;

public class MainActivity extends AppCompatActivity {
    Database database;
    public  static ArrayList<CartModel> arrayCart ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(UserModel.CURRENT_USER == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        database = new Database(this, Database.DATABASE_NAME, null);
//        deleteDatabase(Database.DATABASE_NAME);
        if(database.getAllProducts().size() == 0){
            Log.e("PRODUCT TABLE ", "NULL");
            createDataBase();

        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_category,R.id.navigation_home,R.id.navigation_userinfo)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        String value= getIntent().getStringExtra("key");
        if (value != null){
            navView.setSelectedItemId(R.id.navigation_category);
        }
        else{
            Log.e("KEY", "NULL");
        }
        //arrayCart
        if(arrayCart != null){
            Log.e("Cart", String.valueOf(arrayCart.size()));

        }else{
            arrayCart = new ArrayList<>();
        }


    }

    private void createDataBase() {

        database.userCreate("user@gmail.com", "123", "Pham Hoang Long", "user", null);
        database.createCategory(new CategoryModel("CƠM NGON MẸ NẤU",null));//1
        database.createCategory(new CategoryModel("MÌ",null));//2
        database.createCategory(new CategoryModel("THỨC ĂN NHANH",null));//3
        database.createCategory(new CategoryModel("ĂN VẶT",null));//4
        database.createCategory(new CategoryModel("NƯỚC UỐNG",null));//5
        database.createCategory(new CategoryModel("THỨC ĂN CHAY",null));//6

        // CƠM NGON MẸ NẤU ID = 1
        database.createProduct(new ProductModel("CƠM NGON THỊT KHO ĐẬU HỦ",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON KHO QUẸT",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON GÀ KHO GỪNG",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON GÀ NẤU CHAO",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON CANH CHUA CÁ LÓC",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON GÀ XỐI MỠ",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON BÒ XÀO DƯA CẢI",null,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON BÒ XÀO RAU MUỐNG",null,1,"",25000));
        database.createProduct(new ProductModel("CANH CHUA CÁ LÓC",null,1,"",15000));
        database.createProduct(new ProductModel("CANH KHOAI MỠ THỊT BẰM",null,1,"",15000));
        database.createProduct(new ProductModel("CANH BÍ ĐỎ THỊT BA CHỈ",null,1,"",15000));
        database.createProduct(new ProductModel("CANH BÍ BAO THỊT BẰM",null,1,"",15000));

        //MÌ ID = 2
        database.createProduct(new ProductModel("MÌ KHÔ XÀO BÒ",null,2,"",25000));
        database.createProduct(new ProductModel("MÌ KHÔ TRỨNG CHIÊN ỐP LA",null,2,"",25000));
        database.createProduct(new ProductModel("MÌ HOÀNH THÁNH",null,2,"",25000));
        database.createProduct(new ProductModel("MÌ XƯƠNG HẦM CỦ CẢI",null,2,"",25000));
        database.createProduct(new ProductModel("MÌ KHÔ XÀO ĐÙI GÀ QUAY",null,2,"",35000));
        database.createProduct(new ProductModel("MÌ TONKOTSU NHẬT BẢN",null,2,"",35000));
        database.createProduct(new ProductModel("MÌ CAY 7 CẤP ĐỘ HÀN QUỐC",null,2,"",45000));
        database.createProduct(new ProductModel("MÌ THÊM (SỢI MÌ THƯỜNG)",null,2,"",5000));
        database.createProduct(new ProductModel("MÌ THÊM (SỢI MÌ NHẬT BẢN)",null,2,"",12000));
        database.createProduct(new ProductModel("MÌ THÊM (SỢI MÌ HÀN QUỐC)",null,2,"",12000));

        // THỨC ĂN NHANH ID = 3
        database.createProduct(new ProductModel("ĐÙI GÀ CHIÊN GIÒN",null,3,"",35000));
        database.createProduct(new ProductModel("ĐÙI GÀ CHIÊN GIÒN CAY",null,3,"",38000));
        database.createProduct(new ProductModel("KHOAI TÂY CHIÊN",null,3,"",27000));
        database.createProduct(new ProductModel("KHOAI TÂY CHIÊN PHỦ PHÔ MAI",null,3,"",30000));
        database.createProduct(new ProductModel("KHOAI TÂY NGHIỀN",null,3,"",20000));
        database.createProduct(new ProductModel("COMBO 2 ĐÙI GÀ CHIÊN GIÒN 1 KHOAI TÂY CHIÊN",null,3,"",87000));
        database.createProduct(new ProductModel("COMBO 2 ĐÙI GÀ CHIÊN GIÒN CAY 1 KHOAI TÂY CHIÊN",null,3,"",93000));
        database.createProduct(new ProductModel("SNACK MỰC CHIÊN",null,3,"",30000));
        database.createProduct(new ProductModel("GÀ VIÊN LẮC PHÔ MAI",null,3,"",40000));

        //ĂN VẶT ID = 4
        database.createProduct(new ProductModel("BÁNH TRÁNG MUỐI TẮC",null,4,"",12000));
        database.createProduct(new ProductModel("BÁNH TRÁNG MUỐI TẮC CAY",null,4,"",14000));
        database.createProduct(new ProductModel("BÁNH TRÁNG CUỐN",null,4,"",15000));
        database.createProduct(new ProductModel("XOÀI LẮC CHUA NGỌT",null,4,"",15000));
        database.createProduct(new ProductModel("BÁNH TRÁNG TRỘN SÀI GÒN",null,4,"",15000));
        database.createProduct(new ProductModel("SỮA CHUA NHA ĐAM",null,4,"",17000));
        database.createProduct(new ProductModel("BÁNH TRÁNG TRỘN PHÔ MAI",null,4,"",17000));
        database.createProduct(new ProductModel("SỮA CHUA NẾP CẨM",null,4,"",20000));
        database.createProduct(new ProductModel("DÂU TÂY LẮC CHUA NGỌT",null,4,"",25000));
        database.createProduct(new ProductModel("SỮA CHIÊN PHỦ SIRO",null,4,"",35000));

        //TRÀ SỮA ID = 5
        database.createProduct(new ProductModel("CÀ PHÊ",null,5,"",10000));
        database.createProduct(new ProductModel("COCA COLA",null,5,"",12000));
        database.createProduct(new ProductModel("SPRITE",null,5,"",12000));
        database.createProduct(new ProductModel("MIRINDA",null,5,"",12000));
        database.createProduct(new ProductModel("PEPSI",null,5,"",12000));
        database.createProduct(new ProductModel("CÀ PHÊ SỮA ĐÁ",null,5,"",13000));
        database.createProduct(new ProductModel("TRÀ SỮA CA CAO",null,5,"",20000));
        database.createProduct(new ProductModel("TRÀ SỮA KHOAI MÔN",null,5,"",20000));
        database.createProduct(new ProductModel("TRÀ SỮA BỒ TÁT QUAN ÂM",null,5,"",25000));
        database.createProduct(new ProductModel("TRÀ SỮA TRUYỀN THỐNG",null,5,"",25000));
        database.createProduct(new ProductModel("TRÀ SỮA TRÂN CHÂU MẬT ONG",null,5,"",30000));
        database.createProduct(new ProductModel("TRÀ SỮA TRÂN CHÂU MẬT ONG HOÀNG KIM",null,5,"",35000));
        database.createProduct(new ProductModel("TRÀ SEN VÀNG",null,5,"",40000));
        database.createProduct(new ProductModel("CARAMEL MACCHIATO",null,5,"",40000));
        database.createProduct(new ProductModel("FREEZE TRÀ XANH",null,5,"",40000));

        //HEALTHHY FOOD ID = 6
        database.createProduct(new ProductModel("GỎI HOA CHUỐI",null,6,"",27000));
        database.createProduct(new ProductModel("CƠM CHIÊN RANG TỎI",null,6,"",27000));
        database.createProduct(new ProductModel("CƠM TRẮNG + ĐẬU HỦ TƯƠNG ",null,6,"",27000));
        database.createProduct(new ProductModel("CƠM TRẮNG + CANH NẤM ",null,6,"",35000));
        database.createProduct(new ProductModel("CƠM SƯỜN NON CHAY",null,6,"",37000));
        database.createProduct(new ProductModel("CƠM SƯỜN NƯỚNG XÁ XÍU CHAY",null,6,"",37000));
        database.createProduct(new ProductModel("NGŨ CỐC DINH DƯỠNG ",null,6,"",40000));










































    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sub_main_menu, menu);
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            Log.d("color", "khac null");
//            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("@color/submenu")));
            bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_menu));
        }else {
            Log.d("color", "null");
        }
        return true;
    }
}