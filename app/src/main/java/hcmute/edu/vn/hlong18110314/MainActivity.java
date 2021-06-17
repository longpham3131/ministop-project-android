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

        database.userCreate("user@gmail.com", "123", "Pham Hoang Long", "user", 0);
        database.createCategory(new CategoryModel("CƠM NGON MẸ NẤU",0));//1
        database.createCategory(new CategoryModel("MÌ",0));//2
        database.createCategory(new CategoryModel("THỨC ĂN NHANH",0));//3
        database.createCategory(new CategoryModel("ĂN VẶT",0));//4
        database.createCategory(new CategoryModel("NƯỚC UỐNG",0));//5
        database.createCategory(new CategoryModel("THỨC ĂN CHAY",0));//6

        // CƠM NGON MẸ NẤU ID = 1
        database.createProduct(new ProductModel("CƠM NGON THỊT KHO ĐẬU HỦ",R.drawable.thit_kho_dau_hu,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON KHO QUẸT",R.drawable.kho_quet,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON GÀ KHO GỪNG",R.drawable.ga_kho_rung,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON VỊT NẤU CHAO",R.drawable.vit_nau_chao,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON CANH CHUA CÁ LÓC",R.drawable.canh_chua_ca_loc,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON GÀ XỐI MỠ",R.drawable.ga_xoi_mo,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON BÒ XÀO DƯA CẢI",R.drawable.bo_xao_dua_cai,1,"",25000));
        database.createProduct(new ProductModel("CƠM NGON BÒ XÀO RAU MUỐNG",R.drawable.bo_xao_rau_muong,1,"",25000));
        database.createProduct(new ProductModel("CANH CHUA CÁ LÓC",R.drawable.canh_chua_ca_loc,1,"",15000));
        database.createProduct(new ProductModel("CANH KHOAI MỠ THỊT BẰM",R.drawable.canh_khoai_mo,1,"",15000));
        database.createProduct(new ProductModel("CANH BÍ ĐỎ THỊT BẰM",R.drawable.canh_bi_do_thit_bam,1,"",15000));
        database.createProduct(new ProductModel("CANH BÍ BAO THỊT BẰM",R.drawable.canh_bi_dao_thit_bam,1,"",15000));

        //MÌ ID = 2
        database.createProduct(new ProductModel("MÌ KHÔ XÀO BÒ",R.drawable.mi_xao_bo,2,"",25000));
        database.createProduct(new ProductModel("MÌ KHÔ TRỨNG CHIÊN ỐP LA",R.drawable.mi_xao_trung,2,"",25000));
        database.createProduct(new ProductModel("MÌ HOÀNH THÁNH",R.drawable.mi_hoanh_thanh,2,"",25000));
        database.createProduct(new ProductModel("MÌ XƯƠNG HẦM CỦ CẢI",R.drawable.mi_xuong_ham_cu_cai,2,"",25000));
        database.createProduct(new ProductModel("MÌ KHÔ XÀO ĐÙI GÀ QUAY",R.drawable.mi_dui_ga,2,"",35000));
        database.createProduct(new ProductModel("MÌ TONKOTSU NHẬT BẢN",R.drawable.mi_tonkotsu,2,"",35000));
        database.createProduct(new ProductModel("MÌ CAY 7 CẤP ĐỘ HÀN QUỐC",R.drawable.mi_cay,2,"",45000));


        // THỨC ĂN NHANH ID = 3
        database.createProduct(new ProductModel("ĐÙI GÀ CHIÊN GIÒN",R.drawable.dui_ga_chien_gion,3,"",35000));
        database.createProduct(new ProductModel("KHOAI TÂY CHIÊN",R.drawable.khoai_tay_chien,3,"",27000));
        database.createProduct(new ProductModel("KHOAI TÂY CHIÊN PHỦ PHÔ MAI",R.drawable.khoai_lac_pho_mai,3,"",30000));
        database.createProduct(new ProductModel("KHOAI TÂY NGHIỀN",R.drawable.khoai_tay_nghien,3,"",20000));
        database.createProduct(new ProductModel("SNACK MỰC CHIÊN",R.drawable.snack_muc,3,"",30000));
        database.createProduct(new ProductModel("GÀ VIÊN LẮC PHÔ MAI",R.drawable.ga_vien_lac_pho_mai,3,"",40000));

        //ĂN VẶT ID = 4
        database.createProduct(new ProductModel("BÁNH TRÁNG MUỐI TẮC",R.drawable.banh_trang_muoi_tac,4,"",12000));
        database.createProduct(new ProductModel("BÁNH TRÁNG CUỐN",R.drawable.banh_trang_cuon,4,"",15000));
        database.createProduct(new ProductModel("XOÀI LẮC CHUA NGỌT",R.drawable.xoai_lac,4,"",15000));
        database.createProduct(new ProductModel("BÁNH TRÁNG TRỘN SÀI GÒN",R.drawable.banh_tran_tron,4,"",15000));
        database.createProduct(new ProductModel("SỮA CHUA NHA ĐAM",R.drawable.sua_chua_nha_dam,4,"",17000));
        database.createProduct(new ProductModel("SỮA CHUA NẾP CẨM",R.drawable.sua_chua_nep_cam,4,"",20000));
        database.createProduct(new ProductModel("DÂU TÂY LẮC CHUA NGỌT",R.drawable.dau_tay_lac,4,"",25000));

        //TRÀ SỮA ID = 5
        database.createProduct(new ProductModel("CÀ PHÊ",R.drawable.ca_phe,5,"",10000));
        database.createProduct(new ProductModel("CÀ PHÊ SỮA ĐÁ",R.drawable.ca_phe_sua,5,"",13000));
        database.createProduct(new ProductModel("COCA COLA",R.drawable.coca_cola,5,"",12000));
        database.createProduct(new ProductModel("SPRITE",R.drawable.sprite,5,"",12000));
        database.createProduct(new ProductModel("MIRINDA",R.drawable.mirinda,5,"",12000));
        database.createProduct(new ProductModel("PEPSI",R.drawable.pepsi,5,"",12000));
        database.createProduct(new ProductModel("TRÀ SỮA CA CAO",R.drawable.tra_sua_cacao,5,"",20000));
        database.createProduct(new ProductModel("TRÀ SỮA KHOAI MÔN",R.drawable.tra_sua_khoai_mon,5,"",20000));
        database.createProduct(new ProductModel("TRÀ SỮA TRUYỀN THỐNG",R.drawable.tra_sua,5,"",25000));
        database.createProduct(new ProductModel("TRÀ SEN VÀNG",R.drawable.tra_sen_vang,5,"",40000));
        database.createProduct(new ProductModel("FREEZE TRÀ XANH",R.drawable.freeze_tra_xanh,5,"",40000));

        //HEALTHHY FOOD ID = 6
        database.createProduct(new ProductModel("NỘM HOA CHUỐI",R.drawable.goi_hoa_chuoi,6,"",27000));
        database.createProduct(new ProductModel("CƠM CHIÊN RANG TỎI",R.drawable.com_rang_toi,6,"",27000));
        database.createProduct(new ProductModel("CƠM TRẮNG + ĐẬU HỦ TƯƠNG ",R.drawable.dau_hu_tuong,6,"",27000));
        database.createProduct(new ProductModel("CƠM TRẮNG + CANH NẤM ",R.drawable.canh_nam_rom,6,"",35000));
        database.createProduct(new ProductModel("CƠM SƯỜN NON CHAY",R.drawable.suon_non_chay,6,"",37000));
        database.createProduct(new ProductModel("CƠM SƯỜN NƯỚNG XÁ XÍU CHAY",R.drawable.suon_nuong_chay,6,"",37000));
        database.createProduct(new ProductModel("NGŨ CỐC DINH DƯỠNG ",R.drawable.ngu_coc,6,"",40000));

    }

//    public byte[] convertImageToBye(){
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageInByte = baos.toByteArray();
//    }

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