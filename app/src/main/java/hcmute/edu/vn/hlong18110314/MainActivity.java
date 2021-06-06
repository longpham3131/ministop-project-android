package hcmute.edu.vn.hlong18110314;


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

import java.util.List;

import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.CategoryModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteDatabase(Database.DATABASE_NAME);
        Database database = new Database(this, Database.DATABASE_NAME, null);
        database.userCreate("user@gmail.com", "123", "Pham Hoang Long", "user", null);
        database.createCategory(new CategoryModel("SANDWICH",null));//1
        database.createCategory(new CategoryModel("NƯỚC GIẢI KHÁT",null));//2
        database.createCategory(new CategoryModel("SUSHI",null));//3
        database.createCategory(new CategoryModel("ONIGIRI",null));//4
        database.createCategory(new CategoryModel("TRÁNG MIỆNG",null));//5
        database.createCategory(new CategoryModel("CƠM",null));//6
        database.createCategory(new CategoryModel("ODEN",null));//7
        database.createCategory(new CategoryModel("THỨC ĂN NHANH",null));//8
        database.createCategory(new CategoryModel("STREET FOOD",null));//9
        database.createCategory(new CategoryModel("SALAD",null));//10
        database.createCategory(new CategoryModel("MÌ",null));//11
        database.createProduct(new ProductModel("SANDWICH GÀ NƯỚNG SẢ",null,1,"Thịt ức gà cắt lát được tẩm ướp gia vị cùng sả băm nhuyễn thơm khó cưỡng, nướng vừa tới để dậy mùi hương và gia vị thêm thẩm thấu đậm đà. Kết hợp với xà lách và bắp cải tím để hương vị thêm hoàn hảo và trông thu hút",25000));
        database.createProduct(new ProductModel("CREPE SẦU RIÊNG",null,1,"BÁNH CREPE SẦU RIÊNG “ ĐỈNH CỦA CHÓP” NAY ĐÃ GIA NHẬP MINISTOP\n" +
                "Bánh Crepe được mệnh danh là bánh tráng miệng thơm ngon chiều lòng mọi thực khách.\n" +
                "Khỏi phải bàn đến mùi thơm của chiếc bánh này luôn nha, chỉ cần nghe thôi đã muốn cắn ngay một miếng rồi.\n" +
                "Bánh có lớp vỏ dai dai hòa cùng nhân kem tươi và sầu riêng mềm thơm ngon. Cắn tới đâu, tan chảy tới đó.\n" +
                "Dắt tay nhau đến MINISTOP ngay để cùng nếm thử thức món ngon khó cưỡng này Bạn nhé!",25000));
        database.createProduct(new ProductModel("SANDWICH GÀ KARAAGE",null,1,"Gà Karaage tươi dinh dưỡng với từng thớ thịt trắng buốt dai ngon được bao bọc bởi lớp bột chiên vàng giòn hấp dẫn sau đó nằm gọn giữa \"hai mảnh\" bánh mì tam giác mềm thơm.",25000));
        database.createProduct(new ProductModel("SANDWICH CÁ CHIÊN & XÚC XÍCH XÔNG KHÓI",null,1,"Cá chiên với lớp vỏ giòn tan bao bọc lấy thịt cá mềm ngọt tự nhiên kết hợp với xúc xích xông khói dai giòn sần sật nằm gọn gẽ giữa 2 lớp bánh mì mềm thơm. Kết hợp thêm chút xà lách tươi ngon để trung hòa trọn vị, ăn hoài không ngán.",25000));
        database.createProduct(new ProductModel("SANDWICH TÔM SỐT THÁI & TRỨNG CHIÊN",null,1,"Vẫn là những miếng bánh mì tươi thơm lừng hương mì và trứng nhưng lại kết hợp lạ lẫm với tôm sốt Thái đậm vị và trứng chiên bùi bùi dinh dưỡng. Chút rau xanh gia nhập để trung hòa trọn vị, ăn không ngán mà lúc chán lại thèm ăn.",27000));
        database.createProduct(new ProductModel("SANDWICH THỊT NƯỚNG CAY & TRỨNG",null,1,"Miếng bánh mì tươi mềm thơm kẹp cặt lấy thịt nướng đậm đà cay xé, kết hợp thêm trứng giàu protein và chất xơ từ xà lách tươi giòn sẽ là lựa chọn tuyệt vời cho bữa sáng tiện lợi.",26000));
        database.createProduct(new ProductModel("MAI GHẸ FARCE",null,1,"Siêu phẩm Mai Ghẹ Farce hạ cánh vào nồi lẩu Oden trứ danh. Sự tươi ngon và dinh dưỡng từ ghẹ hòa cùng nước lẩu nóng hổi đậm đà, cay xé nồng nàn tạo nên một kiệt tác ẩm thực.",15000));
        database.createProduct(new ProductModel("BÁNH BÔNG LAN PHÔ MAI",null,1,"Bánh bông lan phô mai là món bánh nổi lên trong vài năm trở lại đây khiến nhiều người yêu thích và tìm mua. Ngoài cốt bánh thơm mềm, đậm vị phô mai, khi ăn có cảm giác bánh tan ngay trong miệng thì vẻ ngoài căng tròn và mềm mịn nhìn cực thích đã tạo nên sức hút cho món bánh này.",18000));
        database.createProduct(new ProductModel("SANDWICH TRỨNG MAYO",null,1,"Sandwich trứng mayo",18000));
        database.createProduct(new ProductModel("ONIGIRI BÒ PHÔ MAI",null,2,"Nắm cơm chắc nịch, dẻo thơm kết hợp hoàn hảo với bò dinh dưỡng, lại còn thêm phô mai béo ngậy làm tăng hương vị. Bữa ăn gọn nhẹ mà đầy đủ như vậy thì còn gì bằng đúng không nào.",17000));
        database.createProduct(new ProductModel("ONIGIRI XÚC XÍCH & PHÔ MAI",null,2,"Cơm nắm luôn là sự lựa chọn hoàn hảo cho những bữa ăn vội vàng. Nắm cơm chắc nịch, mềm dẻo ôm lấy nhân thơm ngon",16000));
        database.createProduct(new ProductModel("Temaki Onigiri Gà Nướng",null,2,"Nắm cơm chắc nịch, mềm dẻo được bao bọc bởi rong biển dai giòn dinh dưỡng, nhân gà nướng thơm lừng được nêm nếm chuẩn vị cho bữa ăn trọn vẹn, no lâu.",12000));
        database.createProduct(new ProductModel("Temaki Sushi gà sốt phô mai",null,2,"Nắm cơm mềm dẻo bao bọc lấy nhân thịt gà sốt phô mai béo ngậy đầy kích thích. Tất cả nằm trọn trong lớp rong biển dai ngon dinh dưỡng. Chỉ cẩn ăn một lần là mê một đời luôn nhé!",14000));
        database.createProduct(new ProductModel("Temaki Onigiri thịt heo sốt bulgogi",null,2,"Đột phá mới trong làng cơm nắm chính là món Temaki Onirigi heo sốt bulgogi. Vẫn là rong biển dai giòn và nắm cơm mềm dẻo nhưng nhân heo sốt bulgogi sẽ khiến bao tử bạn háo hức cho mà xem. Vị beo béo cay cay của sốt hòa vào từng hạt cơm làm nên vị ngon khó cưỡng.",15000));
        database.createProduct(new ProductModel("Temaki Onigiri cá hồi mayo",null,2,"Temaki Onigiri cá hồi mayo",16000));
        database.createProduct(new ProductModel("Temaki onigiri bò sốt phomai",null,2,"Temaki onigiri bò sốt phomai",16000));
        database.createProduct(new ProductModel("Temaki Onigiri Cá Ngừ Mayo",null,2,"Temaki Onigiri từ lâu đã là món ăn quen thuộc được mọi người yêu thích. Và hôm nay, đến với Ministop bạn sẽ gặp một Temaki Onigiri hoàn toàn mới, sự lột xác hoàn hảo và toàn diện của chúng sẽ mang lại luồng gió mới làm hài lòng hơn khẩu vị của thực khách khó tính.",12000));
        database.createProduct(new ProductModel("SANDWICH GÀ NƯỚNG SẢ",null,2,"",25000));




        List<UserModel> users = database.getAllUser();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_product,R.id.navigation_home,R.id.navigation_cart)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_sub_main_notify:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_sub_main_profile:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_sub_main_search:
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}