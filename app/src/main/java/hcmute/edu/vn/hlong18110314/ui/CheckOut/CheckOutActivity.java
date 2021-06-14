package hcmute.edu.vn.hlong18110314.ui.CheckOut;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;
import hcmute.edu.vn.hlong18110314.ui.bill.BillActivity;
import hcmute.edu.vn.hlong18110314.ui.userDetail.UserInfoViewModel;

public class CheckOutActivity extends AppCompatActivity {
    ArrayList<CartModel> lCartModel;
    RecyclerView rvCart;
    Database database;
    static   TextView totalPriceCart;
    static Button btnCheckOut;
    private UserInfoViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Giỏ hàng");
        database = new Database(this, Database.DATABASE_NAME, null);
        loadData();
        calculatorTotalPrice();
        onClickCheckOut();
        Log.e("ON RESUME", "RESUME");

    }

    private  void onClickCheckOut() {
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserModel.CURRENT_USER != null){
                    Log.e("USER", UserModel.CURRENT_USER.getFullName() );
                    //Lấy ngày hiện tại của khu vực
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    //Gán dữ liệu vào newOrder
                    OrderModel newOrder = new OrderModel();
                    newOrder.setName(UserModel.CURRENT_USER.getFullName());
                    newOrder.setDateCreated(dtf.format(now));
                    newOrder.setUserID(UserModel.CURRENT_USER.getId());
                    int totalPrice = 0;
                    for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
                        totalPrice +=  MainActivity.arrayCart.get(i).getTotalPrice();
                    }
                    newOrder.setTotal( totalPrice);
                    newOrder.setStatus("Đã thanh toán");
                    //Thêm order
                    OrderModel newOrderId = database.createOrder(newOrder);
                    //Thêm sản phẩm vào order
                    for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
                        OrderDetailModel newItemOrder = new OrderDetailModel();
                        newItemOrder.setOrderId(newOrderId.getId());
                        newItemOrder.setProductId(MainActivity.arrayCart.get(i).productId);
                        newItemOrder.setQuantity(MainActivity.arrayCart.get(i).numberOfProduct);
                        newItemOrder.setTotal(MainActivity.arrayCart.get(i).totalPrice);
                        database.createOrderDetail(newItemOrder);
                        Log.e("ODER NEW " + i , "SUCCESS");
                    }
                    // xóa giỏ hàng
                    MainActivity.arrayCart.removeAll(MainActivity.arrayCart);
                    Log.e("Cart HERE", String.valueOf(MainActivity.arrayCart.size()));
                    // Chuyển trang BillActivity
                    int order_id = newOrderId.getId();
                    Intent intent = new Intent(CheckOutActivity.this, BillActivity.class);
                    intent.putExtra("orderID", order_id);
                    startActivity(intent);

                }
                else{
                    Log.e("USER", "NULL" );
                }
            }
        });

    }
    public  static  void enableButton(Boolean isEnable){
        btnCheckOut.setEnabled(isEnable);
        if(isEnable == false){
            btnCheckOut.setVisibility(View.INVISIBLE);
        }else {
            btnCheckOut.setVisibility(View.VISIBLE);
        }
    }
    public static void calculatorTotalPrice() {
        int totalPrice = 0;
        for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
            totalPrice += MainActivity.arrayCart.get(i).getTotalPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        totalPriceCart.setText(decimalFormat.format(totalPrice) + " Đ" );

    }

    public void loadData() {
        totalPriceCart = (TextView) findViewById(R.id.text_totalPrice);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        rvCart = (RecyclerView) findViewById(R.id.rvCart);
        lCartModel = MainActivity.arrayCart;
        CheckOutAdapter checkOutAdapter = new CheckOutAdapter(lCartModel);
        rvCart.setAdapter(checkOutAdapter);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        calculatorTotalPrice();
        Log.e("ON RESUME", "RESUME");
        Log.e("ON CART", String.valueOf(MainActivity.arrayCart.size()));
        if(MainActivity.arrayCart.size() == 0){
            enableButton(false);
        }
    }
}