package hcmute.edu.vn.hlong18110314.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.ui.CheckOut.CheckOutActivity;
import hcmute.edu.vn.hlong18110314.ui.home.HomeAdapter;
import hcmute.edu.vn.hlong18110314.ui.product.ProductActivity;

public class CategoryFragment extends Fragment {
    private CategoryViewModel categoryViewModel;
    public ImageButton btnCtgRice;
    public ImageButton btnCtgNoodle;
    public ImageButton btnCtgFFood;
    public ImageButton btnCtgSnack;
    public ImageButton btnCtgSoftDrink;
    public ImageButton btnCtgChay;

    public  static RecyclerView rvCart;
    public static TextView txtNumberItem;
    public static TextView txtTotalPriceCart;
    public static TextView rvCartNull;
    public static Button btnGoCheckOut;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        btnCtgRice = (ImageButton) root.findViewById(R.id.btn_category_1);
        btnCtgNoodle = (ImageButton) root.findViewById(R.id.btn_category_2);
        btnCtgFFood = (ImageButton) root.findViewById(R.id.btn_category_3);
        btnCtgSnack = (ImageButton) root.findViewById(R.id.btn_category_4);
        btnCtgSoftDrink = (ImageButton) root.findViewById(R.id.btn_category_5);
        btnCtgChay = (ImageButton) root.findViewById(R.id.btn_category_6);

        txtNumberItem = (TextView) root.findViewById(R.id.txtNumberItem);
        txtTotalPriceCart = (TextView) root.findViewById(R.id.txtTotalPriceCart);
        rvCartNull = (TextView) root.findViewById(R.id.rvCartNull);
        btnGoCheckOut = (Button) root.findViewById(R.id.btnGoCheckOut);

        btnCtgRice.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 1);
            startActivity(intent);
        });
        btnCtgNoodle.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 2);
            startActivity(intent);
        });
        btnCtgFFood.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 3);
            startActivity(intent);
        });
        btnCtgSnack.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 4);
            startActivity(intent);
        });
        btnCtgSoftDrink.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 5);
            startActivity(intent);
        });
        btnCtgChay.setOnClickListener((view)->{
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 6);
            startActivity(intent);
        });
        btnGoCheckOut.setOnClickListener((view) ->{
            Intent intent = new Intent(inflater.getContext(), CheckOutActivity.class);
            startActivity(intent);
        });
        rvCart = (RecyclerView) root.findViewById(R.id.rvCart);
        if(MainActivity.arrayCart.size() == 0){
            enableFunction(false);
        }
        else{
            enableFunction(true);
        }
        onCreateRecycleView();
        calculatorTotalPrice();


        return root;
    }

    private void onCreateRecycleView() {
        List<CartModel> cartModelList = MainActivity.arrayCart;
        CategoryAdapter homeAdapter = new CategoryAdapter(cartModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCart.setAdapter(homeAdapter);
        rvCart.setLayoutManager(layoutManager);
    }
    public static void enableFunction(Boolean isEnable){
        btnGoCheckOut.setEnabled(isEnable);
        if(isEnable == false){
            rvCartNull.setVisibility(View.VISIBLE);
            rvCart.setVisibility(View.GONE);
            btnGoCheckOut.setVisibility(View.INVISIBLE);
        }else {
            rvCartNull.setVisibility(View.GONE);
            rvCart.setVisibility(View.VISIBLE);
            btnGoCheckOut.setVisibility(View.VISIBLE);
        }
    }
    public static void calculatorTotalPrice() {
        int totalPrice = 0;
        for(int i = 0 ; i < MainActivity.arrayCart.size() ; i++){
            totalPrice += MainActivity.arrayCart.get(i).getTotalPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalPriceCart.setText("Tổng tiền: " + decimalFormat.format(totalPrice) + " Đ" );
        txtNumberItem.setText(String.valueOf(MainActivity.arrayCart.size())+" Món");
    }
}
