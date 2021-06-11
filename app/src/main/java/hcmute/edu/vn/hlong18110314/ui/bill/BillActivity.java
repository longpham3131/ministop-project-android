package hcmute.edu.vn.hlong18110314.ui.bill;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;

public class BillActivity extends AppCompatActivity {
    public TextView txtFullNameUser;
    public TextView txtDateCreate;
    public RecyclerView rvBill;
    public TextView txtTotalPrice;
    public Button btnBackToHome;
    public  List<OrderDetailModel> lOrderDetail;
    public static Database database;
    OrderModel orderCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getSupportActionBar().hide();
        database = new Database(this, Database.DATABASE_NAME, null);
        loadData();

    }

    private void loadData() {
        txtFullNameUser = (TextView) findViewById(R.id.txtFullNameUser);
        txtDateCreate = (TextView) findViewById(R.id.txtDateCreate);
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        rvBill = (RecyclerView) findViewById(R.id.rvBill);
        btnBackToHome = (Button) findViewById(R.id.btnBackToHome);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        //Lấy order_id từ checkOut Activity
        Intent intent = getIntent();
        int order_id = intent.getIntExtra("orderID", -1);
        orderCurrent =  database.getByIdOrder(order_id);
        //Thêm tên và ngày tạo của đơn hàng
        txtFullNameUser.setText(orderCurrent.getName());
        txtDateCreate.setText(orderCurrent.getDateCreated());
        txtTotalPrice.setText(decimalFormat.format(orderCurrent.getTotal())+ " VNĐ");


        //Lấy danh sách order detail
        lOrderDetail = database.getAllOrderDetailsByOrderId(orderCurrent);
        BillAdapter billAdapter = new BillAdapter(lOrderDetail);
        rvBill.setAdapter(billAdapter);
        rvBill.setLayoutManager(new LinearLayoutManager(this));

        btnBackToHome.setOnClickListener((view) -> {
            Intent intentGoMain = new Intent(BillActivity.this, MainActivity.class);
            startActivity(intentGoMain);
        });

    }
}
