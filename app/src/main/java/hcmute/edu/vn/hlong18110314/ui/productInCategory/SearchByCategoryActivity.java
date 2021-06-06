package hcmute.edu.vn.hlong18110314.ui.productInCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;

public class SearchByCategoryActivity extends AppCompatActivity {
    List<ProductModel> lSearchproducts;
    RecyclerView rvProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_category);
//        Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show();
        rvProducts = (RecyclerView) findViewById(R.id.rvSearchByCategory);

        Integer category_id = getIntent().getIntExtra("CATEGORY_ID",1);

        Database database = new Database(this, Database.DATABASE_NAME, null);
//        List<ProductModel> dbproducts = database.getAllProducts();
        List<ProductModel> dbproducts = database.getProductByCategoryId(category_id);
        lSearchproducts = dbproducts;
        SearchByCategoryAdapter adapter = new SearchByCategoryAdapter(lSearchproducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        Integer category_id = getIntent().getIntExtra("CATEGORY_ID",1);

        Database database = new Database(this, Database.DATABASE_NAME, null);

        List<ProductModel> dbproducts = database.getProductByCategoryId(category_id);
        lSearchproducts = dbproducts;
        SearchByCategoryAdapter adapter = new SearchByCategoryAdapter(lSearchproducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
    }
}