package hcmute.edu.vn.hlong18110314.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutActivity;

public class ProductActivity extends AppCompatActivity {
    List<ProductModel> lSearchproducts;
    RecyclerView rvProducts;
    String result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        // toolbar
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Sản phẩm");

        Button btnGoCart = findViewById(R.id.btnCheckOut);

        btnGoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CheckOutActivity.class);
                startActivity(intent);

            }
        });
        
        rvProducts = (RecyclerView) findViewById(R.id.dataProduct);

        Integer category_id = getIntent().getIntExtra("CATEGORY_ID",1);




        Database database = new Database(this, Database.DATABASE_NAME, null);
//        List<ProductModel> dbproducts = database.getAllProducts();
        List<ProductModel> dbproducts = database.getProductByCategoryId(category_id);
        lSearchproducts = dbproducts;
        ProductAdapter adapter = new ProductAdapter(lSearchproducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        result = "CANCELED";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            result = "OK";
            Intent intent = new Intent(ProductActivity.this, MainActivity.class);
            intent.putExtra("key", result);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        Integer category_id = getIntent().getIntExtra("CATEGORY_ID",1);

        Database database = new Database(this, Database.DATABASE_NAME, null);



        List<ProductModel> dbproducts = database.getProductByCategoryId(category_id);
        lSearchproducts = dbproducts;
        ProductAdapter adapter = new ProductAdapter(lSearchproducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
    }
}