package hcmute.edu.vn.hlong18110314.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutActivity;

public class ProductActivity extends AppCompatActivity {
    List<ProductModel> lSearchproducts;
    RecyclerView rvProducts;
    static TextView totalPriceCart;
    static TextView txtSizeItem;
    static Button btnGoCart;
    String result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        // toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Sản phẩm");
//        getSupportActionBar().setBackgroundDrawable(Color.parseColor("@color/primary_color"));
        //Functions
        loadData();
        calculatorTotalPrice();
        moveCheckOutActivity();
        result = "CANCELED";
    }

    private void moveCheckOutActivity() {
        if(MainActivity.arrayCart.size() == 0){
            enableButton(false);
        }
        btnGoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CheckOutActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadData() {
        // RecycleView
        rvProducts = (RecyclerView) findViewById(R.id.dataProduct);
        Integer category_id = getIntent().getIntExtra("CATEGORY_ID",1);
        Database database = new Database(this, Database.DATABASE_NAME, null);
        List<ProductModel> dbproducts = database.getProductByCategoryId(category_id);
        lSearchproducts = dbproducts;
        ProductAdapter adapter = new ProductAdapter(lSearchproducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        //TextView totalPrice
        totalPriceCart = (TextView) findViewById(R.id.text_totalPrice);
        txtSizeItem = (TextView) findViewById(R.id.txtSizeItem);
        //Button
        btnGoCart = (Button) findViewById(R.id.btnCheckOut);
    }
    public  static  void enableButton(Boolean isEnable){
        btnGoCart.setEnabled(isEnable);
        if(isEnable == false){
            btnGoCart.setVisibility(View.INVISIBLE);
        }else {
            btnGoCart.setVisibility(View.VISIBLE);
        }
    }
    public static void calculatorTotalPrice() {
        int totalPrice = 0;
        for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
            totalPrice += MainActivity.arrayCart.get(i).getTotalPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        totalPriceCart.setText(decimalFormat.format(totalPrice) + " Đ" );
        //
        txtSizeItem.setText(" ( "+String.valueOf(MainActivity.arrayCart.size())+" ): ");
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
        loadData();
        calculatorTotalPrice();
        if(MainActivity.arrayCart.size() == 0){
            enableButton(false);
        }
    }
}