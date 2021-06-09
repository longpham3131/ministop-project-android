package hcmute.edu.vn.hlong18110314.ui.CheckOut;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.Cart;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.ui.cart.CartAdapter;
import hcmute.edu.vn.hlong18110314.ui.cart.CartViewModel;

public class CheckOutActivity extends AppCompatActivity {
    ArrayList<CartModel> lCartModel;
    RecyclerView rvCart;
    static   TextView totalPriceCart;
    private CartViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        loadData();
        calculatorTotalPrice();

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
        rvCart = (RecyclerView) findViewById(R.id.rvCart);
        lCartModel = MainActivity.arrayCart;
        CheckOutAdapter checkOutAdapter = new CheckOutAdapter(lCartModel);
        rvCart.setAdapter(checkOutAdapter);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
    }
}