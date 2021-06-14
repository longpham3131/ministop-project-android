package hcmute.edu.vn.hlong18110314.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.hlong18110314.MainActivity;
import hcmute.edu.vn.hlong18110314.R;
import hcmute.edu.vn.hlong18110314.database.Database;
import hcmute.edu.vn.hlong18110314.database.Model.CartModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.ui.product.ProductActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Database database;
    public RecyclerView rvCart;
    Button btnGoToMom;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnGoToMom = (Button) root.findViewById(R.id.btnGoToMom);
        btnGoToMom.setOnClickListener((view) -> {
            Intent intent = new Intent(inflater.getContext(), ProductActivity.class);
            intent.putExtra("CATEGORY_ID", 1);
            startActivity(intent);
        });

        database = new Database(getContext(), Database.DATABASE_NAME, null);
        List<ProductModel> dbproducts = database.getProductByCategoryId(1);
        HomeAdapter homeAdapter = new HomeAdapter(dbproducts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCart = (RecyclerView) root.findViewById(R.id.rvCart);
        rvCart.setAdapter(homeAdapter);
        rvCart.setLayoutManager(layoutManager);

        return root;
    }
}